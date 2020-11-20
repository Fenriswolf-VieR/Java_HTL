import java.awt.image.BufferedImage;

public class Explosion extends Sprite {

	int old_pic = 0;

	public Explosion(BufferedImage[]i, double x, double y, long delay, Game_panel p) {
		super(i,x,y,delay,p);
	}
	
	@Override
	public void doLogic(long delta) {
		old_pic = currentpic;
		super.doLogic(delta);
		if(currentpic==0 && old_pic!=0) {
			remove = true;
		}
	}
	@Override
	public boolean collidedWith(Sprite s) {
		return false;
	}

}
