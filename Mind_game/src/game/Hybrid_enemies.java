package game;

import java.awt.image.BufferedImage;

public class Hybrid_enemies extends Sprites {

	boolean homming;
	boolean ranged;
	boolean melee;
	boolean back;
	boolean is_black;
	int damage;

	public Hybrid_enemies(BufferedImage[] i, double x, double y, long delay, GamePanel p,boolean homme,boolean range, boolean nah) {
		super(i, x, y, delay, p);
		homming = homme;
		ranged = range;
		melee = nah;
		damage = 5;
		if(pics == parent.small_black_back || pics == parent.big_black_back || pics == parent.small_black || pics == parent.big_black ) {
			is_black = true;
			if(x<=parent.player.x) {
				back = true;
				setAnimation(parent.get_oposite(i),true);
			}else {
				back = false;
			}
		}else {
			is_black = false;
			if(x<=parent.player.x) {
				back = false;
			}else {
				back = true;
				setAnimation(parent.get_oposite(i),true);
			}
		}
	}

	@Override
	public boolean collidedWith(Sprites s) {
		if(remove) {
			return false;
		}
		if(this.intersects(s)) {
			if(s instanceof Wall){
				if (dx!=0) {
					dx=0;
				}

				if (dy!=0) {
					dy=0;
				}
				return true;
			}
		}
		
		if(this.checkOpaqueColorCollisions(s)) {
			if(s instanceof Angel){
				if(s.pics==parent.angel_fight1 || s.pics==parent.angel_fight1_back) {
					remove = true;
					parent.points += 100;
					parent.create_something();
				}else {
					parent.remove_health(damage,this);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void doLogic(long delta, boolean ignore_this) {

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

		attack();
		//System.out.println("Copyright by René Viehhauser);
		
		if(!homming) {
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
	}
	
	public void setDamage(int dam) {
		damage=dam;
	}

	public void attack() {
		if(ranged && melee) {
			if((getX()<= parent.player.x-450 )||(getX()>= parent.player.x+750 )) {
				rangedAtack();
			}else {
				meleeAtack();
			}

		}else if(ranged) {
			rangedAtack();
		}else if(melee) {
			meleeAtack();
		}
	}

	public void meleeAtack() {
		if(homming) {
			if(this.checkOpaqueColorCollisions(parent.player)) {
				setDx(-parent.speedx);
				setDy(-parent.speedy);
			}else {
				if(getX()<= parent.player.x) {
					setDx(1.5*parent.speed);
					if(is_black) {
						if(back==false) {
							back = true;
							setAnimation(parent.get_oposite(pics),true);
						}
					}else {
						if(back==true) {
							back = false;
							setAnimation(parent.get_oposite(pics),false);
						}
					}
				}else if(getX()>= parent.player.x){
					setDx(-1.5*parent.speed);
					if(is_black) {
						if(back==true) {
							back = false;
							setAnimation(parent.get_oposite(pics),false);
						}
					}else {
						if(back==false) {
							back = true;
							setAnimation(parent.get_oposite(pics),true);
						}
					}
				}

				if(getY()<= parent.player.y){
					setDy(1.5*parent.speed);
				}else if(getY()>= parent.player.y) {
					setDy(-1.5*parent.speed);
				}
			}
		}else {
			if(is_black) {
				if(back) {
					setDx(parent.speed);
				}else {
					setDx(-parent.speed);
				}
			}else {
				if(back) {
					setDx(-parent.speed);
				}else {
					setDx(parent.speed);
				}
			}
		}
	}

	public void rangedAtack() {
		if(getX()<= parent.player.x) {
			if(is_black) {
				if(back==false) {
					back = true;
					setAnimation(parent.get_oposite(pics),true);
				}
			}else {
				if(back==true) {
					back = false;
					setAnimation(parent.get_oposite(pics),false);
				}
			}
		}else if(getX()>= parent.player.x){
			if(is_black) {
				if(back==true) {
					back = false;
					setAnimation(parent.get_oposite(pics),false);
				}
			}else {
				if(back==false) {
					back = true;
					setAnimation(parent.get_oposite(pics),true);
				}
			}
		}
		if(melee) {
			if(getX()<= parent.player.x) {
				setDx(0.5*parent.speed);
			}else if(getX()>= parent.player.x){
				setDx(-0.5*parent.speed);
			}

			if(getY()<= parent.player.y){
				setDy(parent.speed);
			}else if(getY()>= parent.player.y) {
				setDy(-parent.speed);
			}
		} else {
				setDx(-parent.speedx);
		}

	}

	private void computeAnimation() {

		currentpic++;

		if(currentpic>loop_to) {
			currentpic = loop_from;
		}
	}

	private void computeAnimation_back() {

		currentpic--;

		if(currentpic<0) {
			currentpic = loop_to;
		}
	}

}
