import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Rocket extends Sprite{
	
	Rectangle2D.Double target;
	boolean locked = false;
	final int speed = 140;
	
	public Rocket(BufferedImage[] i, double x, double y, long delay, Game_panel p) {
		super(i,x,y,delay,p);
		
		if(getY()<parent.getHeight()/2) {
			setDy(speed);
		}else {
			setDy(-speed);
		}
	}
	
	@Override
	public void doLogic (long delta) {
		super.doLogic(delta);
		
		if(getDx()>0) {
			target = new Rectangle2D.Double(getX()+getWidth(), getY(),parent.getWidth()-getX(),getHeight());
		}else {
			target = new Rectangle2D.Double(0,getY(),getX(),getHeight());
		}
		
		if(!locked&&parent.copter.intersects(target)) {
			setDy(0);
			locked = true;
		}
		
		if(locked) {
			if(getY()<parent.copter.getY()){
				setDy(40);
			}
			if(getY()>parent.copter.getY()+parent.copter.getHeight()) {
				setDy(-40);
			}
		}
		
		if(getDx()>0 && getX()>parent.getWidth()) {
			remove = true;
		}
		
		if(getDx()<0 && getX()+getWidth()<0) {
			remove = true;
		}
	}
	
	public void setHorizontalSpeed (double d) {
		super.setDx(d);
		
		if(getDx()>0) {
			setLoop(4,7);
		}else {
			setLoop(0,3);
		}
	}

	public boolean collidedWith(Sprite s) {
		
		if(remove) {
			return false;
		}
		
		if(this.checkOpaqueColorCollisions(s)) {
			if(s instanceof Heli) {
				parent.createExplosion((int)getX(), (int)getY());
				parent.createExplosion((int)s.getX(), (int)s.getY());
				remove = true;
				s.remove = true;
			}
			
			if(s instanceof Rocket) {
				parent.createExplosion((int)getX(), (int)getY());
				parent.createExplosion((int)s.getX(), (int)s.getY());
				remove = true;
				s.remove = true;
			}
			
			return true;
		}
		return false;
	}
	
}
