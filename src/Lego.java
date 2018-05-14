/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Piece {
	//caractérisée par son nom et sa forme (simple ou composée)
	private String nom;
	
	public Piece(String n) {
		nom = n;
	}
	
	public String getNom() {
		return nom;
	}
	public String toString() {
		return(getNom());
	}
}

class Composant {
	//Constitué d'une pièce et de sa quantité
	//Un composant EST UNE piece ?
	private Piece piece;
	private int quantite;
	
	public Composant(Piece p, int q) {
		piece = p;
		quantite = q;
	}
	
	public Piece getPiece() {
		return piece;
	}
	public int getQuantite() {
		return quantite;
	}
}

class Simple extends Piece {
	//caractérisée par son orientation (string): gauche, droite, ou aucune
	private String orientation;
	
	public Simple(String n) {
		super(n);
		orientation = "aucune";
	}
	public Simple(String n, String o) {
		super(n);
		orientation = o;
	}
	
	public String getOrientation() {
		return orientation;
	}
	public String toString() {
		if (getOrientation().equals("aucune")) {
			return (getNom());
		} else {
			return (getNom()+" "+getOrientation());
		}
	}
}

class Composee extends Piece {
	//ArrayList de Piece (simple ou composée) ainsi que le nbre de pièce
	private int nombreMaxDePiece;
	private ArrayList<Piece> listeDePieces;
	
	public Composee(Piece p, int nbMax) {
		super(p.getNom());
		nombreMaxDePiece = nbMax;
		listeDePieces= new ArrayList<Piece>();
	}
	public Composee(Simple s, int nbMax) {
		super(s.getNom());
		nombreMaxDePiece = nbMax;
		listeDePieces= new ArrayList<Piece>();
	}
	public Composee(Composee c, int nbmax) {
		super(c.getNom());
		nombreMaxDePiece = nbmax;
		listeDePieces= new ArrayList<Piece>();
	}
	public Composee(String n, int nbMax) {
		super(n);
		nombreMaxDePiece = nbMax;
		listeDePieces= new ArrayList<Piece>();
	}
	
	public int taille() {
		return listeDePieces.size();
	}
	public int tailleMax() {
		return nombreMaxDePiece;
	}
	public void construire(Piece p) {
		if (taille()<tailleMax()) {
			listeDePieces.add(p);
		} else {
			System.out.println("Construction impossible");
		}	
	}
	public String toString() {
		String affichage;
		affichage = getNom() + " (";
		for (int i = 0; i < listeDePieces.size(); i++) {
			if (i<listeDePieces.size()-1) {
				affichage = affichage + listeDePieces.get(i) + ",";
			} else {
				affichage = affichage + listeDePieces.get(i) + ")";
			}
		}
		return affichage;
	}
}

class Construction {
	//ArrayList de composants caractérisée par le nbre max de composants
	private ArrayList<Composant>listeDeComposants;
	private int nombreMaxDeComposants;
	
	public Construction(int nbMax) {
		nombreMaxDeComposants = nbMax;
		listeDeComposants = new ArrayList<Composant>();
	}
	
	public int taille() {
		return listeDeComposants.size();
	}
	public int tailleMax() {
		return nombreMaxDeComposants;
	}
	public void ajouterComposant(Piece p, int nbMax) {
		Composant c = new Composant(p, nbMax);
		if (taille()<tailleMax()) {
			listeDeComposants.add(c);
		} else {
			System.out.println("Ajout de composant impossible");
		}
	}
	public String toString() {
		String affichage = "";
		for (int i = 0; i < listeDeComposants.size(); i++) {
			affichage = affichage + listeDeComposants.get(i).getPiece().toString() + " (quantité " 
						+ listeDeComposants.get(i).getQuantite() + ")" + "\n";
		}
		return affichage;
	}
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

class Lego {

    public static void main(String[] args) {
        // declare un jeu de construction de 10 pieces
        Construction maison = new Construction(10);

        // ce jeu a pour premier composant: 59 briques standard
        // une brique standard a par defaut "aucune" comme orientation
        maison.ajouterComposant(new Simple("brique standard"), 59);

        // declare une piece dont le nom est "porte", composee de 2 autres pieces
        Composee porte = new Composee("porte", 2);

        // cette piece composee est constituee de deux pieces simples:
        // un cadran de porte orient'e a gauche
        // un battant orient'e a gauche
        porte.construire(new Simple("cadran porte", "gauche"));
        porte.construire(new Simple("battant", "gauche"));

        // le jeu a pour second composant: 1 porte
        maison.ajouterComposant(porte, 1);

        // déclare une piece composee de 3 autres pieces dont le nom est "fenetre"
        Composee fenetre = new Composee("fenetre", 3);

        // cette piece composee est constitu'ee des trois pieces simples:
        // un cadran de fenetre (aucune orientation)
        // un volet orient'e a gauche
        // un volet orient'e a droite
        fenetre.construire(new Simple("cadran fenetre"));
        fenetre.construire(new Simple("volet", "gauche"));
        fenetre.construire(new Simple("volet", "droit"));

        // le jeu a pour troisieme composant: 2 fenetres
        maison.ajouterComposant(fenetre, 2);

        // affiche tous les composants composants (nom de la piece et quantit'e)
        // pour les pieces compos'ees : affiche aussi chaque piece les constituant
        System.out.println("Affichage du jeu de construction : ");
        System.out.println(maison);
    }
}
