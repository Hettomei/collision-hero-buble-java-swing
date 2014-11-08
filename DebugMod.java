import java.awt.Color;
import java.awt.Graphics2D;


public class DebugMod {

  private long tempsEcoule = 0;
  private int fps = 0;
  private int fpsOK = 0;
  private boolean debug_mode = true;


  public void showFps(long delta, Graphics2D g, Entity perso){
    if (this.debug_mode == true){
      this.tempsEcoule += delta; //on prend sur 1 seconde
      this.fps++;
      g.setColor(Color.white);
      g.drawString(this.fpsOK + " FPS",750,10);

      if (this.tempsEcoule >= 1000){
        this.tempsEcoule = 0;
        this.fpsOK = fps;
        this.fps = 0;
      }
      g.drawRect(perso.getX(), perso.getY(), 20, 33);
    }
  }

  public void setdebug_mode(boolean debug){
    debug_mode = debug;
  }
  public void flipdebug_mode(){
    this.debug_mode = (this.debug_mode) ? false : true ;
  }
  public void S(String s){
    if (this.debug_mode == true){
      System.out.println(s);
    }
  }
}
