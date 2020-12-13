package game;

import java.awt.image.BufferedImage;

public class Wall extends Sprites{

	public Wall(BufferedImage[] i, double x, double y, long delay, GamePanel p) {
		super(i, x, y, delay, p);
	}

	@Override
	public boolean collidedWith(Sprites s) {
		if(remove) {
			return false;
		}
		//System.out.println("Copyright by René Viehhauser);
		if(this.intersects(s)) {
			if(s instanceof Angel){
				
				parent.free_angel(this);
				return true;
			}
			
			if(s instanceof Hybrid_enemies){
				if ((parent.up)||(parent.down)) {
					s.dx=0;
				}

				if ((parent.right)||(parent.left)) {
					s.dy=0;
				}
				return true;
			}
		}
		if(this.checkOpaqueColorCollisions(s)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void doLogic(long delta, boolean back) {
		super.doLogic(delta,false);
		
		if(getX()>parent.getWidth()) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		if((getX()+getWidth()<0)) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		if(getY()>parent.getHeight()) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		if((getY()+getHeight()<0)) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		
	}

}
