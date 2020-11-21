import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

public class Game_panel extends JPanel implements Runnable, KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	boolean game_running = true;
	boolean started = false;
	boolean once = false;

	long delta = 0;
	long last = 0;
	long fps = 0;
	long gameover = 0;

	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	int speed = 70;

	Timer timer;
	BufferedImage[] rocket;
	BufferedImage background;
	BufferedImage[] explosion;
	JFrame frame;
	
	SoundLib slib;

	Sprite copter;
	Vector<Sprite> actors;

	public static void main(String[] args) {
		new Game_panel(1920, 1080);
	}

	public Game_panel(int w, int h) {
		this.setPreferredSize(new Dimension(w, h));
		//this.setBackground(Color.cyan);
		frame = new JFrame("The Heli-game");
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		Timer fpsHelper = new Timer(1000, fpsTimer);
		fpsHelper.restart();
		
		doInitializations();
	}

	private void doInitializations() {

		last = System.nanoTime();

		actors = new Vector<Sprite>();
		BufferedImage[] heli = this.loadPics("pics/heli.gif", 4);
		rocket = loadPics("pics/rocket.gif", 8);
		background = loadPics("pics/background.jpg", 1)[0];
		explosion = loadPics("pics/explosion.gif",5);
		copter = new Heli(heli, 400, 300, 100, this);
		actors.add(copter);
		
		slib = new SoundLib();
		slib.loadSound("bumm", "sound/boom.wav");
		slib.loadSound("rocket", "sound/rocket_start.wav");
		slib.loadSound("heli", "sound/heli.wav");
		
		if(isStarted()) {
			slib.loopSound("heli");
		}

		createClouds();

		timer = new Timer(3000, this);
		timer.restart();
		
		if (!once) {
			Thread t = new Thread(this);
			t.start();
		}
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

			repaint();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}

		}
		System.exit(0);
	}
	
	public void createExplosion (int x, int y) {
		Explosion ex = new Explosion(explosion,x,y,100,this);
		actors.add(ex);
		slib.playSound("bumm");
	}

	private void createClouds() {
		BufferedImage[] ci = this.loadPics("pics/cloud.gif", 1);

		for (int y = 10; y < getHeight(); y += 50) {
			int x = (int) (Math.random() * getWidth());
			Cloud cloud = new Cloud(ci, x, y, 1000, this);
			actors.add(cloud);
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
			copter.setDy(-speed);
		}

		if (down) {
			copter.setDy(speed);
		}

		if (right) {
			copter.setDx(speed);
		}

		if (left) {
			copter.setDx(-speed);
		}

		if (!up && !down) {
			copter.setDy(0);
		}

		if (!left && !right) {
			copter.setDx(0);
		}
	}

	private void doLogic() {

		Vector<Sprite> trash = new Vector<Sprite>();
		for (Movable mov : actors) {
			mov.doLogic(delta);
			Sprite check = (Sprite) mov;
			if (check.remove) {
				trash.add(check);
			}
		}
		// System.out.println(actors.size());
		
		for(int i = 0;i < actors.size();i++) {
			for(int n = i+1; n<actors.size();n++) {
				Sprite s1 = actors.elementAt(i);
				Sprite s2 = actors.elementAt(n);
				
				s1.collidedWith(s2);
			}
		}

		if (trash.size() > 0) {
				actors.removeAll(trash);
				trash.clear();
		}
		
		if(gameover>0) {
			if(System.currentTimeMillis()-gameover>3000) {
				stopGame();
			}
		}
	}
	
	private void stopGame() {
		timer.stop();
		slib.stopLoopingSound();
		setStarted(false);
	}

	private void moveObjects() {
		for (Movable mov : actors) {
			mov.move(delta);
		}
	}

	private void computeDelta() {
		try {
			delta = System.nanoTime() - last;
			last = System.nanoTime();
		} catch (ArithmeticException e) {
		}
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
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
				slib.loopSound("heli");
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

	private void createRocket() {

		int x = 0;
		int y = (int) (Math.random() * getHeight());
		int hori = (int) (Math.random() * 2);

		if (hori == 0) {
			x = -30;
		} else {
			x = getWidth() + 30;
		}

		Rocket rock = new Rocket(rocket, x, y, 100, this);
		if (x < 0) {
			rock.setHorizontalSpeed(200);
		} else {
			rock.setHorizontalSpeed(-200);
		}
		actors.add(rock);
		slib.playSound("rocket");
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	long frames = 0;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);


		frames++;
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

		g.setColor(Color.red);
		g.drawString("FPS: " + Long.toString(fps), 20, 10);

		if (!isStarted()) {
			return;
		}

		if (actors != null) {
			for (Drawable draw : actors) {
				draw.drawObjects(g);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (isStarted() && e.getSource().equals(timer)) {
			createRocket();
		}
		
		
		
	}
}
