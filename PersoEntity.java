/**
 * 
 */

/**
 * @author tim
 *
 */
public class PersoEntity extends Entity{

	private Start game;
	
	public PersoEntity(Start game, String ref, int x, int y) {
		super(ref, x, y);
		this.game = game;
	}

	/**
	 * Request that the ship move itself based on an elapsed ammount of
	 * time
	 * 
	 * @param delta The time that has elapsed since last move (ms)
	 */
	public void move(long delta) {
		
		if ((dy < 0) && (y < -10)) {
			//dy = 0;
			super.setY(590);
			
		}
		// if we're moving right and have reached the right hand side
		// of the screen, don't move
		if ((dy > 0) && (y > 590)) {
			//dy = 0;
			super.setY(-10);
		}
		
		// if we're moving left and have reached the left hand side
		// of the screen, don't move
		if ((dx < 0) && (x < -5)) {
			//dx = 0;
			super.setX(795);
		}
		// if we're moving right and have reached the right hand side
		// of the screen, don't move
		if ((dx > 0) && (x > 795)) {
			//dx = 0;
			super.setX(-5);
		}
		
		super.move(delta);
	}

	@Override
	public void collidedWith(Entity other) {
		if (other instanceof WallEntity) {
			
		}
	}

}
