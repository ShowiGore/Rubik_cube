package Alternative;

public class Main {

	public static void main (String[] args) {

	Cube c = new Cube(3);

	c.print();
	System.out.println("Puntuación: " + c.getHeuristic() + "/54");

	moveAndInfo(c, "U");
	moveAndInfo(c, "L");
	moveAndInfo(c, "F");
	moveAndInfo(c, "R");
	moveAndInfo(c, "B");
	moveAndInfo(c, "D");
	moveAndInfo(c, "U");
	moveAndInfo(c, "L");
	moveAndInfo(c, "F");
	moveAndInfo(c, "R");
	moveAndInfo(c, "B");
	moveAndInfo(c, "D");

	}

	private static void moveAndInfo (Cube c, String s) {
		c.move(s);
		c.print();
		System.out.println("Puntuación: " + c.getHeuristic() + "/54");
	}

	private static void speedTest(int n) {
		long start = System.nanoTime();

		Cube cube = new Cube(3);

		for (int i=0; i<n; i++)
		{
			cube.move("F");
		}

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

}
