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

	public int getSize () {
		return cube[0].getColumn(0).length;
	}

	public Face getFace (int face) {
		return this.cube[face];
	}

	private void moveSide(int face, boolean counterclockwise) {

		if (counterclockwise) {
			cube[face].rotateCounterclockwise();
		} else {
			cube[face].rotateClockwise();
		}

	}

	/**
	 *
	 * @param faces			Caras adyacentes a la cara que se rota (en orden, 1º dónde se introduce después de dónde se extrae, es decir en orden opuesto al que se gira)
	 * @param columnORrow	De cada cara anterior se extrae fila o columna
	 * @param wich			Que fila o columna se extrae
	 * @param inverted		Al extraer se tiene que invertir o no
	 *
	 */
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

	public boolean solved() {
		int N = cube[0].getRow(0).length;

		for (int i=0; i<N; i++) {
			if (!this.cube[i].solved()) {
				return false;
			}
		}

		return true;

	}

	public List<Pair<Cube, String>> nextStates(){
		String[] moves = {"U", "L", "F", "R", "B", "D", "M", "E", "S", "U'", "L'", "F'", "R'", "B'", "D'", "M'", "E'", "S'"};
		List<Pair<Cube, String>> l = new LinkedList<>();
		Cube nc;

		for (int i=0; i<moves.length; i++) {
			nc = (Cube) this.clone();
			nc.move(moves[i]);

			l.add(new Pair<>(nc, moves[i]));
		}

		return l;
	}

	private int getPos(char[] a, char target) {
		for (int i = 0; i < a.length; i++){
			if (target == (a[i])){
				return i;
			}
		}

		return -1;
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

	protected Object clone() {
		Face[] fs = new Face[6];

		for (int i=0; i<6; i++) {
			fs[i] = (Face) this.cube[i].clone();
		}

		Cube nc = new Cube(fs);

		return nc;
	}

	public void moves(String movements){

		String[] moves = movements.split(" ");

		for (int i=0; i<moves.length; i++) {
			move(moves[i]);
		}

	}

	public void move(String movement){

		if ("F".equals(movement)) {
			this.F();
		} else if ("R".equals(movement)) {
			this.R();
		} else if ("U".equals(movement)) {
			this.U();
		} else if ("B".equals(movement)) {
			this.B();
		} else if ("L".equals(movement)) {
			this.L();
		} else if ("D".equals(movement)) {
			this.D();
		} else if ("M".equals(movement)) {
			this.M();
		} else if ("E".equals(movement)) {
			this.E();
		} else if ("S".equals(movement)) {
			this.S();
		} else if ("F'".equals(movement)) {
			this.F();
			this.F();
			this.F();
		} else if ("R'".equals(movement)) {
			this.R();
			this.R();
			this.R();
		} else if ("U'".equals(movement)) {
			this.U();
			this.U();
			this.U();
		} else if ("B'".equals(movement)) {
			this.B();
			this.B();
			this.B();
		} else if ("L'".equals(movement)) {
			this.L();
			this.L();
			this.L();
		} else if ("D'".equals(movement)) {
			this.D();
			this.D();
			this.D();
		} else if ("M'".equals(movement)) {
			this.M();
		} else if ("E'".equals(movement)) {
			this.E();
			this.E();
			this.E();
		} else if ("S'".equals(movement)) {
			this.S();
			this.S();
			this.S();
		} else {
			System.out.println("\n" + movement + " is not a move." + "\n");
			return;
		}

	}

	private void F (){
		int N = cube[0].getColumn(0).length;
		moveSide(2, false);
		moveLayer(new int[]{0, 1, 5, 3}, new boolean[]{true, false, true, false}, new int[]{N-1, N-1, 0, 0}, new boolean[]{false, true, false, true});
	}

	private void R (){
		int N = cube[0].getColumn(0).length;
		moveSide(3, false);
		moveLayer(new int[]{0, 2, 5, 4}, new boolean[]{false, false, false, false}, new int[]{N-1, N-1, N-1, 0}, new boolean[]{true, false, false, true});
	}

	private void U (){
		int N = cube[0].getColumn(0).length;
		moveSide(0, false);
		moveLayer(new int[]{1, 2, 3, 4}, new boolean[]{true, true, true, true}, new int[]{0, 0, 0, 0}, new boolean[]{false, false, false, false});
	}

	private void B (){
		int N = cube[0].getColumn(0).length;
		moveSide(4, false);
		moveLayer(new int[]{0, 3, 5, 1}, new boolean[]{true, false, true, false}, new int[]{0, N-1, N-1, 0}, new boolean[]{true, false, true, false});
	}

	private void L (){
		int N = cube[0].getColumn(0).length;
		moveSide(1, false);
		moveLayer(new int[]{0, 4, 5, 2}, new boolean[]{false, false, false, false}, new int[]{0, N-1, 0, 0}, new boolean[]{false, true, true, false});
	}

	private void D (){
		int N = cube[0].getColumn(0).length;
		moveSide(5, false);
		moveLayer(new int[]{1, 4, 3, 2}, new boolean[]{true, true, true, true}, new int[]{N-1, N-1, N-1, N-1}, new boolean[]{false, false, false, false});
	}

	private void M (){
		int N = cube[0].getColumn(0).length;
		moveLayer(new int[]{0, 4, 5, 2}, new boolean[]{false, false, false, false}, new int[]{N/2, N/2, N/2, N/2}, new boolean[]{false, true, true, false});
	}

	private void E (){
		int N = cube[0].getColumn(0).length;
		moveLayer(new int[]{1, 4, 3, 2}, new boolean[]{true, true, true, true}, new int[]{N/2, N/2, N/2, N/2}, new boolean[]{false, false, false, false});
	}

	private void S (){
		int N = cube[0].getColumn(0).length;
		moveLayer(new int[]{0, 1, 5, 3}, new boolean[]{true, false, true, false}, new int[]{N/2, N/2, N/2, N/2}, new boolean[]{false, true, false, true});
	}

};
