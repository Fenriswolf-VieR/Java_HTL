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
			parent.create_something();
		}
		
		if((getDx()+this.parent.getSpeedx())<0 && (getX()+getWidth()<0)) {
			remove = true;
			parent.create_something();
		}
		
		if((getDy()+this.parent.getSpeedy())>0 && getY()>parent.getHeight()) {
			remove = true;
			parent.create_something();
		}
		
		if((getDy()+this.parent.getSpeedy())<0 && (getY()+getHeight()<0)) {
			remove = true;
			parent.create_something();
		}
	}

	@Override
	public boolean collidedWith(Sprites s) {
		//System.out.println("Copyright by René Viehhauser);
		return false;
	}

}
