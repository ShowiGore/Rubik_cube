package Alternative;

public class Cube {

	private Face[] cube;

	public Cube(int size) {
		char[] color = {'u', 'l', 'f', 'r', 'b', 'd'};

		cube = new Face[6];

		for (int i=0; i<6; i++) {
			cube[i] = new Face(size, color[i]);
		}
	}

	public void move (char move) {

		switch(move) {
			case 'U':
				break;
			case 'L':
				break;
			case 'F':
				break;
			case 'R':
				break;
			case 'B':
				break;
			case 'D':
				break;
			default:
				System.out.println("Try a real move");
		}

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
		String[] color = {Colour.ANSI_BG_BLACK+Colour.ANSI_GREEN, Colour.ANSI_BG_BLACK+Colour.ANSI_BLUE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_WHITE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_YELLOW, Colour.ANSI_BG_BLACK+Colour.ANSI_PURPLE, Colour.ANSI_BG_BLACK+Colour.ANSI_RED}; // colors
		int N = cube[0].getColumn(0).length;
		char[] c = {'u', 'l', 'f', 'r', 'b', 'd'};

		for (int i=0; i<N; i++){
			System.out.print(color[getPos(c, cube[face].getRow(row)[i])] + "■ " + Colour.ANSI_RESET);
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

	/* F R U B L D M E S */ /* +inverses */

}
