import com.sun.source.tree.ReturnTree;

public class Cube_3x3x3 {

	private String[] color = {Colour.ANSI_BG_BLACK+Colour.ANSI_RED, Colour.ANSI_BG_BLACK+Colour.ANSI_PURPLE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_WHITE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_YELLOW, Colour.ANSI_BG_BLACK+Colour.ANSI_BLUE, Colour.ANSI_BG_BLACK+Colour.ANSI_GREEN}; // colors

	private int N = 3;

	private int[][] F = new int[N][N];
	private int[][] B = new int[N][N];
	private int[][] U = new int[N][N];
	private int[][] D = new int[N][N];
	private int[][] L = new int[N][N];
	private int[][] R = new int[N][N];

	public Cube_3x3x3() {

		filler(F, 0);
		filler(B, 1);
		filler(U, 2);
		filler(D, 3);
		filler(L, 4);
		filler(R, 5);

	}

	private int[][] filler (int[][] f, int c) { // Rellena una cara de un color

		for (int i=0; i<N; i++){
			for (int j=0; j<N; j++){
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

		for (int i=0; i<N; i++){
			s += padding + getRowString(U, i) + padding + padding + "\n";
		}

		for (int i=0; i<N; i++){
			s += getRowString(L, i) + getRowString(F, i) + getRowString(R, i) + getRowString(B, i) + "\n";
		}

		for (int i=0; i<N; i++){
			s += padding + getRowString(D, i) + padding + padding + "\n";
		}

		return s;
	}

	private String getRowString (int[][] f, int i){
		String s = "";

		for (int j=0; j<N; j++){
			s += color[f[i][j]] + "■" + Colour.ANSI_RESET;
		}

		return s;
	}

	public void F (){

		int[] aux = new int[N];

		rotateFaceClockwise(F);

		aux = getRow(U, 2);

		putRow(getColumn(L, 2), U, 2);
		putColumn(getRow(D, 0), L, 2);
		putRow(getColumn(R, 0), D, 0);
		putColumn(aux, R, 0);

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

	private void rotateFaceClockwise (int[][] f){
		int[][] aux = f;

		for (int i=0; i<N; i++){
			for (int j=0; j<N; j++){
				f[i][j] = aux[N-1-j][i];
			}
		}

		/*
		f[0][0] = aux[2][0];
		f[0][1] = aux[1][0];
		f[0][2] = aux[0][0];

		f[1][0] = aux[2][1];
		//f[1][1] = aux[1][1];
		f[1][2] = aux[0][1];

		f[2][0] = aux[2][2];
		f[2][1] = aux[1][2];
		f[2][2] = aux[0][2];
		*/

	}

	private void rotateFaceCounterclockwise (int[][] f){
		int[][] aux = f;

		for (int i=0; i<N; i++){
			for (int j=0; j<N; j++){
				f[i][j] = aux[j][N-1-i];
			}
		}

		/*
		f[0][0] = aux[0][2];
		f[0][1] = aux[1][2];
		f[0][2] = aux[2][2];

		f[1][0] = aux[0][1];
		//f[1][1] = aux[1][1];
		f[1][2] = aux[2][1];

		f[2][0] = aux[0][0];
		f[2][1] = aux[1][0];
		f[2][2] = aux[2][0];
		*/

	}

	private int[] getRow (int[][] f, int i){
		int[] r = new int[N];

		for (int j=0; j<N; j++){
			r[j] = f[i][j];
		}

		return r;
	}

	private int[] getColumn (int[][] f, int j){
		int[] c = new int[N];

		for (int i=0; i<N; i++){
			c[i] = f[i][j];
		}

		return c;
	}

	private void putRow (int[] r, int[][] f, int i){

		for (int j=0; j<N; j++){
			f[i][j] = r[j];
		}

	}

	private void putColumn (int[] c, int[][] f, int j){

		for (int i=0; i<N; i++){
			f[i][j] = c[i];
		}

	}

}
