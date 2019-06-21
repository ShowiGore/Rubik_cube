import com.sun.source.tree.ReturnTree;

public class Cube_3x3x3 {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private String[] color = {"\u001B[40m"+ANSI_RED, "\u001B[40m"+ANSI_PURPLE, "\u001B[40m"+ANSI_WHITE, "\u001B[40m"+ANSI_YELLOW, "\u001B[40m"+ANSI_BLUE, "\u001B[40m"+ANSI_GREEN}; // colors

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
		String padding = "\u001B[40m"+ANSI_BLACK+"■■■"+"\u001B[38m";
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
			s += color[f[i][j]]+"■"+ANSI_RESET;
		}

		return s;
	}

}
