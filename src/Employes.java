/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

import java.util.*;

/**
 * La classe PrimeChereException
 * pour les demandes de primes > 2% du revenuAnnuel*/
class PrimeChereException extends Exception{
	public PrimeChereException() {
		System.out.println("Trop cher!");
	}
}

/**
 * La classe Employe
 * */
class Employe {
	private final String nom;
	private double revenuMensuel;
	private int tauxOccupation;
	private double prime;
	
	/* Les constructeurs */
	public Employe(String nom, double revenu) {
		this.nom = nom;
		revenuMensuel = revenu;
		tauxOccupation = 100;
		prime = 0.0;
		System.out.print("Nous avons un nouvel employé : "+getNom()+", ");
	}
	public Employe(String nom, double revenu, int taux) {
		this.nom = nom;
		revenuMensuel = revenu;
		if (taux < 10) {
			tauxOccupation = 10;
		} else {
			if (taux > 100) {
				tauxOccupation = 100;
			} else {
				tauxOccupation = taux;
			}
		}
		prime = 0.0;
		System.out.print("Nous avons un nouvel employé : "+getNom()+", ");
	}
	/**
	 * @return nom
	 * */
	public String getNom() {
		return nom;
	}
	/**
	 * @return revenuMensuel
	 * */
	public double getRevenu() {
		return revenuMensuel;
	}
	/**
	 * @return tauxOccupation*/
	public int getTaux() {
		return tauxOccupation;
	}
	/**
	 * @return prime*/
	public double getPrime() {
		return prime;
	}
	/**
	 * @return revenuAnnuelDeBase
	 * */
	public double revenuAnnuel() {
		double revenuAnnuelDeBase = (getRevenu()*12*getTaux()/100)+getPrime();
		return(revenuAnnuelDeBase);
	}
	/**
	 * Affichage des employés, de leurs revenus et primes
	 * @return toDisplay
	 * */
	public String toString() {
		String affichageRevenu = String.format("%.2f", revenuAnnuel());
		String affichagePrime = String.format("%.2f",getPrime());
		String toDisplay = getNom()+" : \n"+"  Taux d'occupation : "+getTaux()+"%. "
				+ "Salaire annuel : "+affichageRevenu+" francs";
		if (getPrime()>0) {
			toDisplay = toDisplay + ", Prime : "+affichagePrime+".\n";
		} else {
			toDisplay = toDisplay + ".\n";
		}
		return toDisplay;
	}
	/**
	 * méthode pour la demande de prime
	 * */
	public void demandePrime() {
		Scanner clavier = new Scanner(System.in);
		double laPrime;
		int nombreDeDemande = 0;
		do {
			nombreDeDemande++;
			System.out.println("Montant de la prime souhaitée par "+getNom()+" ?");
			try {
				laPrime = clavier.nextDouble();
				if (laPrime > (2*revenuAnnuel())/100) {
					throw new PrimeChereException();
				}
				prime = laPrime;
				return;
			} catch (PrimeChereException e) {
				
			} catch (InputMismatchException e) {
				System.out.println("Vous devez introduire un nombre!");
				clavier.nextLine();
			}
		} while (nombreDeDemande<5);
	}
}
/**
 * La classe Manager
 * */
class Manager extends Employe {
	private int nombreDeJoursVoyage;
	private int nombreDeClients;
	public static final int FACTEUR_GAIN_CLIENT = 500;
	public static final int FACTEUR_GAIN_VOYAGE = 100;
	
	/* Les constructeurs */
	public Manager(String nom, double revenu, int voyage, int clients) {
		super(nom, revenu);
		nombreDeJoursVoyage = voyage;
		nombreDeClients = clients;
		System.out.println("c'est un manager.");
	}
	public Manager(String nom, double revenu, int voyage, int clients, int taux) {
		super(nom, revenu, taux);
		nombreDeJoursVoyage = voyage;
		nombreDeClients = clients;
		System.out.println("c'est un manager.");
	}
	/**
	 * @return nombreDeJoursDeVoyage
	 * */
	public int getVoyage() {
		return nombreDeJoursVoyage;
	}
	/**
	 * @return nombreDeClients
	 * */
	public int getClient() {
		return nombreDeClients;
	}
	/**
	 * @return 
	 * */
	public double revenuAnnuel() {
		double revenuAnnuelManager = super.revenuAnnuel()+getClient()*FACTEUR_GAIN_CLIENT+getVoyage()*FACTEUR_GAIN_VOYAGE;
		return(revenuAnnuelManager);
	}
	/**
	 * @return affichageVoyagesEtClients
	 * */
	public String toString() {
		String affichageVoyagesEtClients = super.toString()+"  A voyagé "+getVoyage()+" jours et apporté "+getClient()+" nouveaux clients.\n";
		return(affichageVoyagesEtClients);
	}
}
/**
 * La classe Testeur
 * */
class Testeur extends Employe {
	private int nombreErreursCorrigees;
	public static final int FACTEUR_GAIN_ERREURS = 10;
	
	/* Les constructeurs */
	public Testeur(String nom,double revenu,int erreurs) {
		super(nom, revenu);
		nombreErreursCorrigees = erreurs;
		System.out.println("c'est un testeur.");
	}
	public Testeur(String nom,double revenu,int erreurs,int taux) {
		super(nom, revenu, taux);
		nombreErreursCorrigees = erreurs;
		System.out.println("c'est un testeur.");
	}
	/**
	 * @return nombreErreusCorrigees
	 */
	public int getErreurs() {
		return nombreErreursCorrigees;
	}
	/**
	 * @return revenuAnnuelTesteur*/
	public double revenuAnnuel() {
		double revenuAnnuelTesteur = super.revenuAnnuel()+FACTEUR_GAIN_ERREURS*getErreurs();
		return(revenuAnnuelTesteur);
	}
	/**
	 * @return affichageNombreErreursCorrigees
	 **/
	public String toString() {
		String affichageNombreErreursCorrigees = super.toString()+ "  A corrigé "+getErreurs()+" erreurs.\n";
		return(affichageNombreErreursCorrigees);
	}
}
/**
 * La classe Programmeur
 * */
class Programmeur extends Employe {
	private int nombreProjetsAcheves;
	public static final int FACTEUR_GAIN_PROJETS = 200;
	
	/* Les constructeurs */
	public Programmeur(String nom,double revenu,int projets) {
		super(nom, revenu);
		nombreProjetsAcheves = projets;
		System.out.println("c'est un programmeur.");
	}
	public Programmeur(String nom,double revenu,int projets,int taux) {
		super(nom, revenu, taux);
		nombreProjetsAcheves = projets;
		System.out.println("c'est un programmeur.");
	}
	
	/**
	 * @return nombreProjetsAcheves
	 * */
	public int getProjets() {
		return nombreProjetsAcheves;
	}
	/**
	 * @return revenuAnnuelProgrammeur
	 * */
	public double revenuAnnuel() {
		double revenuAnnuelProgrammeur = super.revenuAnnuel()+FACTEUR_GAIN_PROJETS*getProjets();
		return(revenuAnnuelProgrammeur);
	}
	/**
	 * @return affichageNombreProjetsMenes
	 * */
	public String toString() {
		String affichageNombreProjetsMenes = super.toString()+"  A mené à bien "+getProjets()+" projets\n";
		return(affichageNombreProjetsMenes);
	}
}

/**
 * TAF : revoir les constructeurs et les différentes fonctions pour s'assurer d'un affichage correct.
 * Cf. la SandBox;
 */


/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

