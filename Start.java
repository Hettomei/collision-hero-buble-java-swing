import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	/** The list of entities that need to be removed from the game this loop */
	private ArrayList<Entity> removeList = new ArrayList<Entity>();
	
	private Entity perso;
	private double moveSpeed = 500;
	
	/** key press */
//	private boolean waitingForKeyPress = true;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean firePressed = false;
	
	private long fpsSeconde = 0;
	private int fps = 0;
	private int fpsOK = 0;
	
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

		addKeyListener(new KeyInputHandler());

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

		long delta;
		
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
			
			// cycle round asking each entity to move itself
			for (int i=1;i<entities.size();i++) {
				Entity entity = (Entity) entities.get(i);
				entity.draw(g);
			}
			

			// resolve the movement of the ship. First assume the ship 
			// isn't moving. If either cursor key is pressed then
			// update the movement appropriately
			perso.setHorizontalMovement(0);
			perso.setVerticalMovement(0);
			
			if ((upPressed) && (!downPressed)) {
				perso.setVerticalMovement(-moveSpeed);
			} else if ((downPressed) && (!upPressed)) {
				perso.setVerticalMovement(moveSpeed);
			}
			if ((leftPressed) && (!rightPressed)) {
				perso.setHorizontalMovement(-moveSpeed);
			} else if ((rightPressed) && (!leftPressed)) {
				perso.setHorizontalMovement(moveSpeed);
			}
			perso.move(delta);
			
			// mettre commentaire
			
			
			for (int i=1;i < entities.size();i++) {	
				Entity wall = (Entity) entities.get(i);
					
				if (perso.collidesWith(wall)) {
					perso.move(-delta);
					
					
					//on test quel mouvement pose probleme :
					perso.setHorizontalMovement(0);
					perso.setVerticalMovement(0);
					if ((upPressed) && (!downPressed)) {
						perso.setVerticalMovement(-moveSpeed);
						perso.move(delta);
						if (perso.collidesWith(wall)) {
							perso.move(-delta);
							perso.setVerticalMovement(0);
						}
						else{
							perso.move(-delta);
						}
					} else if ((downPressed) && (!upPressed)) {
						perso.setVerticalMovement(moveSpeed);
						perso.move(delta);
						if (perso.collidesWith(wall)) {
							perso.move(-delta);
							perso.setVerticalMovement(0);
						}else{
							perso.move(-delta);
						}
					}
					
					if ((leftPressed) && (!rightPressed)) {
						perso.setHorizontalMovement(-moveSpeed);
						perso.move(delta);
						if (perso.collidesWith(wall)) {
							perso.move(-delta);
							perso.setHorizontalMovement(0);
						}else{
							perso.move(-delta);
						}
					} else if ((rightPressed) && (!leftPressed)) {
						perso.setHorizontalMovement(moveSpeed);
						perso.move(delta);
						if (perso.collidesWith(wall)) {
							perso.move(-delta);
							perso.setHorizontalMovement(0);
						}else{
							perso.move(-delta);
						}
					}
					perso.move(delta);
					
					perso.collidedWith(wall);
					wall.collidedWith(perso);
					
				}
			}
			perso.draw(g);
			
			
			// remove any entity that has been marked for clear up
			entities.removeAll(removeList);
			removeList.clear();
			
			
			//this.debug_mode(delta, g);
			
			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();
			
			
			// finally pause for a bit. Note: this should run us at about
			// 100 fps but on windows this might vary each loop due to
			// a bad implementation of timer
			try { Thread.sleep(15); } catch (Exception e) {}
		}
	}
	
	private void debug_mode( long delta, Graphics2D g) {
		fpsSeconde += delta;
		fps++;
		g.setColor(Color.white);
		g.drawString(fpsOK + " FPS",750,10);
		if (fpsSeconde >= 1000){
			fpsSeconde = 0;
			fpsOK = fps;
			fps = 0;
		}
		g.drawRect(perso.getX(), perso.getY(), 20, 33);
	}

	private void initEntities() {
		// create the player
		perso = new PersoEntity(this,"sprites/perso.png",200,200);
		System.out.println("Perso.png crŽŽ");
		entities.add(perso);
		
		//create the wall
		for (int x=0;x<100;x++) {
		//	Entity mur = new WallEntity(this, "sprites/mur.png", 100+x*70, 300);
		//	entities.add(mur);
		//	System.out.println("mur.png crŽŽ");
			
			int randx = (int)(Math.random() * 800);
			int randy = (int)(Math.random() * 600);
			Entity mure = new WallEntity(this, "sprites/point.png", randx, randy);
			entities.add(mure);
			System.out.println("Entity mure = new WallEntity(this, \"sprites/mur.png\", " + randx + ", " + randy + ");");
			System.out.println("entities.add(mure);");
		}	
	}

	/**
	 * Start a fresh game, this should clear out any old data and
	 * create a new set.
	 */
	private void reStartGame() {
		// clear out any existing entities and intialise a new set
		entities.clear();
		initEntities();
		
		// blank out any keyboard settings we might currently have
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
	}
	
	private class KeyInputHandler extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = true;
			}
		} 
			
			
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
			// if we hit escape, then quit the game
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_R) {
				reStartGame();
			}
		}
	}

}
