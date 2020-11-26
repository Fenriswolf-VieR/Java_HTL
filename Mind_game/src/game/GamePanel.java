package game;
import java.awt.Color;
import java.awt.Dimension;
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

	long delta = 0;
	long last = 0;
	long fps = 0;
	long time = 0;
	long gameover = 0;

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

	Timer timer;
	BufferedImage[] angel;
	BufferedImage[] angel_back;
	BufferedImage background;
	BufferedImage[] explosion;
	BufferedImage[] c1;
	BufferedImage[] c2;
	BufferedImage[] c3;
	BufferedImage[] c4;
	BufferedImage[] c5;

	JFrame frame;

	//SoundLib slib;

	Sprites player;
	Vector<Sprites> actors;
	Vector<Sprites> actors2;

	public static void main(String[] args) {
		new GamePanel(1920, 1080);
	}

	private boolean fullscreen = true ;

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
		//angel = this.loadPics("pics/angel_animations1.png", 9);
		angel = this.loadPics("pics/angel.png", 12);
		angel_back = this.loadPics("pics/angel_back.png", 12);
		c1 = this.loadPics("pics/clouds1.png", 1);
		c2 = this.loadPics("pics/clouds3.png", 1);
		c3 = this.loadPics("pics/clouds4.png", 1);
		c4 = this.loadPics("pics/clouds5.png", 1);
		c5 = this.loadPics("pics/clouds6.png", 1);
		//rocket = loadPics("pics/rocket.gif", 8);
		background = loadPics("pics/night_sky_background.png", 1)[0];
		/*explosion = loadPics("pics/explosion.gif",5);*/
		player = new Angel(angel, (this.getWidth()-angel[0].getWidth())/2, (this.getHeight()-angel[0].getHeight())/2, 100, this);
		//player = new Angel(angel, 0,0, 100, this);
		actors.add(player);

		createClouds();

		timer = new Timer(1000, spawnTimer);
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

			computeDelta();

			if (isStarted()) {
				checkKeys();
				doLogic();
				moveObjects();
			}

			paintImmediately(this.getBounds());;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}

		}
		System.exit(0);
	}

	private void createClouds() {
		for (int y = -(getHeight()/4); y <= getHeight(); y += (getHeight()/4)) {
			int x = (int) ((Math.random() * getWidth())-(Math.random()*getWidth()/2));
			create_Cloud(x,y);
		}
	}

	public void create_Cloud(double x, double y) {
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
		// System.out.println(actors.size());

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

		if(gameover>0) {
			if(System.currentTimeMillis()-gameover>3000) {
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

	private void computeDelta() {
		//try {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		//} catch (ArithmeticException e) {
		//}
	}

	public int getSpeedx() {
		return speedx;
	}

	public int getSpeedy() {
		return speedy;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
			back = true;
			player.setAnimation(angel_back);
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
			back = false;
			player.setAnimation(angel);
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

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!isStarted()) {
				doInitializations();
				timer.restart();
				//slib.loopSound("heli");
				setStarted(true);
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (isStarted()) {
				stopGame();
			} else {
				setStarted(false);
				System.exit(0);
			}
		}

	}

	public void keyTyped(KeyEvent e) {

	}



	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	long frames = 0;
	//double winkle = 0;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		/*g2.translate(this.getWidth()/2, this.getHeight()/2);
		winkle = winkle +0.1;
		g2.rotate(winkle);*/


		frames++;
		g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);

		g2.setColor(Color.red);
		g2.drawString("FPS: " + Long.toString(fps), 20, 10);
		g2.drawString("Points: " + Long.toString(time), getWidth()-60, 10);

		if (!isStarted()) {
			return;
		}

		if (actors != null) {
			for (Drawing draw : actors) {
				draw.drawObjects(g2);
			}
		}
	}

	private ActionListener spawnTimer = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			time++;

		}
	};
}
