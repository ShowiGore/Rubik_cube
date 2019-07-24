package Alternative;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Main {

	public static void main (String[] args) {

		Cube c = new Cube(3);
		printInfo(c);

		c.moves("U R L D M");
		printInfo(c);

		solve(c);

	}

	private static void printInfo (Cube c) {
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
		String solution = "Not found";

		List<Cube> checked = new LinkedList<>();
		List<Pair<Cube, String>> unchecked = new LinkedList<>();

		unchecked.add(new Pair<>(c, ""));

		while (!unchecked.isEmpty() && !solved) {

			Pair<Cube, String> cheking = unchecked.get(0);
			unchecked.remove(cheking);

			List<Pair<Cube, String>> nextStates = cheking.getL().nextStates();

			for (Pair<Cube, String> p : nextStates) {

				//if (checked.indexOf(p.getL()) < 0) {	//no ha sido visitado
					//System.out.println("No se ha visitado");

					p.setR(cheking.getR() + p.getR() + " " );	// aniadimos los pasos
					Cube next = p.getL();

					if (next.solved()) {	//se termina
						solved = true;
						solution = p.getR();
					} else {	//lo aniadimos
						unchecked.add(p);
					}

				//}

			}

			checked.add(cheking.getL());

			System.out.println("Checked: " + checked.size());
			System.out.println("Unchecked: " + unchecked.size());
			System.out.println("--------------");

		}

		System.out.println("\n");
		System.out.println(solution);
		System.out.println("\n");

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
		System.out.println("Checked: " + checked.size());
		System.out.println("Unchecked: " + unchecked.size());
		System.out.println("Total: " + (checked.size()+unchecked.size()) );

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

	static void calculateHeuristic(){

	}

}
