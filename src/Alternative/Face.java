package Alternative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Face {

	private char[][] face;

	public Face (int size, char color) {

		face = new char[size][size];

		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				face [i][j] = color;
			}
		}

	}

	public char[][] getFace() {
		return this.face;
	}

	public void rotateClockwise() {

		int N = face.length;

		for (int i = 0; i <= N/2; i++) {
			for (int j = i; j < N-1-i; j++) {

				char aux = face[i][j];
				face[i][j] = face[N-1-j][i];
				face[N-1-j][i] = face[N-1-i][N-1-j];
				face[N-1-i][N-1-j] = face[j][N-1-i];
				face[j][N-1-i] = aux;

			}
		}

	}

	public void rotateCounterclockwise() {

		int N = face.length;

		for (int i = 0; i < N / 2; i++) {
			for (int j = i; j < N-1-i; j++)  {

				char aux = face[i][j];
				face[i][j] = face[j][N-1-i];
				face[j][N-1-i] = face[N-1-i][N-1-j];
				face[N-1-i][N-1-j] = face[N-1-j][i];
				face[N-1-j][i] = aux;

			}
		}

	}

	public char[] getRow(int i) {

		int N = face.length;
		char[] r = new char[N];

		for (int j=0; j<N; j++) {
			r[j] = face[i][j];
		}

		return r;
	}

	public void setRow(char[] r, int i) {
		int N = face.length;
		for (int j=0; j<N; j++) {
			face[i][j] = r[j];
		}
	}

	public char[] getColumn(int j) {

		int N = face.length;
		char[] c = new char[N];

		for (int i=0; i<N; i++) {
			c[i] = face[i][j];
		}

		return c;
	}

	public void setColumn(char[] c, int j) {
		int N = face.length;
		for (int i=0; i<N; i++)
		{
			face[i][j] = c[i];
		}
	}

	public int getValue() {
		int N = face.length;

		Integer[] counter = new Integer[6];
		Arrays.fill(counter,0);

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {

				switch (this.face[i][j]) {//'u', 'l', 'f', 'r', 'b', 'd'
					case 'u':
						counter[0]++;
						break;
					case 'l':
						counter[1]++;
						break;
					case 'f':
						counter[2]++;
						break;
					case 'r':
						counter[3]++;
						break;
					case 'b':
						counter[4]++;
						break;
					case 'd':
						counter[5]++;
						break;
					default:
						//
				}

			}
		}

		return Collections.max(Arrays.asList(counter));
	}

	public String toString() {

		int size = face.length;
		String s = "";

		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				s += face[i][j];
			}
		}

		return s;
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

		Face f = (Face) o;
		for (int i = 0; i < this.face.length; i++) {
			if (!Arrays.equals(this.face[i], f.getFace()[i])) {
				return false;
			}
		}

		return true;
	}

	public int compareTo(Face f) {

		return this.getValue() - f.getValue();

	}

}
