/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

class Patient {
	private double masse;
	private double hauteur;
	
	/*
	 *  une méthode init prenant en paramètre deux double, le premier pour
	 *  initialiser le poids du patient et le second pour initialiser sa taille;
	 *	ces données ne seront affectées aux attributs du patient que si elles sont
	 *	toutes les deux positives; dans le cas contraire, la taille et le poids du
	 *	patient seront tous deux initialisés à zéro ; pour simplifier, il n’y a pas
	 *	d’autre contrôle à faire sur ces données;
	 */
	
	public void init ( double m, double h) {
		if ((m < 0.0) || (h < 0.0)) {
			masse = 0.0;
			hauteur = 0.0;
		} else {
			masse = m;
			hauteur = h;
		}
	}
	
	/*
	 *une méthode afficher permettant d’afficher sur le terminal les caracté-
	 *ristiques du patient en respectant strictement le format suivant :
	 *Patient : <poids> kg pour <taille> m 
	 */
	public void afficher () {
		System.out.printf("Patient : %.1f kg pour %.1f m \n", masse , hauteur);
		//System.out.println("Patient : " + poids(masse) + "kg pour " + taille(hauteur) + " m ");
	}
	
	/*
	 * une méthode poids retournant le poids du patient;
	 */
	public double poids () {
		return masse;
	}
	
	/*
	 * une méthode taille retournant la taille du patient;
	 */
	public double taille () {
		return hauteur;
	}
	
	/*
	 *une méthode imc retournant l’IMC du patient : son poids divisé par le
	 *carré de sa taille; en cas de taille nulle, cette méthode retournera zéro. 
	 */
	public double imc () {
		if ( hauteur == 0) {
			return hauteur;
		} else {
			return (masse/(hauteur*hauteur));
		}
	}
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Imc {
    public static void main(String[] args) {

        Patient quidam = new Patient();
        quidam.init(74.5, 1.75);
        quidam.afficher();
        System.out.println("IMC : " + quidam.imc());
        quidam.init( -2.0, 4.5);
        quidam.afficher();
    }
}
