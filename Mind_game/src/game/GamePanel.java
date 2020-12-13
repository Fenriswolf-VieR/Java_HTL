package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	boolean game_running = true;
	boolean started = false;
	boolean once = false;
	private boolean fullscreen = true;

	long delta = 0;
	long last = 0;
	long fps = 0;
	long frames = 0;
	//double winkle = 0;
	long time = 0;
	long points = 0;
	long gameover = 0;
	int game_state = 3; //0 = not dead, 1 = dead, 2 = won
	int walls_vertical_destroyed = 0;
	int walls_horizontal_destroyed = 0;
	boolean dragon_cooldown = false;
	boolean walls_vert_triggered = false;
	boolean walls_horizon_triggered = false;
	boolean temple_spawned = false;

	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	boolean back = false;
	int speedx = 0;
	int speedy = 0;
	int fictx = 0;
	int ficty = 0;
	int speed = 700;
	//int speed = 350;
	int health = 100;
	int stamina = 100;

	Timer timer;
	BufferedImage[] angel;
	BufferedImage[] angel_back;
	BufferedImage[] angel_fight1;
	BufferedImage[] angel_fight1_back;
	BufferedImage background;
	BufferedImage start_screen;
	BufferedImage victory_screen;
	BufferedImage defeat_screen;
	BufferedImage[] explosion;
	BufferedImage[] c1;
	BufferedImage[] c2;
	BufferedImage[] c3;
	BufferedImage[] c4;
	BufferedImage[] c5;
	BufferedImage[] wall_v;
	BufferedImage[] wall_h;
	BufferedImage[] small_black_back;
	BufferedImage[] small_black;
	BufferedImage[] big_black_back;
	BufferedImage[] big_black;
	BufferedImage[] big_orange_back;
	BufferedImage[] big_orange;
	BufferedImage[] big_yellow_back;
	BufferedImage[] big_yellow;
	BufferedImage[] old_green_back;
	BufferedImage[] old_green;
	BufferedImage[] violett_butterfly_back;
	BufferedImage[] violett_butterfly;

	JFrame frame;

	//Sound_library slib;

	Sprites player;
	Vector<Sprites> actors;
	Vector<Sprites> actors2;

	public static void main(String[] args) {
		new GamePanel(1920, 1080);
	}

	public GamePanel(int w, int h) {
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(Color.black);
		frame = new JFrame("The MIND-GAME");
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.add(this);
		if (fullscreen) {
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setResizable(false);
		}
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		Timer fpsHelper = new Timer(1000, fpsTimer);
		fpsHelper.restart();

		doInitializations();
	}

	private void doInitializations() {

		last = System.nanoTime();

		actors = new Vector<Sprites>();
		actors2 =  new Vector<Sprites>();
		angel_fight1 = this.loadPics("pics/angel_fight1.png", 16);
		//System.out.println("Copyright by René Viehhauser);
		angel_fight1_back = this.loadPics("pics/angel_fight1_back.png", 16);
		angel = this.loadPics("pics/angel.png", 12);
		angel_back = this.loadPics("pics/angel_back.png", 12);
		c1 = this.loadPics("pics/clouds1.png", 1);
		c2 = this.loadPics("pics/clouds3.png", 1);
		c3 = this.loadPics("pics/clouds4.png", 1);
		c4 = this.loadPics("pics/clouds5.png", 1);
		c5 = this.loadPics("pics/clouds6.png", 1);
		wall_v = this.loadPics("pics/wall2.png", 1);
		wall_h = this.loadPics("pics/wall.png", 1);
		small_black_back = this.loadPics("pics/small_flying_black_dragon_back.png", 12);
		small_black= this.loadPics("pics/small_flying_black_dragon.png", 12);
		big_black_back= this.loadPics("pics/big_flying_black_dragon_back.png", 11);
		big_black= this.loadPics("pics/big_flying_black_dragon.png", 11);
		big_orange_back=this.loadPics("pics/big_orange_dragon_back.png", 20);
		big_orange=this.loadPics("pics/big_orange_dragon.png", 20);
		big_yellow_back=this.loadPics("pics/big_yellow_dragon_back.png", 20);
		big_yellow=this.loadPics("pics/big_yellow_dragon.png", 20);
		old_green_back=this.loadPics("pics/old_green_dragon_back.png", 58);
		old_green=this.loadPics("pics/old_green_dragon.png", 58);
		violett_butterfly_back=this.loadPics("pics/violett_butterfly_dragon_back.png", 20);
		violett_butterfly=this.loadPics("pics/violett_butterfly_dragon.png", 20);
		//background = loadPics("pics/night_sky_background.png", 1)[0];
		victory_screen= loadPics("pics/victory_screen.png", 1)[0];
		defeat_screen = loadPics("pics/defeat_screen.png", 1)[0];
		start_screen = loadPics("pics/start_screen.png", 1)[0];
		player = new Angel(angel, (this.getWidth()-angel[0].getWidth())/2, (this.getHeight()-angel[0].getHeight())/2, 80, this);
		//player = new Angel(angel, 0,0, 100, this);
		actors.add(player);

		//create_Temple();
		createClouds(); //12 per summon
		create_Walls(1,getHeight()-wall_h[0].getHeight()); //8 per summon
		create_Walls(2,0); //5 per summon
		create_Dragon();


		timer = new Timer(1000, pointsTimer);
		//timer.restart();

		if (!once) {
			once = true;
			Thread t = new Thread(this);
			t.start();
		}
		//started = true;
	}

	private ActionListener fpsTimer = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			fps = frames;
			frames = 0;

		}
	};

	public void run() {

		while (frame.isVisible()) {
			//System.out.println("Copyright by René Viehhauser);
			computeDelta();

			if (isStarted()) {
				checkKeys();
				doLogic();
				moveObjects();
				remove_health(0,player);
			}

			paintImmediately(this.getBounds());;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}

		}
		System.exit(0);
	}

	private void createClouds() { //12 clouds per spawn
		for (int y = -(getHeight()/10); y <= getHeight(); y += (getHeight()/8)) {
			int x = (int) ((Math.random() * getWidth())-(Math.random()*getWidth()/2));
			create_Cloud(x,y);
		}
	}

	private void create_Cloud(double x, double y) {
		double random = Math.random()*100;
		BufferedImage[] ci = null;
		if(random<=20) {
			ci = c1;
		}else if(random<=40) {
			ci=c2;
		}else if(random<=60) {
			ci=c3;
		}else if(random<=80) {
			ci=c4;
		}else if(random<=100) {
			ci=c5;
		}
		Cloud cloud = new Cloud(ci, x, y, 1000, this);
		random = Math.random()*100;
		if(random<=50) {
			actors2.add(0, cloud);
		}else {
			actors2.add(cloud);
		}

	}

	private void create_Temple(){
		BufferedImage[] temple = this.loadPics("pics/temple.png", 1);
		Temple temple_1;
		boolean intersect = true;
		do {
			double x = Math.random()*getWidth();
			double y = Math.random()*getHeight();
			temple_1 = new Temple(temple, x, y, 1000, this);
			for(int i = 0;i < actors.size();i++) {
				if(actors.elementAt(i).intersects(temple_1)) {
					intersect = true;
					return;
				}
				intersect=false;
			}
		}while(intersect);
		temple_spawned = true;
		actors2.add(temple_1);
	}

	private void create_Dragon() {
		boolean successful = false;
		BufferedImage[] image = null;
		Hybrid_enemies Dragon = null;
		do {
			double melee = Math.random()*100;
			//double melee = 100;
			//double range = Math.random()*100;
			double range = 100;
			double homme = Math.random()*100;
			double version = Math.random()*100;
			boolean home = false;
			double y = Math.random()*getHeight();
			double x = Math.random()*100;
			if(x<=50) {
				x=0;
			}else {
				x=getWidth();
			}
			if(homme<=50) {
				home = true;
			}
			/*if(range <= 50 && melee<= 50) {
				Hybrid_enemies Dragon = new Hybrid_enemies(image, x, y, 100, this, home, true, true);
			}else */if(range <= 50) {
				if(version<=33) {
					image = big_black;
					successful = true;
				}else if(version<=66) {
					image = old_green;
					successful = true;
				}else {
					image = violett_butterfly;
					successful = true;
				}
				boolean intersect = true;
				do {
					Dragon = new Hybrid_enemies(image, x, y, 100, this, home, true, false);
					y = Math.random()*getHeight();
					x = Math.random()*100;
					if(x<=50) {
						x=0;
					}else {
						x=getWidth();
					}
					for(int i = 0;i < actors.size();i++) {
						if(actors.elementAt(i).intersects(Dragon)) {
							intersect = true;
							return;
						}
						intersect=false;
					}
				}while(intersect);
			}else if(melee <= 50) {
				if(version<=33) {
					image = big_yellow;
					successful = true;
				}else if(version<=66) {
					image = big_orange;
					successful = true;
				}else {
					image = small_black;
					successful = true;
				}
				boolean intersect = true;
				do {
					Dragon = new Hybrid_enemies(image, x, y, 100, this, home, false, true);
					y = Math.random()*getHeight();
					x = Math.random()*100;
					if(x<=50) {
						x=0;
					}else {
						x=getWidth();
					}
					for(int i = 0;i < actors.size();i++) {
						if(actors.elementAt(i).intersects(Dragon)) {
							intersect = true;
							return;
						}
						intersect=false;
					}
				}while(intersect);
			}
		}while(!successful);
		actors2.add(Dragon);
	}

	//5 for vertical and 8 for horizontal
	private void create_Walls() {
		double direction = Math.random()*100;
		double place = 0;
		if(direction <= 50) {
			boolean intersect = true;
			do{
				place = Math.random()*getHeight();
				for(int i = 0;i < actors.size();i++) {
					if(actors.elementAt(i).intersects(0, place, getWidth(), wall_h[0].getHeight())) {
						intersect = true;
						return;
					}
					intersect=false;
				}
			}while (intersect);
			for(int i = 0; i <= getWidth(); i= i+260) {
				create_Wall(1,i,place);
			}
		}else{
			boolean intersect = true;
			do{
				place = Math.random()*getWidth();
				for(int i = 0;i < actors.size();i++) {
					if(actors.elementAt(i).intersects(place, 0, wall_v[0].getWidth(), getHeight())) {
						intersect = true;
						return;
					}
					intersect=false;
				}
			}while (intersect);
			for(int i = 0; i <= getHeight(); i= i+260) {
				create_Wall(2,place,i);
			}
		}
	}

	private void create_Walls(int direction) {
		double place = 0;
		if(direction == 1) { //horizontal
			do{
				place = Math.random()*getHeight();
			}while (place>=player.y-150 && place<=player.y+300);

			for(int i = 0; i <= getWidth(); i= i+260) {
				create_Wall(direction,i,place);
			}
		}else if(direction == 2) { //vertical
			do{
				place = Math.random()*getWidth();
			}while (place>=player.x-150 && place<=player.x+300);

			for(int i = 0; i <= getHeight(); i= i+260) {
				create_Wall(direction,place,i);
			}
		}
	}

	private void create_Walls(int direction,double x_y) {
		if(direction == 1) {
			for(int i = 0; i <= getWidth(); i= i+260) {
				create_Wall(direction,i,x_y);
			}
		}else if(direction == 2) {
			for(int i = 0; i <= getHeight(); i= i+260) {
				create_Wall(direction,x_y,i);
			}
		}
	}

	private void create_Wall(int direction, double x, double y){
		BufferedImage[] wall = null;
		if(direction ==1) {
			wall = wall_h;
		}else if(direction ==2) {
			wall = wall_v;
		}
		Wall wall_1 = new Wall(wall, x, y, 1000, this);
		actors2.add(wall_1);
	}

	public void wall_destroyed(Wall s) {
		if (s.pics == wall_h) {
			walls_horizontal_destroyed ++;
			if(walls_horizontal_destroyed<=8 && !walls_horizon_triggered) {
				double random = Math.random()*100;
				double chance = (1/(9-walls_horizontal_destroyed))*100;
				if(random<=chance) {
					create_something();
					walls_horizon_triggered = true;
				}

			}
			if(walls_horizontal_destroyed ==8) {
				walls_horizontal_destroyed = 0;
				walls_horizon_triggered = false;
			}
		}else if(s.pics== wall_v) {
			walls_vertical_destroyed ++;
			if(walls_vertical_destroyed<=5 && !walls_vert_triggered) {
				double random = Math.random()*100;
				double chance = (1/(6-walls_vertical_destroyed))*100;
				if(random<=chance) {
					create_something();
					walls_vert_triggered = true;
				}

			}
			if(walls_vertical_destroyed==5) {
				walls_vertical_destroyed = 0;
				walls_vert_triggered = false;
			}
		}else {
			System.out.println("wall had neither of the two animations");
		}
	}

	public void create_something() {
		double random = Math.random()*100;
		if(random<5 && !temple_spawned) {
			create_Temple();
		}else if(random<30) {
			double x = Math.random()*getWidth();
			double y = Math.random()*getHeight();
			create_Cloud(x, y);
		}else if(random<65) {
			create_Dragon();
		}else {
			create_Walls();
		}
	}

	private BufferedImage[] loadPics(String path, int pics) {

		BufferedImage[] anim = new BufferedImage[pics];
		BufferedImage source = null;

		URL pic_url = getClass().getClassLoader().getResource(path);

		try {
			source = ImageIO.read(pic_url);
		} catch (IOException e) {
		}

		for (int x = 0; x < pics; x++) {
			anim[x] = source.getSubimage(x * source.getWidth() / pics, 0, source.getWidth() / pics, source.getHeight());
		}

		return anim;
	}

	private void checkKeys() {

		if (up) {
			speedy=speed;
		}

		if (down) {
			speedy=-speed;
		}

		if (right) {
			speedx=-speed;
		}

		if (left) {
			speedx=speed;
		}

		if (!up && !down) {
			speedy=0;
		}

		if (!left && !right) {
			speedx=0;
		}
	}

	private void doLogic() {

		Vector<Sprites> trash = new Vector<Sprites>();
		actors2.addAll(actors);
		for (Moving mov : actors) {
			mov.doLogic(delta,back);
			Sprites check = (Sprites) mov;
			if (check.remove) {
				trash.add(check);
			}
		}
		//System.out.println(actors.size());

		for(int i = 0;i < actors.size();i++) {
			for(int n = i+1; n<actors.size();n++) {
				Sprites s1 = actors.elementAt(i);
				Sprites s2 = actors.elementAt(n);

				s1.collidedWith(s2);
			}
		}

		if (trash.size() > 0) {
			actors.removeAll(trash);
			actors2.removeAll(trash);
			trash.clear();
		}

		actors.clear();
		actors.addAll(actors2);
		actors2.clear();

		if(actors.size()<13) {
			create_something();
		}

		if(gameover>0) {
			if(System.currentTimeMillis()-gameover>1000) {
				stopGame();
			}
		}
	}

	private void stopGame() {
		timer.stop();
		time = 0;
		//slib.stopLoopingSound();
		setStarted(false);
	}

	private void moveObjects() {
		for (Moving mov : actors) {
			mov.move(delta,speedx,speedy);
		}
	}

	public void move_screen(double pix_x, double pix_y) {
		for (Sprites mov : actors) {
			mov.free(pix_x, pix_y);
		}
	}


	public void free_angel(Sprites s) { //1 = left, 2 = right, 3 = up 4=down

		double shift_left =Math.abs(s.x+s.width-player.x);
		double shift_right=Math.abs(player.x+player.width-s.x);
		double shift_up = Math.abs(s.y+s.height-player.y);
		double shift_down = Math.abs(-s.y+player.y+player.height);

		if (min(shift_left,shift_right,shift_up,shift_down)) {
			for (Sprites mov : actors) {
				mov.free(-shift_left, 0);
			}
		}else if (min(shift_right,shift_left,shift_up,shift_down)) {
			for (Sprites mov : actors) {
				mov.free(shift_right, 0);
			}
		}

		if (min(shift_up,shift_right,shift_left,shift_down)) {
			for (Sprites mov : actors) {
				mov.free(0, -shift_up);
			}
		}else if (min(shift_down,shift_right,shift_up,shift_left)) {
			for (Sprites mov : actors) {
				mov.free(0, shift_down);
			}
		}
	}

	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
	}

	public int getSpeedx() {
		return speedx;
	}

	public int getSpeedy() {
		return speedy;
	}

	private static boolean min(double val, double... d) {

		for (double v : d) {
			if (val > v) {
				return false;
			}
		}

		return true;
	}

	public void remove_health(int damage,Sprites source) {
		if(source instanceof Hybrid_enemies) {
			if(dragon_cooldown) {
				health-=damage;
				dragon_cooldown=false;
			}
		}

		if (health<=0) {
			health = 0;
			game_state = 1;
			stopGame();
		}
	}

	public void keyPressed(KeyEvent e) {

		if(!isStarted()) {
			return;
		}else {
			if(player.pics == angel_fight1 || player.pics == angel_fight1_back) {
				return;
			}else {

				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					up = true;
				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					left = true;
					if(back == false) {
						back = true;
						player.setAnimation(angel_back,true);
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					down = true;
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					right = true;
					if(back==true) {
						back = false;
						player.setAnimation(angel,false);
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(player.pics==angel_fight1 || player.pics == angel_fight1_back || stamina<20) {
				return;
			}else {
				stamina -= 20;
				if (back == true) {
					player.setAnimation(angel_fight1_back,true);
				}else {
					player.setAnimation(angel_fight1,false);
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!isStarted()) {
				doInitializations();
				timer.restart();
				gameover = 0;
				points = 0;
				game_state = 0;
				health = 100;
				stamina = 100;
				temple_spawned = false;
				//slib.loopSound("heli");
				setStarted(true);
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (isStarted()) {
				game_state = 3;
				stopGame();
			} else {
				setStarted(false);
				System.exit(0);
			}
		}

	}

	public void keyTyped(KeyEvent e) {

	}

	public BufferedImage[] get_oposite(BufferedImage[] i) {
		if(i == small_black_back) {
			return small_black;
		}else if( i == small_black) {
			return small_black_back;
		}else if(i == big_black_back) {
			return big_black;
		}else if(i == big_black) {
			return big_black_back;
		}else if(i == big_yellow) {
			return big_yellow_back;
		}else if(i == big_yellow_back) {
			return big_yellow;
		}else if(i == big_orange) {
			return big_orange_back;
		}else if(i == big_orange_back) {
			return big_orange;
		}else if(i == old_green_back) {
			return old_green;
		}else if(i == old_green) {
			return old_green_back;
		}else if(i == violett_butterfly) {
			return violett_butterfly_back;
		}else if(i == violett_butterfly_back) {
			return violett_butterfly;
		}
		return i;
	}


	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		/*g2.translate(this.getWidth()/2, this.getHeight()/2);
		winkle = winkle +0.1;
		g2.rotate(winkle);*/


		frames++;
		g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		g2.setColor(Color.RED);
		g2.drawString("FPS: " + Long.toString(fps), 20, 10);
		g2.drawString("Time: " + Long.toString(time)+" sec", getWidth()-6*(Long.toString(time).length())-70, 10);
		g2.drawString("Points: " + Long.toString(points), getWidth()-6*(Long.toString(points).length())-60, 30);
		g2.drawString("Health: " + Long.toString(health), getWidth()-6*(Long.toString(health).length())-70, 50);
		g2.drawString("Stamina: " + Long.toString(stamina), getWidth()-6*(Long.toString(stamina).length())-70, 70);
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 100 );
		if(game_state ==1) {
			g2.drawImage(defeat_screen, 0, 0, getWidth(), getHeight(), this);
			g2.setColor(Color.BLUE);
			g2.setFont(stringFont);
			g2.drawString(Long.toString(points)+" Points",900,675);
		}else if(game_state ==2) {
			g2.drawImage(victory_screen, 0, 0, getWidth(), getHeight(), this);
			g2.setColor(Color.BLUE);
			g2.setFont(stringFont);
			g2.drawString(Long.toString(points)+" Points",900,675);
		}else if(game_state ==3) {
			g2.drawImage(start_screen, 0, 0, getWidth(), getHeight(), this);
		}

		if (!isStarted()) {
			return;
		}
		if (actors != null) {
			for (Drawing draw : actors) {
				draw.drawObjects(g2);
			}
		}
	}

	private ActionListener pointsTimer = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			points++;
			if(stamina <100) {
				stamina++;
			}
			if(!dragon_cooldown) {
				dragon_cooldown=true;
			}
		}
	};

}
