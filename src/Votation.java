import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme à partir d'ici.
 *******************************************/

/**
 * la classe Postulant 
 */
class Postulant {
	private String nom;
	private int nombreDeVotants;
	
	/* Les constructeurs */
	public Postulant(String n){
		nom = n;
		nombreDeVotants = 0;
	}
	public Postulant(String n, int nbre){
		nom = n;
		nombreDeVotants = nbre;
	}
	public Postulant(Postulant p) {
		nom = p.nom;
		nombreDeVotants = p.nombreDeVotants;
	}
	
	/* Les méthodes */
	public int elect() {
		nombreDeVotants++;
		return nombreDeVotants;
	}
	public void init() {
		nombreDeVotants = 0;
		//return nombreDeVotants;
	}
	public String getNom() {
		return nom;
	}
	public int getVotes() {
		return nombreDeVotants;
	}
}

/**
 * La classe Scrutin 
 */
class Scrutin {
	private ArrayList<Postulant> listeDesPostulants;
	private double nombreMaxDeVotants;
	private int dateDuScrutin;
	private boolean reinitialisation;
	private ArrayList<Vote> votes;
	
	/* Les constructeurs */
	public Scrutin(ArrayList<Postulant> liste, double nb,int jour) {
		listeDesPostulants = new ArrayList<Postulant>();
		listeDesPostulants.addAll(liste);
		nombreMaxDeVotants = nb;
		dateDuScrutin = jour;
		reinitialisation = true;
		votes = new ArrayList<Vote>();
		for (int i = 0; i < listeDesPostulants.size(); i++) {
			listeDesPostulants.get(i).init();
		}
		votes = new ArrayList<Vote>();
	}
	public Scrutin(ArrayList<Postulant> liste, double nb,int jour, boolean raz) {
		listeDesPostulants = new ArrayList<Postulant>();
		listeDesPostulants.addAll(liste);
		nombreMaxDeVotants = nb;
		dateDuScrutin = jour;
		reinitialisation = raz;
		if (this.reinitialisation) {
			for (int i = 0; i < listeDesPostulants.size(); i++) {
				listeDesPostulants.get(i).init();
			}
		}
		votes = new ArrayList<Vote>();
	}
	
	/* Les méthodes */
	public ArrayList<Vote> getListeDesVotes(){
		return votes;
	}
	public int getDateDuScrutin() {
		return dateDuScrutin;
	}
	public ArrayList<Postulant>getListeDesPostulants(){
		return listeDesPostulants;
	}
	public double getNombreMaxDeVotants() {
		return nombreMaxDeVotants;
	}
	public void compterVotes() {
		for (int i = 0; i < getListeDesVotes().size(); i++) {
			if (!getListeDesVotes().get(i).estInvalide()) {
				for (int j = 0; j < getListeDesPostulants().size(); j++) {
					if (getListeDesPostulants().get(j).getNom().equals(getListeDesVotes().get(i).getPostulant())) {
						getListeDesPostulants().get(j).elect();
					}
				}
			}
		}
	}
	public void simuler(double taux,int jourVote) {
		int nombreDeVotants = (int)(getNombreMaxDeVotants()*taux); //possible loss of precision ?
		int candNum ;
		for (int i = 0; i < nombreDeVotants; i++) {
			candNum = Utils.randomInt(getListeDesPostulants().size());
			if (i%3==0) {
				getListeDesVotes().add(new BulletinElectronique(getListeDesPostulants().get(candNum).getNom(), jourVote, getDateDuScrutin()));
			}
			if (i%2==0) {
				if (i%3==1) {
					getListeDesVotes().add(new BulletinPapier(getListeDesPostulants().get(candNum).getNom(), jourVote, getDateDuScrutin(), false));
				}
				if (i%3==2) {
					getListeDesVotes().add(new BulletinCourrier(getListeDesPostulants().get(candNum).getNom(), jourVote, getDateDuScrutin(), false));
				}
			} else {
				if (i%3==1) {
					getListeDesVotes().add(new BulletinPapier(getListeDesPostulants().get(candNum).getNom(), jourVote, getDateDuScrutin(), true));
				}
				if (i%3==2) {
					getListeDesVotes().add(new BulletinCourrier(getListeDesPostulants().get(candNum).getNom(), jourVote, getDateDuScrutin(), true));
				}
			}
			//getListeDesVotes().get(i-1);
		}
		for (int i = 0; i < getListeDesVotes().size(); i++) {
			System.out.println(getListeDesVotes().get(i));
		}
	}
	public int calculerVotants() {
		int totalVotants = 0;
		for (int i = 0; i < getListeDesPostulants().size(); i++) {
			totalVotants += getListeDesPostulants().get(i).getVotes();
		}
		//getListeDesVotes().get(0).g
		return totalVotants;
	}
	public String gagnant() {
		String gagnant = "";
		int voteGagnant = 0;
		for (int i = 0; i < getListeDesPostulants().size(); i++) {
			if (getListeDesPostulants().get(i).getVotes()>=voteGagnant) {
				voteGagnant = getListeDesPostulants().get(i).getVotes();
				gagnant = getListeDesPostulants().get(i).getNom();
			}
		}
		return gagnant;
	}
	public double tauxParticipation() {
		//lancer une ArithmeticException ??
		return((calculerVotants()/getNombreMaxDeVotants()));
		/*if (getNombreMaxDeVotants()<=0) {
			return 0.0;
		} else {
			return((calculerVotants()/getNombreMaxDeVotants()));
		}*/
	}
	public double pourcentagePostulant(int i) {
		//lancer une ArithmeticException ??
		return((getListeDesPostulants().get(i).getVotes()/(double)calculerVotants()));
		/*if (calculerVotants()<=0) {
			return 0.0;
		} else {
			return((getListeDesPostulants().get(i).getVotes()/calculerVotants()));
		} */
	}
	public void resultats() {
		
		if (getNombreMaxDeVotants() <= 0.0) {
			//return("Scrutin annulé, pas de votants");
			System.out.println("Scrutin annulé, pas de votants");
		} else {
			//String affichageFinal;
			System.out.format("Taux de participation -> %.1f", tauxParticipation()*100);
			System.out.println(" pour cent");
			System.out.println("Nombre effectif de votants -> "+ calculerVotants());
			System.out.println("Le chef choisi est -> "+ gagnant());
			System.out.println("Répartition des electeurs");
			for (int i = 0; i < getListeDesPostulants().size(); i++) {
				System.out.print (getListeDesPostulants().get(i).getNom());
				System.out.format(" -> %.1f", pourcentagePostulant(i)*100);
				System.out.println(" pour cent des électeurs");
			}
			System.out.println();
		}
	}
}

/**
 * La classe Vote
 */
abstract class Vote {
	private String postulant;
	private int dateVote;
	private int dateLimite;
	/* Les constructeurs */
	public Vote(String n, int d, int dl) {
		postulant = n;
		dateVote = d;
		dateLimite = dl;
	}
	
	/* Les méthodes */
	public abstract boolean estInvalide();
	public String getPostulant() {
		return postulant;
	}
	public int getDate() {
		return dateVote;
	}
	public int getDateLimite() {
		return dateLimite;
	}
	public String toString() {
		if (estInvalide()) {
			return(" pour "+getPostulant()+" -> invalide");
		} else {
			return(" pour "+getPostulant()+" -> valide");
		}
	}
}

/**
 * La classe BulletinElectronique 
 */
class BulletinElectronique extends Vote implements CheckBulletin{
	
	/* Les constructeurs */
	public BulletinElectronique(String n, int d, int dl) {
		super(n, d, dl);
	}
	
	/* Les méthodes */
	 public boolean checkDate() {
		 if (getDate() > getDateLimite()-2) {
				return false;
			} else {
				return true;
			}
	 }
	public boolean estInvalide() {
		if (!checkDate()) {
			return true;
		} else {
			return false;
		}
	}
	public String toString() {
		return("vote électronique" + super.toString());
	}
}

/**
 * La classe BulletinPapier 
 */
class BulletinPapier extends Vote {
	private boolean signature;
	
	/* Les constructeurs */
	public BulletinPapier(String n, int d, int dl, boolean s) {
		super(n, d, dl);
		signature = s;
	}
	
	/* Les méthodes */
	public boolean getSignature() {
		return signature;
	}
	public boolean estInvalide() {
		if (getSignature()) {
			return false;
		} else {
			return true;
		}
	}
	public String toString() {
		return("vote par bulletin papier" + super.toString());
	}
}

/**
 * La classe BulletinCourrier
 */
class BulletinCourrier extends BulletinPapier implements CheckBulletin{
	
	/* Les constructeurs */
	public BulletinCourrier(String n, int d, int dl, boolean s) {
		super(n, d, dl, s);
	}
	
	/* Les méthodes */
	public boolean checkDate() {
		if (getDate()> getDateLimite()) {
			return false;
		} else {
			return true;
		}
	}
	public boolean estInvalide() {
		if (!getSignature() || !checkDate()) {
			return true;
		} else {
			return false;
		}
	}
	public String toString() {
		return("envoi par courrier d'un " + super.toString());
	}
}

/**
 * L'interface CheckBulletin
 */
interface CheckBulletin{
	boolean checkDate() ;
	/*{
		if (v.getDate()> v.getDateLimite()) {
			return false;
		} else {
			return true;
		}
	}*/
}
//"vote électronique"
//System.out.println("Scrutin annulé, pas de votants");
//"Répartition des électeurs "
//" -> %.1f pour cent des électeurs\n"
/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
