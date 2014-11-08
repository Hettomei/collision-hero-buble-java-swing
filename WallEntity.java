/**
 *
 */

/**
 * @author tim
 *
 */
public class WallEntity extends Entity{

  private Start game;
  private int debug_colision = 0;
  private static int debug_colision_total = 0;

  public WallEntity(Start game, String ref, int x, int y) {
    super(ref, x, y);
    this.game = game;
  }

  @Override
  public void collidedWith(Entity other) {
    debug_colision++;
    debug_colision_total++;

    game.debug.S("Bim : " + debug_colision + " total : " + debug_colision_total);
  }

}
