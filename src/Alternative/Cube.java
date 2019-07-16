package Alternative;

import java.util.*;
import java.lang.Object.*;

public class Cube {

	private Face[] cube;

	public Cube(Face[] cube) {
		this.cube = cube;
	}

	public Cube(int size) {
		char[] color = {'u', 'l', 'f', 'r', 'b', 'd'};

		cube = new Face[6];

		for (int i=0; i<6; i++) {
			cube[i] = new Face(size, color[i]);
		}
	}

	private int getPos(char[] a, char target) {
		for (int i = 0; i < a.length; i++){
			if (target == (a[i])){
				return i;
			}
		}

		return -1;
	}

	public void move(String movement){
		int N = cube[0].getColumn(0).length;

		int face = -1;
		boolean counterclockwise = false;

		int[] faces = new int[4];
		boolean[] columnORrow = new boolean[4];
		int[] wich = new int[4];
		boolean[] inverted = new boolean[4];


		if ("F".equals(movement)) {
			face = 2;
			counterclockwise = false;
			faces = new int[]{0, 1, 5, 3};
			columnORrow = new boolean[]{true, false, true, false};
			wich = new int[]{N-1, N-1, 0, 0};
			inverted = new boolean[]{false, true, false, true};
		} else if ("R".equals(movement)) {
			face = 3;
			counterclockwise = false;
			faces = new int[]{0, 2, 5, 4};
			columnORrow = new boolean[]{false, false, false, false};
			wich = new int[]{N-1, N-1, N-1, 0};
			inverted = new boolean[]{true, false, false, true};
		} else if ("U".equals(movement)) {
			face = 0;
			counterclockwise = false;
			faces = new int[]{1, 2, 3, 4};
			columnORrow = new boolean[]{true, true, true, true};
			wich = new int[]{0, 0, 0, 0};
			inverted = new boolean[]{false, false, false, false};
		} else if ("B".equals(movement)) {
			face = 4;
			counterclockwise = false;
			faces = new int[]{0, 3, 5, 1};
			columnORrow = new boolean[]{true, false, true, false};
			wich = new int[]{0, N-1, N-1, 0};
			inverted = new boolean[]{true, false, true, false};
		} else if ("L".equals(movement)) {
			face = 1;
			counterclockwise = false;
			faces = new int[]{0, 4, 5, 2};
			columnORrow = new boolean[]{false, false, false, false};
			wich = new int[]{0, N-1, 0, 0};
			inverted = new boolean[]{false, true, true, false};
		} else if ("D".equals(movement)) {
			face = 5;
			counterclockwise = false;
			faces = new int[]{1, 4, 3, 2};
			columnORrow = new boolean[]{true, true, true, true};
			wich = new int[]{N-1, N-1, N-1, N-1};
			inverted = new boolean[]{false, false, false, false};
		} else if ("M".equals(movement)) {

		} else if ("E".equals(movement)) {

		} else if ("S".equals(movement)) {

		} else {
			return;
		}

		if (face != -1) {
			moveSide(face, counterclockwise);
		}
		moveLayer(faces, columnORrow, wich, inverted);

	}

	private void moveSide(int face, boolean counterclockwise) {

		if (counterclockwise) {
			cube[face].rotateCounterclockwise();
		} else {
			cube[face].rotateClockwise();
		}

	}

	private void moveLayer(int[] faces, boolean[] columnORrow, int[] wich, boolean[] inverted) {
		int N = cube[0].getRow(0).length;

		char[] aux = new char[N];
		char[] insert = new char[N];

		if (columnORrow[0]) {
			if (inverted[0]) {
				aux = cube[faces[0]].getRowReverse(wich[0]);
			} else {
				aux = cube[faces[0]].getRow(wich[0]);
			}
		} else {
			if (inverted[0]) {
				aux = cube[faces[0]].getColumnReverse(wich[0]);
			} else {
				aux = cube[faces[0]].getColumn(wich[0]);
			}
		}	//save first

		for (int i=0; i<3; i++) {

			if (columnORrow[i+1]) {
				if (inverted[i+1]) {
					insert = cube[faces[i+1]].getRowReverse(wich[i+1]);
				} else {
					insert = cube[faces[i+1]].getRow(wich[i+1]);
				}
			} else {
				if (inverted[i+1]) {
					insert = cube[faces[i+1]].getColumnReverse(wich[i+1]);
				} else {
					insert = cube[faces[i+1]].getColumn(wich[i+1]);
				}
			}	//what to insert

			if (columnORrow[i]) {
				cube[faces[i]].setRow(insert, wich[i]);
			} else {
				cube[faces[i]].setColumn(insert, wich[i]);
			}	//where to insert

		}

		if (columnORrow[3]) {
			cube[faces[3]].setRow(aux, wich[3]);
		} else {
			cube[faces[3]].setColumn(aux, wich[3]);
		}	//set last

	}	//inverted at get, not at set

	public int getHeuristic() {
		int c = 0;

		for (int i=0; i<6; i++) {
			c += this.cube[i].getHeuristic();
		}

		return c;
	}

	public List<Cube> nextStates(){
		String[] moves = {"U", "L", "F", "R", "B", "D", "M", "E", "S"};
		List l = new LinkedList<Cube>();
		Cube nc;

		for (int i=0; i<moves.length; i++) {
			nc = new Cube(this.cube.clone());
			nc.move(moves[i]);
			l.add(nc);
		}

		return l;
	}

	public void print() {

		int N = cube[0].getColumn(0).length;

		for (int i=0; i<N; i++) {
			printPadding();
			printRow(0, i);
			printPadding();
			printPadding();
			System.out.println();
		}

		for (int i=0; i<N; i++) {
			for (int j=1; j<=4; j++) {
				printRow(j, i);
			}
			System.out.println();
		}

		for (int i=0; i<N; i++) {
			printPadding();
			printRow(5, i);
			printPadding();
			printPadding();
			System.out.println();
		}

	}

	private void printPadding() {
		System.out.print(Colour.ANSI_BG_BLACK+ Colour.ANSI_BLACK + "■ ■ ■ " + Colour.ANSI_RESET);
	}

	private void printRow(int face, int row) {
		String[] color = {Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_WHITE, Colour.ANSI_BG_BLACK+Colour.ANSI_PURPLE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_GREEN, Colour.ANSI_BG_BLACK+Colour.ANSI_RED, Colour.ANSI_BG_BLACK+Colour.ANSI_BLUE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_YELLOW}; // colors
		int N = cube[0].getColumn(0).length;
		char[] c = {'u', 'l', 'f', 'r', 'b', 'd'};

		for (int i=0; i<N; i++){
			System.out.print(color[getPos(c, cube[face].getRow(row)[i])] + "■ " + Colour.ANSI_RESET);
		}
	}

	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (getClass() != o.getClass()){
			return false;
		}

		Cube c = (Cube) o;

		/**
		 * hay que comprobar que todas las caras son iguales,
		 * pero caras cuyo centro sean iguales,
		 * los centros pueden no estar en la misma posición del array de caras
		 **/

		return true;
	}

	public int compareTo(Cube c) {

		return this.getHeuristic() - c.getHeuristic();

	}

}
