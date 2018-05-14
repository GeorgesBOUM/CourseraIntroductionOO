/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Vehicule {
	private String nom;
	private double vitesseMaximale;
	private int poids;
	private int niveauDeCarburant;
	
	public Vehicule() {
		nom = "Anonyme";
		vitesseMaximale = 130.0;
		poids = 1000;
		niveauDeCarburant = 0;
	}
	public Vehicule(String n, double vmax, int p, int niveau) {
		nom = n;
		vitesseMaximale = vmax;
		poids = p;
		niveauDeCarburant = niveau;
	}
	public Vehicule(Vehicule v) {
		nom = v.getNom();
		vitesseMaximale = v.getVitesseMax();
		poids = v.getPoids();
		niveauDeCarburant = v.getCarburant();
	}
	
	public String getNom() {
		return nom;
	}
	public double getVitesseMax() {
		return vitesseMaximale;
	}
	public int getPoids() {
		return poids;
	}
	public int getCarburant() {
		return niveauDeCarburant;
	}
	public void setCarburant(int c) {
		do {
			if (c < 0) {
				c = 0;
			} else {
				niveauDeCarburant = c;
			}
		} while (c<0);
	}
	public String toString() {
		return(getNom()+" -> vitesse max = "+getVitesseMax()+ " km/h, poids = " +getPoids() + " kg");
	}
	private double performance() {
		return(getVitesseMax()/getPoids());
	}
	public boolean meilleur(Vehicule v) {
		if (this.performance() > v.performance()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean estDeuxRoues() {
		return false;
	}
}

class Voiture extends Vehicule {
	private String categorie;
	
	public Voiture(String c) {
		super();
		categorie = c;
	}
	public Voiture(String n, double vmax, int p, int carburant, String cat) {
		super(n, vmax, p, carburant);
		categorie = cat;
	}
	
	public String getCategorie() {
		return categorie;
	}
	public String toString() {
		return(super.toString()+", Voiture de "+getCategorie());
	}
}

class Moto extends Vehicule {
	private boolean sidecar;
	
	public Moto() {
		super();
		sidecar = false;
	}
	public Moto(String n, double vmax, int p, int carburant) {
		super(n, vmax, p, carburant);
		sidecar = false;
	}
	public Moto (String n, double vmax, int p, int carburant, boolean s) {
		super(n, vmax, p, carburant);
		sidecar = s;
	}
	
	public boolean getSideCar() {
		return sidecar;
	}
	public String affichageSidecar() {
		if(getSideCar()) {
			return(", Moto, avec sidecar");
		} else {
			return(", Moto");
		}
	}
	public String toString() {
		return(super.toString() + affichageSidecar());
	}
	public boolean estDeuxRoues() {
		if (getSideCar()) {
			return false;
		} else {
			return true;
		}
	}
}

abstract class Rallye {
	abstract public boolean check();
}

class GrandPrix extends Rallye{
	private ArrayList<Vehicule> listeVehicules;
	
	public GrandPrix() {
		listeVehicules = new ArrayList<Vehicule>();
	}
	
	public ArrayList<Vehicule> getListeVehicules() {
		return listeVehicules;
	}
	public void ajouter( Vehicule v) {
		getListeVehicules().add(v);
	}
	public boolean vehiculeSidecar(Vehicule v) {
		if (v.getClass() == Moto.class) {
			Moto m = (Moto)v;
			if (m.getSideCar()) {
				return true;
			} else {
				return false;
			} 
		} else {
			return false;
		}
	}
	public boolean checkSurDeuxvehicules(Vehicule v1, Vehicule v2) {
		if (v1.getClass() == v2.getClass()) {
			if (v1.estDeuxRoues() == v2.estDeuxRoues()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (vehiculeSidecar(v1) || vehiculeSidecar(v2)) {
				return true;
			} else {
				return false;
			}
		}
	}
	public boolean check() {
		boolean grandPrixPossible = false;
		int i = 0;
		if (getListeVehicules().isEmpty()) {
			grandPrixPossible = false;
			return grandPrixPossible;
		} else {
			if (getListeVehicules().size() < 2) {
				grandPrixPossible = true;
				return grandPrixPossible;
			} else {
				do {
					grandPrixPossible = checkSurDeuxvehicules(getListeVehicules().get(i), getListeVehicules().get(i+1));
					i++;
				} while ((grandPrixPossible) && (i < getListeVehicules().size() - 1));
				return grandPrixPossible;
			}
		}
		
	}
	public void run (int tours) {
		if (check()) {
			int toursEffectues = 0;
			while (!getListeVehicules().isEmpty() && toursEffectues <= tours) {
				for (int i = 0; i < getListeVehicules().size(); i++) {
					if (getListeVehicules().get(i).getCarburant() > 0) {
						getListeVehicules().get(i).setCarburant(getListeVehicules().get(i).getCarburant() - 1);
					} else {
						if (getListeVehicules().get(i).getCarburant() <= 0) {
							listeVehicules.remove(i);
						}
					}
				}
				toursEffectues++;
			}
			if (getListeVehicules().isEmpty()) {
				System.out.println("Elimination de tous les vehicules");
			} else {
				System.out.println("Le gagnant du grand prix est :");
				Vehicule vehiculePerformant = getListeVehicules().get(0);//new Vehicule(getListeVehicules().get(0));
				//boolean plusPerformant = true;//getListeVehicules().get(0).meilleur(vehiculePerformant);
				if (getListeVehicules().size() == 1) {
					System.out.println(getListeVehicules().get(0));;
				} else {
					for (int i = 1; i < getListeVehicules().size() - 1; i++) {
						if (!vehiculePerformant.meilleur(getListeVehicules().get(i))) {
							vehiculePerformant = getListeVehicules().get(i);
						}
					}
					System.out.println(vehiculePerformant);
				}
			}
		} else {
			System.out.println("Pas de Grand Prix");
		}
	}
}


/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}
