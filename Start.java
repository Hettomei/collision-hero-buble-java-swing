import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author tim
 *
 */
@SuppressWarnings("serial")
public class Start extends Canvas{

	/** The stragey that allows us to use accelerate page flipping */
	private BufferStrategy strategy;
	boolean gameRunning = true;
	long lastLoopTime = System.currentTimeMillis();
	long delta = 0;
	
	@SuppressWarnings("rawtypes")
	private ArrayList entities = new ArrayList();
	private Entity perso;
	/**
	 * @param args
	 */
	public static void main(String argv[]) {
		Start s = new Start();
		s.gameLoop();
	}
	
	public Start(){
		// create a frame to contain our game
		JFrame container = new JFrame("PacoBombo");
				
		// get hold the content of the frame and set up the 
		// resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
				
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,800,600);
		
		panel.add(this);
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
				
		// finally make the window visible, centered
		container.pack();
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setResizable(false);
		container.setLocationRelativeTo(null);
		container.setVisible(true);

		// request the focus so key events come to us

		requestFocus();

		// create the buffering strategy which will allow AWT

		// to manage our accelerated graphics

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		initEntities();

	}
	
	/**
	 * The main game loop. This loop is running during all game
	 * play as is responsible for the following activities:
	 * <p>
	 * - Working out the speed of the game loop to update moves
	 * - Moving the game entities
	 * - Drawing the screen contents (entities, text)
	 * - Updating game events
	 * - Checking Input
	 * <p>
	 */
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		// keep looping round til the game ends
		while (gameRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
		
			// cycle round drawing all the entities we have in the game
			for (int i=0;i<entities.size();i++) {
				Entity entity = (Entity) entities.get(i);
				
				entity.draw(g);
			}
			
			
			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();
			
			// finally pause for a bit. Note: this should run us at about
			// 100 fps but on windows this might vary each loop due to
			// a bad implementation of timer
			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initEntities() {
		// create the player ship and place it roughly in the center of the screen
		perso = new PersoEntity(this,"sprites/perso.png",400,300);
		System.out.println("Perso.png crŽŽ");
		entities.add(perso);
	}

}
