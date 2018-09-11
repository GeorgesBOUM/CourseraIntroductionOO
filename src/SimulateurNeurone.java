
import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

/* LA CLASSE Position */
class Position {
	private double x;
	private double y;
	
	/* Les constructeurs */
	public Position() {
		x = 0.0;
		y = 0.0;
	}
	public Position(double abs, double ord){
		x = abs;
		y = ord;
	}
	
	/* Les getters */
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	/* Autres méthodes */
	public String toString() {
		return("("+getX()+","+" "+getY()+")");
	}
}

/* LA CLASSE Neurone */
class Neurone {
	private Position position;
	private ArrayList<Neurone> connexions;
	private double signal;
	private double attenuation;
	
	/* Le constructeur*/
	public Neurone(Position p, double att) {
		position = p;
		connexions = new ArrayList<Neurone>();
		signal = 0.0;
		attenuation = att;
	}
	
	/* Les getters */
	public Position getPosition() {
		return position;
	}
	public Neurone getConnexion(int index) {
		return connexions.get(index);
	}
	public int getNbConnexions() {
		return connexions.size();
	}
	public double getSignal() {
		return signal;
	}
	public double getAttenuation() {
		return attenuation;
	}
	
	/*autres méthodes */
	/**
	 * Ajout d'un neurone à la connexion
	 * @param n
	 * 		On ajoute le neurone n
	 * */
	public void connexion(Neurone n) {
		connexions.add(n);
	}

	/**
	 * La stimulation d'un neurone
	 * @param stimulus
	 * */
	public void recoitStimulus(double stimulus) {
		this.signal = stimulus * getAttenuation();
		propagationStimulus(getSignal());
	}
	
	/**
	 * La propagation du stimulus
	 * aux neurones connectés
	 * */
	public void propagationStimulus(double stimul) {
		for(int i=0; i<connexions.size(); i++) {
			connexions.get(i).recoitStimulus(stimul);
		}
	}
	
	public String toString() {
		String  messageToDisplay = "Le neurone en position " +getPosition().toString()+ " avec attenuation "+getAttenuation();
			if (connexions.size() >= 1) {
				messageToDisplay = messageToDisplay + " en connexion avec\n";
				for (int i = 0; i < connexions.size(); i++) {
					messageToDisplay = messageToDisplay + "  - un neurone en position "+connexions.get(i).getPosition().toString()+"\n";			
				}
				return messageToDisplay;
			} else {
				messageToDisplay = messageToDisplay + " sans connexion\n";
				return messageToDisplay;
			}
	}
}

/* LA CLASSE NeuroneCumulatif */
class NeuroneCumulatif extends Neurone{
	
	/* Le constructeur*/
	public NeuroneCumulatif(Position p, double att) {
		super(p, att);
	}
	
	/** 
	 * @Override
	 * redéfinition de la méthode recoitStimulus
	 * @param stimulus
	 * 		il s'agit au final de recevoir un signal cumulé
	 * (qui est la somme du stimulus reçu en paramètre et du rapport
	 * du signal interne par l'atténuation)
	 * c'est cette somme qui est envoyé à la super.recoitStimulus
	 * */
	public void recoitStimulus(double stimulus) {
		double stimulusCumule = stimulus + getSignal()/getAttenuation();
		super.recoitStimulus(stimulusCumule);
		//propagationStimulus(getSignal());
	}
}

/* La classe Cerveau */
class Cerveau {
	private ArrayList<Neurone> cerveau;
	
	/* Le constructeur par defaut */
	public Cerveau() {
		cerveau = new ArrayList<Neurone>();
	}
	
	/* Les getters */
	public int getNbNeurones() {
		return cerveau.size();
	}
	public Neurone getNeurone(int index) {
		return cerveau.get(index);
	}
	
	/*Autres méthodes*/
	public void ajouterNeurone(Position pos, double attenuation) {
		Neurone neurone = new Neurone(pos, attenuation);
		cerveau.add(neurone);
	}
	public void ajouterNeuroneCumulatif(Position pos, double attenuation) {
		NeuroneCumulatif neuroneCumulatif = new NeuroneCumulatif(pos, attenuation);
		cerveau.add(neuroneCumulatif);
	}
	public void stimuler(int index, double stimulus) {
		cerveau.get(index).recoitStimulus(stimulus);
	}
	public double sonder(int index) {
		double signal = cerveau.get(index).getSignal();
		return signal;
	}
	/**
	 * connexion neuronale quelconque 
	 * elle s'applique à un cerveau d'au moins 3 neurones*/
	private void connexionQuelconque() {
		for (int i = 1; i < getNbNeurones() - 2; i+=2) {
			cerveau.get(i).connexion(cerveau.get(i+1));
			cerveau.get(i+1).connexion(cerveau.get(i+2));
		}
	}
	/**
	 * D'après les tests, cette fonction est INUTILE ?
	 * connexions neuronales selon la parité
	 * ou non du nombre de neurones
	 * */
	private void connexionSelonParite() {
		if (getNbNeurones()%2==0) {
			connexionQuelconque();
		} else {
			connexionQuelconque();
			/* on connecte ensuite les deux derniers neurones 
			 * dans le cas d'un nombre total impair
			 * de neurones */
			cerveau.get(getNbNeurones()-2).connexion(cerveau.get(getNbNeurones()-1));
		}
	}
	public void creerConnexions() {
		if (!cerveau.isEmpty()) {
			if (getNbNeurones() >= 3) {
				/* Penser aux connexions du neurone 0*/
				cerveau.get(0).connexion(cerveau.get(1));
				cerveau.get(0).connexion(cerveau.get(2));
				if (cerveau.size()>3) {
					connexionQuelconque();
				}
			} else if(getNbNeurones() == 2){
				cerveau.get(0).connexion(cerveau.get(1));
			}
		}
	}
	public String toString() {
		String messageFinal = "\n*----------*\n\n";
		messageFinal = messageFinal + "Le cerveau contient "+getNbNeurones()+" neurone(s)\n";
		for (int i = 0; i < getNbNeurones(); i++) {
			messageFinal = messageFinal +cerveau.get(i).toString()+"\n";
		}
		messageFinal = messageFinal + "*----------*\n\n";
		return messageFinal;
	}
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3*/
    }
    
}

