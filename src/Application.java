class Test {

	private String name;
	private int number;

	public Test(String name, int number) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.number = number;
	}

	public void affiche() {
		// TODO Auto-generated method stub
		System.out.println(name + " numéro " + number);
	}
	
}

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test("test",1);
		t.affiche();
	}

}
