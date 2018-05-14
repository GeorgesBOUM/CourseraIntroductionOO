import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code;
    private int anneeEmission;
    private String paysOrigine;
    private double valeur;
    
    public Timbre(){
    		this(CODE_DEFAUT, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }
    public Timbre(String c) {
    		this(c, ANNEE_COURANTE,PAYS_DEFAUT,VALEUR_TIMBRE_DEFAUT);
    }
    public Timbre(String c, int a) {
    		this(c, a, PAYS_DEFAUT,VALEUR_TIMBRE_DEFAUT);
    }
    public Timbre(String c, int a, String pays) {
    		this(c, a, pays, VALEUR_TIMBRE_DEFAUT);
    }
    public Timbre(String c, int a, String pays, double v) {
    		code = c;
    		anneeEmission = a;
    		paysOrigine = pays;
    		valeur = v;
    }
    
    public String getCode() {
    		return code;
    }
    public int getAnnee() {
    		return anneeEmission;
    }
    public String getPays() {
		return paysOrigine;
	}
    public double getValeurFaciale() {
		return valeur;
	}
    
    public double vente () {
    		if (getAnnee()<5) {
    			return getValeurFaciale();
    		} else {
			return (getValeurFaciale()*age()*2.5);	
		}
    }
    public int age() {
    		return ANNEE_COURANTE-getAnnee();
    }
    public String toString() {
		return("Timbre de code "+getCode()+" datant de "+getAnnee()+" (provenance "+getPays()+") ayant pour valeur faciale "+getValeurFaciale()+" francs");
	}
}

class Rare extends Timbre {
	private int nombreExemplaires;
	
	public Rare(int nbExemplaire) {
		nombreExemplaires = nbExemplaire;
	}
	public Rare(String c, int nbExemplaire) {
		super(c);
		nombreExemplaires = nbExemplaire;
	}
	public Rare(String c, int a, int nbExemplaire) {
		super(c, a);
		nombreExemplaires = nbExemplaire;
	}
	public Rare(String c, int a, String p, int nbExemplaire) {
		super(c, a, p);
		nombreExemplaires = nbExemplaire;
	}
	public Rare(String c, int a, String p, double v, int nbExemplaire) {
		super(c, a, p, v);
		nombreExemplaires = nbExemplaire;
	}
	
	public int getExemplaires() {
		return nombreExemplaires;
	}
	public String toString() {
		String affichage;
		affichage = super.toString() + "\n" + "Nombre d’exemplaires -> " +getExemplaires();
		return affichage;
	}
	public double vente() {
		if (getExemplaires()<BASE_1_EXEMPLAIRES) {
			return(PRIX_BASE_1*(age()/10));
		} else {
			if (getExemplaires()>BASE_1_EXEMPLAIRES && getExemplaires()<BASE_2_EXEMPLAIRES) {
				return(PRIX_BASE_2*(age()/10));
			} else {
				return(PRIX_BASE_3*(age()/10));
			}
		}
	}
}

class Commemoratif extends Timbre {
	public Commemoratif() {
		super();
	}
	public Commemoratif(String c) {
		super(c);
	}
	public Commemoratif(String c, int a) {
		super(c, a);
	}
	public Commemoratif(String c, int a, String p) {
		super(c, a, p);
	}
	public Commemoratif(String c, int a, String p, double v) {
		super(c, a, p, v);
	}
	
	public String toString() {
		String affichage;
		affichage = super.toString() + "\n" + "Timbre celebrant un evenement";
		return affichage;
	}
	public double vente() {
		return 2*super.vente();
	}
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}

