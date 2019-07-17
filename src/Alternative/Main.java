package Alternative;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Main {

	public static void main (String[] args) {

	Cube c = new Cube(3);

	c.print();
	System.out.println("Puntuación: " + c.getHeuristic() + "/54");



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
		 pending = new SortedList();




	}

}
