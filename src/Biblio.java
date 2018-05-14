import java.util.ArrayList;

class Auteur {

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
	// Completer la classe Auteur ici
	private String nom;
	private boolean prix;
	
	public Auteur(String n, boolean p) {
		nom = n;
		prix = p;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean getPrix() {
		return prix;
	}
}

/**
 * la classe Oeuvre
 *
 */
class Oeuvre {
 	// Completer la classe Oeuvre ici
	private String titre;
	private Auteur auteur;
	private String langue;
	
	public Oeuvre(String t, Auteur a) {
		titre = t;
		auteur = a;
		langue = "francais";
	}
	
	public Oeuvre(String t, Auteur a, String l) {
		titre = t;
		auteur = a;
		langue = l;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public Auteur getAuteur() {
		return auteur;
	}
	
	public String getLangue() {
		return langue;
	}
	
	public String afficher() {
		return(getTitre()+", "+getAuteur().getNom()+", en "+getLangue());
	}
}

/**
 * la classe Exemplaire
 * attribut : oeuvre de type Oeuvre
 * méthodes getOeuvre et afficher
 */
class Exemplaire {
	private Oeuvre oeuvre;
	
	public Exemplaire(Oeuvre exemplairePrincipal) {
		//oeuvre = exemplairePrincipal;
		oeuvre = exemplairePrincipal;
		System.out.println("Nouvel exemplaire -> "+oeuvre.afficher());
	}
	
	public Exemplaire(Exemplaire exemplaireCopie) {
		//Exemplaire exemplaireCopie = new Exemplaire(exemplairePrincipal)
		oeuvre = exemplaireCopie.oeuvre;
		System.out.println("Copie d’un exemplaire de -> "+oeuvre.afficher());
	}
	
	public Oeuvre getOeuvre() {
		return oeuvre;
	}
	
	public void afficher() {
		System.out.println("Un exemplaire de "+getOeuvre().afficher());
		//System.out.println("Un exemplaire de ");
	}
}

/**
 * la classe Bibliothèque
 */
class Bibliotheque {
	private String nom;
	private ArrayList<Exemplaire> exemplaires = new ArrayList<Exemplaire>();
	
	public Bibliotheque(String n) {
		nom = n;
		System.out.println("La biblithèque "+nom+" est ouverte !");
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getNbExemplaires() {
		return exemplaires.size();
	}
	
	public ArrayList<Exemplaire> stocker(Oeuvre o, int nbExemplaires) {
		 Exemplaire e = new Exemplaire(o);
		for (int i = 0; i < nbExemplaires; i++) {
			exemplaires.add(e);
		}
		return exemplaires;
	}
	
	public ArrayList<Exemplaire> stocker(Oeuvre o) {
		Exemplaire e = new Exemplaire(o);
		exemplaires.add(e);
		return exemplaires;
	}
	//taf et pb: listerExemplaire doit renvoyer un ArrayList d'"Exemplaire"
	public ArrayList<Exemplaire> listerExemplaires(Exemplaire e, String lang) {
		ArrayList<Exemplaire>listeExemplaire = new ArrayList<Exemplaire>();
		for(int i=0; i<exemplaires.size(); i++) {
			if (exemplaires.get(i).getOeuvre().equals(e.getOeuvre()) && exemplaires.get(i).getOeuvre().getLangue().equalsIgnoreCase(lang)) {
				listeExemplaire.add(e);
			}
		}
		return listeExemplaire;
	}
	
	public ArrayList<Exemplaire> listerExemplaires(String lang) {
		ArrayList<Exemplaire>listeExemplaire = new ArrayList<Exemplaire>();
		for(int i=0; i<exemplaires.size(); i++) {
			if (exemplaires.get(i).getOeuvre().getLangue().equalsIgnoreCase(lang)) {
				listeExemplaire.add(exemplaires.get(i)); 
			}
		}
		return listeExemplaire;
	}
	
	public ArrayList<Exemplaire> listerExemplaires() {
		ArrayList<Exemplaire>listeExemplaire = new ArrayList<Exemplaire>();
		for(int i=0; i<exemplaires.size(); i++) {
			listeExemplaire.add(exemplaires.get(i));
		}
		return listeExemplaire;
	}
	
	public int compterExemplaires(Oeuvre o) {
		//Exemplaire e = new Exemplaire(o);
		//ArrayList<Exemplaire> listeExemplaireDuneOeuvre = new ArrayList<Exemplaire>();
		int nombreExemplaireDuneOeuvre = 0;
		for (int i = 0; i < exemplaires.size(); i++) {
			//System.out.println(exemplaires.get(i).getOeuvre());
			if (exemplaires.get(i).getOeuvre().equals(o)) {
				nombreExemplaireDuneOeuvre++;
			}
		}
		return nombreExemplaireDuneOeuvre;
	}
	
	public void afficherAuteur(boolean auteurPrime) {
		for (int i = 0; i < exemplaires.size(); i++) {
			if ( auteurPrime == true && exemplaires.get(i).getOeuvre().getAuteur().getPrix() == true) {
				System.out.println(exemplaires.get(i).getOeuvre().getAuteur().getNom());
			}
			if ( auteurPrime == false && exemplaires.get(i).getOeuvre().getAuteur().getPrix() == false) {
				System.out.println(exemplaires.get(i).getOeuvre().getAuteur().getNom());
			}
		}
	}
	
	public void afficherAuteur() {
		for (int i = 0; i < exemplaires.size(); i++) {
			if (exemplaires.get(i).getOeuvre().getAuteur().getPrix() == true) {
				System.out.println(exemplaires.get(i).getOeuvre().getAuteur().getNom());
			}
		}
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

public class Biblio {

    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();
        }
    }

    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);

        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");

        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);

        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}

