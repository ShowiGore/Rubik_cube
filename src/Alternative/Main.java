package Alternative;

public class Main {

	public static void main (String[] args) {

	Cube c = new Cube(3);

	c.print();
	System.out.println("Puntuación: " + c.getHeuristic() + "/54");

	c.move("F");
	c.print();
	System.out.println("Puntuación: " + c.getHeuristic() + "/54");

	c.move("F");
	c.print();
	System.out.println("Puntuación: " + c.getHeuristic() + "/54");


	}

}
