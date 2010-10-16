import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author tim
 *
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame{

	/**
	 * 
	 */

	RemplirFenetre aa = new RemplirFenetre();
	
	public Fenetre(){

		this.setTitle("Rotation");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(aa);
 		this.setVisible(true);
		
		boolean passe = true;
		while(passe)
		{
			aa.repaint();
			try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
