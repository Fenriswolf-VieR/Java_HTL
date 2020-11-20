import java.awt.image.BufferedImage;

public class Cloud extends Sprite {
	
	final int speed = 40;
	
	public Cloud(BufferedImage[] i, double x, double y, long delay, Game_panel p) {
		super(i,x,y,delay,p);
		
		if((int)(Math.random()*2)>1) {
			setDx(-speed);
		}else {
			setDx(speed);
		}
		
	}
	
	@Override
	public void doLogic(long delta) {
		super.doLogic(delta);
		
		if(getDx()>0 && getX()>parent.getWidth()) {
			setX(-getWidth());
		}
		
		if(getDx()<0 && (getX()+getWidth()<0)) {
			setX(parent.getWidth());
		}
	}

	@Override
	public boolean collidedWith(Sprite s) {
		// TODO Auto-generated method stub
		return false;
	}

}
