package game;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Sprites extends Rectangle2D.Double implements Drawing, Moving {

	long delay;
	long animation = 0;
	protected double dx;
	protected double dy;
	int loop_from;
	int loop_to;
	boolean remove = false;
	GamePanel parent;
	BufferedImage[] pics;
	int currentpic = 0;

	public Sprites(BufferedImage[] i, double x, double y, long delay, GamePanel p) {
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
	
	public void setAnimation(BufferedImage[]i, boolean back) {
		pics = i;
		if(back) {
			loop_to= pics.length-1;
			loop_from = 0;
			currentpic= pics.length-1;
		}else {
			loop_from = 0;
			loop_to = pics.length-1;
			currentpic= 0;
		}
	}

	@Override
	public void drawObjects(Graphics g) {
		g.drawImage(pics[currentpic], (int) x, (int) y, null);
	}

	@Override
	public void doLogic(long delta, boolean back) {

		if(back) {
			animation += (delta/1000000);
			if (animation > delay) {
				animation = 0;
				computeAnimation_back();
			}
		}else {
			animation += (delta/1000000);
			if (animation > delay) {
				animation = 0;
				computeAnimation();
			}
		}

	}

	public boolean checkOpaqueColorCollisions(Sprites s) {

		Rectangle2D.Double cut = (Double) this.createIntersection(s);

		if((cut.width<1)||cut.height<1) {
			return false;
		}

		Rectangle2D.Double sub_me = getSubRec(this,cut);
		Rectangle2D.Double sub_him = getSubRec(s,cut);

		BufferedImage img_me = pics[currentpic].getSubimage((int)sub_me.x,(int) sub_me.y, (int)sub_me.width,(int)sub_me.height);
		BufferedImage img_him = s.pics[s.currentpic].getSubimage((int)sub_him.x,(int) sub_him.y, (int)sub_him.width,(int)sub_him.height);

		for(int i=0;i<img_me.getWidth();i++) {
			for(int n=0;n<img_him.getHeight();n++) {

				int rgb1 = img_me.getRGB(i, n);
				int rgb2 = img_him.getRGB(i, n);

				if(isOpaque(rgb1)&& isOpaque(rgb2)) {
					return true;
				}
			}
		}

		return false;
	}

	protected Rectangle2D.Double getSubRec(Rectangle2D.Double source, Rectangle2D.Double part){
		Rectangle2D.Double sub = new Rectangle2D.Double();

		if(source.x>part.x) {
			sub.x = 0;
		}else {
			sub.x = part.x - source.x;
		}

		if(source.y>part.y) {
			sub.y = 0;
		}else {
			sub.y = part.y - source.y;
		}

		sub.width = part.width;
		sub.height = part.height;

		return sub;
	}

	protected boolean isOpaque (int rgb) {
		int alpha = (rgb >> 24)& 0xff;
		// red = (rgb >> 16) & 0xff;
		//green = (rgb >> 8) & 0xff;
		//blue = (rgb ) & 0xff;

		if(alpha ==0) {
			return false;
		}
		return true;
	}
	
	public int getDamage(Hybrid_enemies s) {
		return s.damage;
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
	public void move(long delta,int speedx,int speedy) {
		double xm = dx+speedx;
		double ym = dy+speedy;

		if(xm!=0) {
			x += xm*(delta/1e9);
		}

		if(ym!=0) {
			y += ym*(delta/1e9);
		}
		//System.out.println("Copyright by René Viehhauser);
	}
	
	public void free(double pixel_x, double pixel_y) {
		x+=pixel_x;
		y+=pixel_y;
	}

	private void computeAnimation() {

		currentpic++;

		if(currentpic>loop_to) {
			currentpic = loop_from;
		}
	}

	private void computeAnimation_back() {

		currentpic--;

		if(currentpic<0) {
			currentpic = loop_to;
		}
	}

	public abstract boolean collidedWith(Sprites s);

	public void setLoop(int from, int to) {
		loop_from = from;
		loop_to = to;
		currentpic = from;
	}
	
	public void setDelay(long d) {
		this.delay = d;
	}

}
