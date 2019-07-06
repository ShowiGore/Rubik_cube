package Alternative;

import java.util.Arrays;
import java.util.BitSet;

public class Face {

	private BitSet[][] face;

	public Face (short size, BitSet color) {
		face = new BitSet[size][size];

		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				face [i][j] = color;
			}
		}

	}

	public BitSet[][] getFace () {
		return this.face;
	}

	private void rotateClockwise () {

		int N = face.length-1;

		for (int i = 0; i <= N/2; i++) {
			for (int j = i; j < N-i; j++) {

				BitSet aux = face[i][j];
				face[i][j] = face[N-j][i];
				face[N-j][i] = face[N-i][N-j];
				face[N-i][N-j] = face[j][N-i];
				face[j][N-i] = aux;

			}
		}

	}


	private void rotateCounterclockwise() {

		int N = face.length-1;

		for (int i = 0; i < N / 2; i++) {
			for (int j = i; j < N-i-1; j++)  {

				BitSet aux = face[i][j];
				face[i][j] = face[j][N-1-i];
				face[j][N-1-i] = face[N-1-i][N-1-j];
				face[N-1-i][N-1-j] = face[N-1-j][i];
				face[N-1-j][i] = aux;

			}
		}

	}

}
