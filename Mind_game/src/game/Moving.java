package game;

public interface Moving {

	public void doLogic (long delta, boolean back);

	public void move(long delta, int speedx,int speedy);
	
}
