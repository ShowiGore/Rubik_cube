import java.awt.*;
import java.util.Random;

public class Main {

	public static void main (String args[]) {

		for (int i=0; i<1; i++) {
			perfectScrambleTest();
		}

	}

	private static void cicleTest () {
		long start = System.nanoTime();

		Cube_3x3x3 cube = new Cube_3x3x3();
		int moves = 0;

		System.out.println(cube.toString());

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

	private static void scrambleTest () {
		long start = System.nanoTime();

		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.toString());
		System.out.println(cube.puntuation());

		cube.scramble();

		System.out.println(cube.toString());
		System.out.println(cube.puntuation());

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

	private static void speedTest () {
		long start = System.nanoTime();

		Cube_3x3x3 cube = new Cube_3x3x3();

		for (int i=0; i<1000000000; i++) {
			cube.F();
		}

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

	private static void perfectScrambleTest () {
		long start = System.nanoTime();

		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.toString());
		System.out.println(cube.puntuation());

		while (cube.puntuation()!=0) {
			cube.scramble(1);
		}

		System.out.println(cube.toString());
		System.out.println(cube.puntuation());

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

}
