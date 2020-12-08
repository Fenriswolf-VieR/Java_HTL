package game;

import java.awt.image.BufferedImage;

public class Angel extends Sprites {

	public Angel(BufferedImage[] i, double x, double y, long delay, GamePanel p) {
		super(i,x,y,delay,p);
	}

	@Override
	public void doLogic(long delta,boolean back) {
		//super.doLogic(delta,back);

		if(back) {
			animation += (delta/1000000);
			if (animation > delay) {
				animation = 0;
				computeAnimation_back();
			}
		}else {
			animation += (delta/1000000);
			if (animation > delay) {
				animation = 0;
				computeAnimation();
			}
		}
	}

	public void move(long delta, int speedx, int speedy) {
		super.move(delta, 0, 0);
	}
	
	public void free(int pixel_x, int pixel_y) {
		return;
	}

	public boolean collidedWith(Sprites s) {
		if(remove) {
			return false;
		}
		
		if(this.intersects(s)) {
			if(s instanceof Wall){
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
		}
		if(this.checkOpaqueColorCollisions(s)) {
			if(remove) {
				parent.gameover = System.currentTimeMillis();
			}

			if(s instanceof Temple) {
				//remove = true;
				//s.remove = true;
				parent.points = parent.points +1000000000;
				parent.game_state =2;
				parent.gameover = 1;
			}
			
			if(s instanceof Hybrid_enemies){
				parent.health -= getDamage((Hybrid_enemies) s);
				parent.check_health();
			}

			/*if(s instanceof Wall){
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

	private void computeAnimation() {

		currentpic++;

		if(currentpic>loop_to) {
			if(pics==parent.angel_fight1) {
				parent.speed = 700;
				setAnimation(parent.angel,false);
			}else {
				currentpic = loop_from;
			}
		}
	}

	private void computeAnimation_back() {

		currentpic--;
		if(currentpic<0) {
			if(pics==parent.angel_fight1_back) {
				parent.speed = 700;
				setAnimation(parent.angel_back,true);
			}else {
				currentpic = loop_to;
			}
		}
	}

}
