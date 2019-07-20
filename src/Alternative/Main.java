package Alternative;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Main {

	public static void main (String[] args) {

	//Cube c = new Cube(3);

	//c.print();
	//System.out.println("Puntuación: " + c.getHeuristic() + "/54");

	testCloning();

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

		HashSet<Cube> visited = new HashSet<>();
		 //pending = new SortedList();




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

}
