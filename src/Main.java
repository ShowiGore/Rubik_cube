import java.awt.*;
import java.util.Random;

import java.awt.*;
import java.util.Random;

public class Main
{

	public static void main (String args[]) throws InterruptedException
	{

		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.getID());
		cube.scramble();
		System.out.println(cube.getID());


	}

	private static void cicleTest()
	{
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

	private static void scrambleTest()
	{
		String s = "";
		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.toString());
		System.out.println(cube.puntuation());

		long start = System.nanoTime();

		s += cube.scramble();

		long end = System.nanoTime();

		System.out.println("Moves: " + s);

		System.out.println(cube.toString());
		System.out.println(cube.puntuation());


		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

	private static void speedTest()
	{
		long start = System.nanoTime();

		Cube_3x3x3 cube = new Cube_3x3x3();

		for (int i=0; i<1000000000; i++)
		{
			cube.F();
		}

		long end = System.nanoTime();
		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

	private static void preciseScrambleTest()
	{
		String s = "";
		Cube_3x3x3 cube = new Cube_3x3x3();

		System.out.println(cube.toString());
		System.out.println("Puntuation:" + cube.puntuation());

		long start = System.nanoTime();

		while (cube.puntuation()<(54-6))
		{
			s += cube.scramble(1);
		}

		System.out.println("Moves: " + s);

		long end = System.nanoTime();

		System.out.println(cube.toString());
		System.out.println("Puntuation:" + cube.puntuation());

		long time = (end-start)/1000000000;
		System.out.println("Time: " + time + " s");
	}

	private static void javasusTest()
	{
		Cube_3x3x3 xd1 = new Cube_3x3x3(0);
		Cube_3x3x3 xd2 = new Cube_3x3x3(0);

		System.out.println("Solved: "+xd1.solved()+" "+xd1.puntuation());

		System.out.println(xd1.equals(xd2)); //true
		//xd2.F();
		System.out.println(xd1.equals(xd2)); //false

		//test compareTo

		Cube_3x3x3 xd3 = new Cube_3x3x3(xd1);

		//test del constructor clon
		System.out.println(xd3.equals(xd1)); //true


		//test solve
		System.out.println("---INITIAL---\n"+xd2);
		xd2.B();


		System.out.println("---THEN---\n"+xd2+" "+xd2.puntuation());

		xd1.scramble(2);
		System.out.println(xd1);
		Cube_solver solver = new Cube_solver(xd1);
		try
		{
			solver.solve();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		//U = PRIMERO IZQIUERDA
		//B = FINAL A LA IZQUIERDA
		//L = COLUMNA DE LA IZQUIERDA HACIA DENTRO
		//R = COLUMNA DE LA DERECHA HACIA ARRIBA
		//D = FILA DE ABAJO MUEVE HACIA LA DERECHA
		//F = GIRAR LA CARA HACIA LA DERECHA

	}



}

