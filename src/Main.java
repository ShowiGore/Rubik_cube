import java.awt.*;

public class Main {

	public static void main (String args[]) {

		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.toString());

		if (cube.solved()) {
			System.out.println(":)\n");
		} else {
			System.out.println(":(\n");
		}

		int moves = 0;
		long start = System.nanoTime();

		 do {
			cube.F();
			cube.R();
			cube.U();
			cube.B();
			cube.L();
			cube.D();
			moves += 6;
		} while (!cube.solved());

		System.out.println(cube.toString());
		System.out.println("Moves: " + moves);

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");

	}

}
