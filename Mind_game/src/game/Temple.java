package game;

import java.awt.image.BufferedImage;

public class Temple extends Sprites{

	public Temple(BufferedImage[] i, double x, double y, long delay, GamePanel p) {
		super(i, x, y, delay, p);
		
	}

	@Override
	public boolean collidedWith(Sprites s) {
		if(remove) {
			return false;
		}
		
		if(this.checkOpaqueColorCollisions(s)) {
			if(s instanceof Angel) {
				//remove = true;
				//s.remove = true;
				parent.points = parent.points +1000000000;
				parent.gameover=1;
				parent.game_state = 2;
			}
			
			return true;
		}
		return false;
	}
}
