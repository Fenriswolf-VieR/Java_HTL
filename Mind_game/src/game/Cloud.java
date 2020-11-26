package game;

import java.awt.image.BufferedImage;

public class Cloud extends Sprites {
	
	final int speed = 40;
	
	public Cloud(BufferedImage[] i, double x, double y, long delay, GamePanel p) {
		super(i,x,y,delay,p);
		
		if((Math.random()*100)>50) {
			setDx(-speed);
		}else {
			setDx(speed);
		}
		
	}
	
	@Override
	public void doLogic(long delta, boolean back) {
		super.doLogic(delta,false);
		
		if((getDx()+this.parent.getSpeedx())>0 && getX()>parent.getWidth()) {
			remove = true;
			double x = -getWidth();
			//setX(-getWidth());
			parent.create_Cloud(x,y);
		}
		
		if((getDx()+this.parent.getSpeedx())<0 && (getX()+getWidth()<0)) {
			remove = true;
			double x = parent.getWidth()+getWidth();
			//setX(parent.getWidth()+getWidth());
			parent.create_Cloud(x, y);
		}
		
		if((getDy()+this.parent.getSpeedy())>0 && getY()>parent.getHeight()) {
			remove = true;
			double y = -getHeight();
			//setY(-getHeight());
			parent.create_Cloud(x, y);
		}
		
		if((getDy()+this.parent.getSpeedy())<0 && (getY()+getHeight()<0)) {
			remove = true;
			double y = parent.getHeight()+getHeight();
			//setX(parent.getHeight()+getHeight());
			parent.create_Cloud(x, y);
		}
	}

	@Override
	public boolean collidedWith(Sprites s) {
		// TODO Auto-generated method stub
		return false;
	}

}
