import com.sun.source.tree.ReturnTree;

public class Cube_3x3x3 {

	private String[] color = {Colour.ANSI_BG_BLACK+Colour.ANSI_RED, Colour.ANSI_BG_BLACK+Colour.ANSI_PURPLE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_WHITE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_YELLOW, Colour.ANSI_BG_BLACK+Colour.ANSI_BLUE, Colour.ANSI_BG_BLACK+Colour.ANSI_GREEN}; // colors

	private int row = 3;
	private int column = 3;

	private int[][] F = new int[row][column];
	private int[][] B = new int[row][column];
	private int[][] U = new int[row][column];
	private int[][] D = new int[row][column];
	private int[][] L = new int[row][column];
	private int[][] R = new int[row][column];

	public Cube_3x3x3() {

		filler(F, 0);
		filler(B, 1);
		filler(U, 2);
		filler(D, 3);
		filler(L, 4);
		filler(R, 5);

	}

	private int[][] filler (int[][] f, int c) { // Rellena una cara de un color

		for (int i=0; i<row; i++){
			for (int j=0; j<column; j++){
				f[i][j] = c;
			}
		}

		return f;
	}

	/*

		UUU
		UUU
		UUU
	LLL	FFF	RRR	BBB
	LLL FFF	RRR	BBB
	LLL FFF	RRR	BBB
		DDD
		DDD
		DDD

	*/

	public String toString () {
		String padding = Colour.ANSI_BG_BLACK+Colour.ANSI_BLACK + "■■■" + Colour.ANSI_RESET;
		String s = "";

		for (int i=0; i<row; i++){
			s += padding + getRow(U, i) + padding + padding + "\n";
		}

		for (int i=0; i<row; i++){
			s += getRow(L, i) + getRow(F, i) + getRow(R, i) + getRow(B, i) + "\n";
		}

		for (int i=0; i<row; i++){
			s += padding + getRow(D, i) + padding + padding + "\n";
		}

		return s;
	}

	private String getRow (int[][] f, int i){
		String s = "";

		for (int j=0; j<column; j++){
			s += color[f[i][j]] + "■" + Colour.ANSI_RESET;
		}

		return s;
	}

	public void F (){

	}

	public void B (){

	}

	public void U (){

	}

	public void D (){

	}

	public void L (){

	}

	public void R (){

	}

	private void rotateSideClockwise (int[][] f, int[][] b, int[][] u, int[][] d, int[][] l, int[][] r){

	}

	private void rotateSideCounterclockwise (int[][] f, int[][] b, int[][] u, int[][] d, int[][] l, int[][] r){

	}

	private void rotateFaceClockwise (int[][] f){ // mejorable
		int[][] aux = f;

		f[0][0] = aux[2][0];
		f[0][1] = aux[1][0];
		f[0][2] = aux[0][0];

		f[1][0] = aux[2][1];
		//f[1][1] = aux[1][1];
		f[1][2] = aux[0][1];

		f[2][0] = aux[2][2];
		f[2][1] = aux[1][2];
		f[2][2] = aux[0][2];

	}

	private void rotateFaceCounterclockwise (int[][] f){ // mejorable
		int[][] aux = f;

		f[0][0] = aux[0][2];
		f[0][1] = aux[1][2];
		f[0][2] = aux[2][2];

		f[1][0] = aux[0][1];
		//f[1][1] = aux[1][1];
		f[1][2] = aux[2][1];

		f[2][0] = aux[0][0];
		f[2][1] = aux[1][0];
		f[2][2] = aux[2][0];

	}

}
