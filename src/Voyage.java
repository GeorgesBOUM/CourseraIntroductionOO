/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
//+ " à "
import java.util.ArrayList;

class OptionVoyage {
	private String nom;
	private double prixForfaitaire;
	
	public OptionVoyage(String n, double p) {
		nom = n;
		prixForfaitaire = p;
	}
	
	public String getNom() {
		return nom;
	}
	public double prix() {
		return prixForfaitaire;
	}
	public String toString() {
		return (getNom()+" -> "+prix()+" CHF");
	}
}

class Sejour extends OptionVoyage {
	private int nombreDeNuits;
	private double prixParNuit;
	
	public Sejour (String nomOption, double prixOption, int nbreNuits, double prixParNt) {
		super(nomOption, prixOption);
		nombreDeNuits = nbreNuits;
		prixParNuit = prixParNt;
	}
	
	public int getNbreDeNuits() {
		return nombreDeNuits;
	}
	public double getPrixParNuit() {
		return prixParNuit;
	}
	public double prix() {
		return(getNbreDeNuits()*getPrixParNuit()+super.prix());
	}
}

class Transport extends OptionVoyage{
	private boolean trajetLong;
	//private String modeTransport;
	public static final double TARIF_LONG = 1500.0;
	public final static double TARIF_BASE = 200.0;
	
	public Transport(String n, double p) {
		super(n, p);
		trajetLong = false;
	}
	public Transport(String n, double p, boolean t) {
		super(n, p);
		trajetLong = t;
	}
	
	public boolean getLongueurTrajet() {
		return trajetLong;
	}
	public double prix() {
		if (getLongueurTrajet()) {
			return(TARIF_LONG + super.prix());
		} else {
			return(TARIF_BASE + super.prix());
		}
	}
}

class KitVoyage {
	private String depart;
	private String arrivee;
	private ArrayList<OptionVoyage> kit;
	
	public KitVoyage(String d, String a) {
		depart = d;
		arrivee = a;
		kit = new ArrayList<OptionVoyage>();
	}
	
	public String getDepart() {
		return depart;
	}
	public String getArrivee() {
		return arrivee;
	}
	public double prix() {
		double p = 0.0;
		for (int i = 0; i < kit.size(); i++) {
			if (kit.get(i).getClass() == Sejour.class) {
				p = p + kit.get(i).prix();
			} else {
				if (kit.get(i).getClass() == Transport.class) {
					p = p + kit.get(i).prix();
				} else {
					p = p + kit.get(i).prix();
				}
			}
		}
		return p;
	}
	public String toString() {
		String affichage = "";
		for (int i = 0; i < kit.size(); i++) {
			if (kit.get(i).getClass() == Sejour.class) {
				affichage = affichage + " - " + kit.get(i).getNom() + " -> " + kit.get(i).prix() + " CHF\n";
			} else {
				if (kit.get(i).getClass() == Transport.class) {
					affichage = affichage + " - " + kit.get(i).getNom() + " -> " + kit.get(i).prix() + " CHF\n";
				} else {
					affichage = affichage + " - " + kit.get(i).getNom() + " -> " + kit.get(i).prix() + " CHF\n";
				}
			}
		}
		affichage = "Voyage de " +getDepart()+ " à "+getArrivee()+", avec pour options :\n"+affichage +"Prix total : " +prix()+ " CHF";
		return affichage;
	}
	public void ajouterOption(OptionVoyage o) {
		if (o != null) {
			kit.add(o);
		}
	}
	public void annuler() {
		kit.clear();
	}
	public int getNbOptions() {
		int nbOptions = 0;
		for (int i = 0; i < kit.size(); i++) {
			nbOptions++;
		}
		return nbOptions;
	}
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("Séjour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidée : London by night" , 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1


        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 50.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("Croisière", 1300.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2


        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}

