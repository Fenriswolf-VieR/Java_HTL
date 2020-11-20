import java.awt.image.BufferedImage;

public class Heli extends Sprite {
	
	public Heli(BufferedImage[] i, double x, double y, long delay, Game_panel p) {
		super(i,x,y,delay,p);
	}
	
	@Override
	public void doLogic(long delta) {
		super.doLogic(delta);
		
		if(getX()<0) {
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
		}
	}

}