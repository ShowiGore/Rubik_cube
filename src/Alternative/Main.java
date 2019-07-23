package Alternative;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Main {

	public static void main (String[] args) {

		Cube c = new Cube(3);

		c.print();
		System.out.println("Puntuación: " + c.getHeuristic() + "/54");

		c.move("U");c.move("U");c.move("U");
		c.move("R");c.move("R");c.move("R");
		c.move("L");c.move("L");c.move("L");
		c.move("D");c.move("D");c.move("D");
		c.move("B");c.move("B");c.move("B");
		c.move("F");c.move("F");c.move("F");
		c.move("U");c.move("U");c.move("U");

		c.print();
		System.out.println("Puntuación: " + c.getHeuristic() + "/54");

		System.out.println("\n\n");
		solve(c);

	}

	private static void moveAndInfo (Cube c, String s) {
		c.move(s);
		c.print();
		System.out.println("Puntuación: " + c.getHeuristic() + "/54");
	}

	private static void speedTest (int n) {
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

	private static void solve (Cube c) {

		long start = System.nanoTime();

		boolean solved = false;
		String solution = "";

		List<Cube> checked = new LinkedList<>();
		List<Pair<Cube, String>> unchecked = new LinkedList<>();

		unchecked.add(new Pair<>(c, ""));

		while (!unchecked.isEmpty() && !solved) {

			Pair<Cube, String> cheking = unchecked.get(0);

			List<Pair<Cube, String>> nextStates = cheking.getL().nextStates();

			for (Pair<Cube, String> p : nextStates) {

				if (checked.indexOf(p.getL()) == -1) {	//no ha sido visitado

					p.setR(cheking.getR() + " " + p.getR());	// aniadimos los pasos
					Cube next = p.getL();

					if (next.solved()) {	//se termina
						solved = true;
						solution = p.getR();
					} else {	//lo aniadimos
						unchecked.add(p);
					}

				}

			}

			unchecked.remove(0);

		}

		System.out.println(solution);

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");

	}

	static void testCloning (){
		Cube original = new Cube(3);

			//Lets create a clone of original object
		Cube cloned = new Cube(4);

		System.out.println(cloned.getFace(0).toString());
		cloned = (Cube) original.clone();
		System.out.println(cloned.getFace(0).toString());

		// different memory addresses
		System.out.println(original != cloned);

		// same class
		System.out.println(original.getClass() == cloned.getClass());

		//equals
		System.out.println(original.equals(cloned));
	}

	static void calculateHeuristic() {

	}

}
