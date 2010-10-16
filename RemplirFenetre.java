import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author tim
 *
 */
@SuppressWarnings("serial")
public class RemplirFenetre extends JPanel {
	
	CreerPolygone cp;
	Polygon p ;
	double theta = -0.04;
	
	public RemplirFenetre(){
		int nb_de_point = 8;
		cp = new CreerPolygone(nb_de_point, 0, 0);

		cp.add(20,0);
		cp.add(0,-10);
		cp.add(15,15);
		cp.add(-15,15);
		cp.add(0,-10);
		cp.add(-20,0);
		cp.add(0,-10);
		System.out.println(cp);
		
		this.p = new Polygon(cp.getX(), cp.getY(), cp.getNbPoint());
		
		
		//creation d'une chaise rapide
		CreerPolygone cpp = new CreerPolygone(30, 40, -100, -100);
		cpp.h();cpp.d();cpp.d();cpp.d();cpp.h();
		cpp.h();cpp.h();cpp.d();cpp.b();cpp.b();
		cpp.b();cpp.b();cpp.b();cpp.b();cpp.g();
		cpp.h();cpp.h();cpp.h();cpp.g();cpp.g();
		cpp.b();cpp.b();cpp.b();cpp.g();
		
		cpp = new CreerPolygone(10, 100, 0, 0);
		cpp.d();cpp.b();cpp.g();cpp.h();//carree simple
		
		cpp = new CreerPolygone(10, 20, 30, 30);
		cpp.g();cpp.hd();cpp.bd();cpp.bg();cpp.hg(); //carre par diagonale
		
		this.p = new Polygon(cpp.getX(), cpp.getY(), cpp.getNbPoint());
		
		//System.out.println(this.p.getPathIterator(null));
		
		System.out.println(cpp + "\n" + this.p.getBounds());
	}
	
	public void paintComponent(Graphics g){
		theta += 0.12;
				
		Graphics2D g2 = (Graphics2D)g;
//		g2.translate(this.getWidth() / 2,this.getHeight() /2);

		g2.clearRect((int)g2.getClipBounds().getX(),
					(int)g2.getClipBounds().getY(),
					(int)g2.getClipBounds().getHeight(),
					(int)g2.getClipBounds().getWidth());
		
		g2.translate(200, 200);

		g2.rotate(theta);
		g2.draw(this.p);
		
	}
}
