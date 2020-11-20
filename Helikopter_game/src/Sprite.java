import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Sprite extends Rectangle2D.Double implements Drawable, Movable {
	
	long delay;
	long animation = 0;
	protected double dx;
	protected double dy;
	int loop_from;
	int loop_to;
	boolean remove = false;
	Game_panel parent;
	BufferedImage[] pics;
	int currentpic = 0;
	
	public Sprite(BufferedImage[] i, double x, double y, long delay, Game_panel p) {
		pics = i;
		this.x = x;
		this.y = y;
		this.delay = delay;
		this.width = pics[0].getWidth();
		this.height = pics[0].getHeight();
		parent = p;
		loop_from = 0;
		loop_to = pics.length-1;
		
	}
	
	@Override
	public void drawObjects(Graphics g) {
		g.drawImage(pics[currentpic], (int) x, (int) y, null);
	}

	@Override
	public void doLogic(long delta) {
		
		animation += (delta/1000000);
		if (animation > delay) {
			animation = 0;
			computeAnimation();
		}
		
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double d) {
		dx = d;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double d) {
		dy = d;
	}
	
	public void setX(double i) {
		x = i;
	}
	
	public void setY(double i) {
		y = i;
	}

	@Override
	public void move(long delta) {
		
		if(dx!=0) {
			x += dx*(delta/1e9);
		}
		
		if(dy!=0) {
			y += dy*(delta/1e9);
		}
	}
	
	private void computeAnimation() {
		
		currentpic++;
		
		if(currentpic>loop_to) {
			currentpic = loop_from;
		}
	}
	
	public void setLoop(int from, int to) {
		loop_from = from;
		loop_to = to;
		currentpic = from;
	}

}
