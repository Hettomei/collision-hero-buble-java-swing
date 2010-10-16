/**
 * 
 */

/**
 * @author tim
 *
 */
public class CreerPolygone {

	private int x[];// = new int[5];
	private int y[];// = new int[5];
	private int pointeur;
	private int pixParTrait ;
	
	public CreerPolygone(int taille, int posX, int posY){
		this.x = new int[taille];
		this.y = new int[taille];
		this.pixParTrait = 10;
		this.pointeur = 0;
		this.x[0] = posX;
		this.y[0] = posY;
	}
	
	public CreerPolygone(int taille, int pixParTrait, int posX, int posY){
		this.x = new int[taille];
		this.y = new int[taille];
		this.pixParTrait = pixParTrait;
		this.pointeur = 0;
		this.x[0] = posX;
		this.y[0] = posY;
	}
	
	public void add(int x, int y){
		pointeur++;
		this.x[this.pointeur] = this.x[this.pointeur - 1] + x; 
		this.y[this.pointeur] = this.y[this.pointeur - 1] + y;
	}
	
	public void h(){ //haut
		this.add(0, pixParTrait);
	}
	public void b(){ //bas
		this.add(0, -pixParTrait);
	}
	public void g(){ //gauche
		this.add(-pixParTrait, 0);
	}
	public void d(){ //droite
		this.add(pixParTrait, 0);
	}
	public void hd(){
		this.add(pixParTrait, -pixParTrait);
	}
	
	public void bd(){
		this.add(pixParTrait, pixParTrait);
	}
	
	public void hg(){
		this.add(-pixParTrait, -pixParTrait);
	}
	
	public void bg(){
		this.add(-pixParTrait, pixParTrait);
	}
	
	public int[] getX(){
		return x;
	}
	public int[] getY(){
		return y;
	}
	
	public int getNbPoint(){
		return pointeur + 1;
	}
	
	public String toString(){
		String texte = new String ();
		texte += "---------------\n";
		for(int j = 0; j < this.x.length; j++){
			texte += j + " -> x : " + this.x[j] + "\t\t y : " + this.y[j] + "\n";
		}
		return texte + "nombres de point : " + getNbPoint() + "\n";
	}
}
