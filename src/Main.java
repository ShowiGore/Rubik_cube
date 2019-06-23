import java.awt.*;

public class Main {

	public static void main (String args[]) {

		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.toString());
		long start = System.nanoTime();

		/*
		for (int i=0; i<1000000000; i++){
			cube.F();
		}
		*/

		cube.D();
		System.out.println(cube.toString());
		cube.D();
		System.out.println(cube.toString());
		cube.D();
		System.out.println(cube.toString());
		cube.D();
		System.out.println(cube.toString());


		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");

	}

}
