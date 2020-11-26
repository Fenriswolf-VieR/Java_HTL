package game;

import java.awt.image.BufferedImage;

public class Angel extends Sprites {
	
	public Angel(BufferedImage[] i, double x, double y, long delay, GamePanel p) {
		super(i,x,y,delay,p);
	}
	
	@Override
	public void doLogic(long delta,boolean back) {
		super.doLogic(delta,back);
		
		/*if(getX()<0) {
			setDx(0);
			setX(0);
		}
		
		if(getX()+getWidth()>parent.getWidth()) {
			setX(parent.getWidth()-getWidth());
			setDx(0);
			
		}
		
		if(getY()<0) {
			setY(0);
			setDy(0);
		}
		
		if(getY()+getHeight()>parent.getHeight()) {
			setY(parent.getHeight()-getHeight());
			setDy(0);
		}*/
	}
	
	public void move(long delta, int speedx, int speedy) {
		super.move(delta, 0, 0);
	}
	
	public boolean collidedWith(Sprites s) {
		if(remove) {
			return false;
		}
		if(this.checkOpaqueColorCollisions(s)) {
			if(remove) {
				parent.gameover = System.currentTimeMillis();
			}
			
			/*if(s instanceof Rocket) {
				parent.createExplosion((int)getX(), (int)getY());
				parent.createExplosion((int)s.getX(), (int)getY());
				remove = true;
				s.remove = true;
			}
			
			if(s instanceof Heli) {
				parent.createExplosion((int)getX(), (int)getY());
				parent.createExplosion((int)s.getX(), (int)getY());
				remove = true;
				s.remove = true;
			}*/
			return true;
		}
		return false;
	}

}
