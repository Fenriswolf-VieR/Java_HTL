package game;

import java.awt.Color;
import java.awt.Graphics;
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
		//System.out.println("Copyright by René Viehhauser);
	}

	@Override
	public void move(long delta, int speedx, int speedy) {
		super.move(delta, 0, 0);
	}

	@Override
	public void free(double pixel_x, double pixel_y) {
		return;
	}

	public boolean collidedWith(Sprites s) {
		if(remove) {
			return false;
		}

		if(this.intersects(s)) {
			if(s instanceof Wall){
				parent.free_angel(s);
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
				parent.points = parent.points +1000;
				parent.game_state =2;
				parent.gameover = 1;
			}

			if(s instanceof Hybrid_enemies){
				if(pics==parent.angel_fight1 || pics==parent.angel_fight1_back) {
					s.remove = true;
					parent.points +=100;
					parent.create_something();
				}else {
					parent.remove_health(((Hybrid_enemies) s).damage,s);
				}
			}
			return true;
		}
		return false;
	}

	private void computeAnimation() {

		currentpic++;
		if(pics==parent.angel_fight1) {
			move_fight_anim1(currentpic,false);
		}

		if(currentpic>loop_to) {
			if(pics==parent.angel_fight1) {
				setAnimation(parent.angel,false);
			}else {
				currentpic = loop_from;
			}
		}
	}

	private void computeAnimation_back() {

		currentpic--;
		if(pics==parent.angel_fight1_back) {
			move_fight_anim1(currentpic,true);
		}
		if(currentpic<0) {
			if(pics==parent.angel_fight1_back) {
				setAnimation(parent.angel_back,true);
			}else {
				currentpic = loop_to;
			}
		}
	}

	private void move_fight_anim1(int current,boolean back) {
		int picture;
		if(back) {
			picture = 15-current; //15 is lenght()-1 of the first fight anim
		}else {
			picture = current;
		}
		double pixel_x;
		double pixel_y;
		switch(picture){
		case 0:
			pixel_x = -20;
			pixel_y = -10;
			break;
		case 1:
			pixel_x = -40;
			pixel_y = 10;
			break;
		case 2:
			pixel_x = 0;
			pixel_y = 110;
			break;
		case 3:
			pixel_x = 0;
			pixel_y = 105;
			break;
		case 4:
			pixel_x = 30;
			pixel_y = 15;
			break;
		case 5:
			pixel_x = -20;
			pixel_y = -80;
			break;
		case 6:
			pixel_x = -120;
			pixel_y = -130;
			break;
		case 7:
			pixel_x = -55;
			pixel_y = -90;
			break;
		case 8:
			pixel_x = -35;
			pixel_y = -15;
			break;
		case 9:
			pixel_x = -30;
			pixel_y = -10;
			break;
		case 10:
			pixel_x = -35;
			pixel_y = 10;
			break;
		case 11:
			pixel_x = -10;
			pixel_y = -10;
			break;
		case 12:
			pixel_x = -5;
			pixel_y = 0;
			break;
		case 13:
			pixel_x = -35;
			pixel_y = -40;
			break;
		case 14:
			pixel_x = -30;
			pixel_y = 15;
			break;
		case 15:
			pixel_x = 0;
			pixel_y = 110;
			break;

		default:
			pixel_x = 0;
			pixel_y = 0;
			break;
		}
		parent.move_screen(pixel_x,pixel_y);


	}

	@Override
	public void drawObjects(Graphics g) {
		super.drawObjects(g);
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

}
