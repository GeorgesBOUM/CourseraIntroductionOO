import java.util.Scanner;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

class Tirelire {
	private double montant;
	/*
	 *une méthode getMontant retournant le montant de la tirelire;
	 */
	public double getMontant () {
		return montant;
	}
	
	/*
	 * une méthode afficher affichant les données de la tirelire sous le format
	 * suivant :
		— Vous etes sans le sou.
			si la tirelire ne contient pas d’argent (les accents on été délibérément
			omis pour éviter les problèmes d’encodage)
		— Vous avez : <montant> euros dans votre tirelire.
			dans le cas contraire (où <montant> est le montant de la tirelire, affiché
			sans formattage particulier)
	 */
	public void afficher () {
		if (montant == 0) {
			System.out.println("Vous etes sans le sou.");
		} else {
			System.out.println("Vous avez : " +montant+ " euros dans votre tirelire.");
		}
	}
	
	/*
	 * une méthode secouer affichant sur le terminal le message Bing bing,
	suivi d’un saut de ligne, dans le cas où la tirelire contient de l’argent, et
	qui n’affiche rien sinon;
	 */
	public void secouer () {
		if (montant != 0) {
			System.out.println("Bing bing");
		}
	}
	
	/*
	 *la méthode remplir mettant un montant donné en paramètre (double)
	dans la tirelire. Seuls les montant positifs seront acceptés (dans le cas
	contraire on ne fait rien);
	 */
	public void remplir(double unMontant) {
		if (unMontant > 0) {
			montant = unMontant;
		}
	}
	
	/*
	 *une méthode vider (re)intialisant le montant de la tirelire à zéro;
	 */
	public void vider() {
		montant = 0;
	}
	
	/*
	 *une méthode puiser permettant de puiser dans la tirelire un montant
	 donné en paramètre. Si le montant est négatif il sera ignoré. Si le montant
	 en argument est plus grand que le montant disponible, la tirelire est alors
	 vidée. La méthode puiser ne retourne rien.
	 */
	public void puiser(double unRetrait) {
		if ((unRetrait > 0) && (unRetrait > montant)) {
			montant = 0;
		}
		if ((unRetrait > 0) && (unRetrait < montant)) {
			montant = montant - unRetrait;
		}
	}
	
	/*
	 *une méthode calculerSolde qui retourne la différence entre le montant
	de la tirelire et le budget que l’on souhaite dépenser (un double). Si
	le budget est négatif (ou nul), la méthode calculerSolde doit retourner
	le montant de la tirelire. 
	 */
	public double calculerSolde(double uneDepense) {
		double contenuTirelire = montant;
		if (uneDepense <= 0) {
			return contenuTirelire;
		} else {
			contenuTirelire = contenuTirelire - uneDepense;
			return contenuTirelire;
		}
	}
	
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
public class TestTirelire {

    public static void main(String[] args) {
        Tirelire piggy = new Tirelire();

        piggy.vider();
        piggy.secouer();
        piggy.afficher();

        piggy.puiser(20.0);
        piggy.secouer();
        piggy.afficher();

        piggy.remplir(550.0);
        piggy.secouer();
        piggy.afficher();

        piggy.puiser(10.0);
        piggy.puiser(5.0);
        piggy.afficher();

        System.out.println();

        // le budget de vos vacances de rêves.
        double budget;
        Scanner clavier = new Scanner(System.in);

        System.out.println("Donnez le budget de vos vacances : ");
        budget = clavier.nextDouble();

        // ce qui resterait dans la tirelire après les
        // vacances
        double solde = piggy.calculerSolde(budget);

        if (solde >= 0) {
            System.out.println("Vous etes assez riche pour partir en vacances !");
            System.out.print(" il vous restera " + solde + " euros");
            System.out.print(" a la rentree \n");
            piggy.puiser(budget);
        }

        else {
            System.out.print("Il vous manque " + (-solde) + " euros");
            System.out.print(" pour partir en vacances !\n");
        }
        clavier.close();
    }
}
