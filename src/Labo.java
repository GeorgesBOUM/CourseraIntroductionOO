class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    /**
     * PROGRAMME DE CREATION 
     * ET DE CLONAGE DE SOURIS
     * DE LABORATOIRE
     */
    
    private int poids;
    private String couleur;
    private int age;
    private int esperanceVie;
    private boolean clonee;
    
    public Souris(int p, String c, int a, int ev) {
		poids = p;
		couleur = c;
		age = a;
		esperanceVie = ev;
		clonee = false;
		System.out.println("Une nouvelle souris !");
	}
    
    public Souris(int p, String c, int a) {
		poids = p;
		couleur = c;
		age = a;
		esperanceVie = ESPERANCE_VIE_DEFAUT;
		clonee = false;
		System.out.println("Une nouvelle souris !");
	}
    
    public Souris(int p, String c) {
    		poids = p;
    		couleur = c;
    		age = 0;
    		esperanceVie = ESPERANCE_VIE_DEFAUT;
    		clonee = false;
    		System.out.println("Une nouvelle souris !");
	}
    
    public Souris(Souris s) {
		//s = new Souris(s.poids, s.couleur);
    		poids = s.poids;
    		couleur = s.couleur;
    		age = s.age;
    		esperanceVie = (int)((4.0/5.0)*s.esperanceVie);
		clonee = true;
		System.out.println("Clonage d'une souris !");
	}
    
    public String toString() {
    		if (clonee) {
			return("Une souris " +couleur+ ", clonée, de " +age+ " mois et pesant " +poids+ " grammes");
		} else {
			return("Une souris " +couleur+ " de " +age+ " mois et pesant " +poids+ " grammes");
		}
    }
    
    public void vieillir() {
		age = age + 1;
		if (clonee && age > (esperanceVie/2)) {
			couleur = "verte";
		}
	}
    
    public void evolue() {
		for(int i=age; i<esperanceVie; i++) {
			vieillir();
		}
	}

}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
