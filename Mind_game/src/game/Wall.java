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
		if(this.intersects(s)) {
			if(s instanceof Angel){
				if (parent.up) {
					parent.speedy=parent.speed*2;
					parent.stuck_up=true;
				}

				if (parent.down) {
					parent.speedy=-parent.speed*2;
					parent.stuck_down=true;
				}

				if (parent.right) {
					parent.speedx=-parent.speed*2;
					parent.stuck_down=true;
				}

				if (parent.left) {
					parent.speedx=parent.speed*2;
					parent.stuck_left=true;
				}
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
			/*if(s instanceof Angel){
				if (parent.up) {
					parent.speedy=parent.speed*2;
				}

				if (parent.down) {
					parent.speedy=-parent.speed*2;
				}

				if (parent.right) {
					parent.speedx=-parent.speed*2;
				}

				if (parent.left) {
					parent.speedx=parent.speed*2;
				}
			}*/
			return true;
		}
		return false;
	}
	
	@Override
	public void doLogic(long delta, boolean back) {
		super.doLogic(delta,false);
		
		if((getDx()+this.parent.getSpeedx())>0 && getX()>parent.getWidth()&& pics == parent.wall_h) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		if((getDx()+this.parent.getSpeedx())<0 && (getX()+getWidth()<0)&& pics == parent.wall_h) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		if((getDy()+this.parent.getSpeedy())>0 && getY()>parent.getHeight()&& pics == parent.wall_v) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		if((getDy()+this.parent.getSpeedy())<0 && (getY()+getHeight()<0) && pics == parent.wall_v) {
			remove = true;
			parent.wall_destroyed(this);
		}
		
		
	}

}
