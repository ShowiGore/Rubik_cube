import com.sun.source.tree.ReturnTree;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Cube_3x3x3 implements Comparable<Cube_3x3x3>
{

	private String[] color = {Colour.ANSI_BG_BLACK+Colour.ANSI_GREEN, Colour.ANSI_BG_BLACK+Colour.ANSI_BLUE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_WHITE, Colour.ANSI_BG_BLACK+Colour.ANSI_BRIGHT_YELLOW, Colour.ANSI_BG_BLACK+Colour.ANSI_PURPLE, Colour.ANSI_BG_BLACK+Colour.ANSI_RED}; // colors

	private int N = 3;
	int nmovs;

	private int[][] F = new int[N][N];
	private int[][] B = new int[N][N];
	private int[][] U = new int[N][N];
	private int[][] D = new int[N][N];
	private int[][] L = new int[N][N];
	private int[][] R = new int[N][N];

	public Cube_3x3x3()
	{
		//nmovs=n;
		filler(F, 0);
		filler(B, 1);
		filler(U, 2);
		filler(D, 3);
		filler(L, 4);
		filler(R, 5);
	}

	private int[][] filler (int[][] f, int c) // Rellena una cara de un color
	{
		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
				f[i][j] = c;
			}
		}

		return f;
	}

	public String toString ()
	{
		String padding = Colour.ANSI_BG_BLACK+Colour.ANSI_BLACK + "■ ■ ■ " + Colour.ANSI_RESET;
		String s = "";

		for (int i=0; i<N; i++)
		{
			s += padding + getRowString(U, i) + padding + padding + "\n";
		}

		for (int i=0; i<N; i++)
		{
			s += getRowString(L, i) + getRowString(F, i) + getRowString(R, i) + getRowString(B, i) + "\n";
		}

		for (int i=0; i<N; i++)
		{
			s += padding + getRowString(D, i) + padding + padding + "\n";
		}
		s+="\n";
		return s;
	}

	private String getRowString (int[][] f, int i)
	{
		String s = "";

		for (int j=0; j<N; j++){
			s += color[f[i][j]] + "■ " + Colour.ANSI_RESET;
		}

		return s;
	}

	public void F ()
	{
		int[] aux = new int[N];

		rotateFaceClockwise(F);

		aux = getRow(U, 2);

		putRow(getColumnReverse(L, 2), U, 2);
		putColumn(getRow(D, 0), L, 2);
		putRow(getColumnReverse(R, 0), D, 0);
		putColumn(aux, R, 0);
	}

	public void FFirst ()
	{
		F();
		F();
		F();
	}

	public void F2 ()
	{
		F();
		F();
	}

	public void B ()
	{
		int[] aux = new int[N];

		rotateFaceClockwise(B);

		aux = getRowReverse(U, 0);

		putRow(getColumn(R, 2), U, 0);
		putColumn(getRowReverse(D, 2), R, 2);//
		putRow(getColumn(L, 0), D, 2);
		putColumn(aux, L, 0);//
	}

	public void U ()
	{
		int[] aux = new int[N];

		rotateFaceClockwise(U);

		aux = getRow(F, 0);

		putRow(getRow(R, 0), F, 0);
		putRow(getRow(B, 0), R, 0);
		putRow(getRow(L, 0), B, 0);
		putRow(aux, L, 0);
	}

	public void D ()
	{
		int[] aux = new int[N];

		rotateFaceClockwise(D);

		aux = getRow(F, 2);

		putRow(getRow(L, 2), F, 2);
		putRow(getRow(B, 2), L, 2);
		putRow(getRow(R, 2), B, 2);
		putRow(aux, R, 2);
	}

	public void L ()
	{
		int[] aux = new int[N];

		rotateFaceClockwise(L);

		aux = getColumn(U, 0);

		putColumn(getColumnReverse(B, 2), U, 0);
		putColumn(getColumnReverse(D, 0), B, 2);
		putColumn(getColumn(F, 0), D, 0);
		putColumn(aux, F, 0);
	}

	public void R ()
	{
		int[] aux = new int[N];

		rotateFaceClockwise(R);

		aux = getColumnReverse(U, 2);

		putColumn(getColumn(F, 2), U, 2);
		putColumn(getColumn(D, 2), F, 2);
		putColumn(getColumnReverse(B, 0), D, 2);
		putColumn(aux, B, 0);
	}

	public String scramble ()
	{
		return scramble(20);
	}

	public String scramble (int moves)
	{
		Random r = new Random();
		String s = "";

		for (int i=0; i<moves; i++)
		{
			s += move(r.nextInt(6)) + " ";
		}

		return s;
	}

	public String move (int movement)
	{

		String s = "";
		//movement = movement%6;------Tecnicamente solo le va a entrar un numero entre 0 y 5, y por si acaso esta el default

		switch(movement)
		{
			case 0:
				F();
				s = "F";
				break;

			case 1:
				B();
				s = "B";
				break;

			case 2:
				U();
				s = "U";
				break;

			case 3:
				D();
				s = "D";
				break;

			case 4:
				L();
				s = "L";
				break;

			case 5:
				R();
				s = "R";
				break;

			default:
				System.out.println("XD");
		}

		return s;
	}

	private void rotateFaceClockwise (int[][] f)
	{
		int[][] aux = matrixClone(f);

		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
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

	private void rotateFaceCounterclockwise (int[][] f)
	{
		int[][] aux = matrixClone(f);

		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
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

	private int[] getRow (int[][] f, int i)
	{
		int[] r = new int[N];

		for (int j=0; j<N; j++)
		{
			r[j] = f[i][j];
		}

		return r;
	}

	private int[] getRowReverse (int[][] f, int i)
	{
		int[] r = new int[N];

		for (int j=0; j<N; j++)
		{
			r[N-1-j] = f[i][j];
		}

		return r;
	}

	private int[] getColumn (int[][] f, int j)
	{
		int[] c = new int[N];

		for (int i=0; i<N; i++)
		{
			c[i] = f[i][j];
		}

		return c;
	}

	private int[] getColumnReverse (int[][] f, int j)
	{
		int[] c = new int[N];

		for (int i=0; i<N; i++)
		{
			c[N-1-i] = f[i][j];
		}

		return c;
	}

	private void putRow (int[] r, int[][] f, int i)
	{
		for (int j=0; j<N; j++)
		{
			f[i][j] = r[j];
		}
	}

	private void putColumn (int[] c, int[][] f, int j)
	{
		for (int i=0; i<N; i++)
		{
			f[i][j] = c[i];
		}
	}

	private int[][] matrixClone (int[][] m)
	{
		int[][] c = new int[m.length][m[0].length];

		for(int i=0; i<m.length; i++)
		{
			c[i] = m[i].clone();
		}

		return c;
	}

	public boolean solved ()
	{
		return solvedFace(F) && solvedFace(B) && solvedFace(U) && solvedFace(D) && solvedFace(L) && solvedFace(R);
	}

	private boolean solvedFace (int[][] f)
	{
		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{

				if (f[1][1] != f[i][j])
				{
					return false;
				}

			}
		}

		return true;
	}

	public int puntuation ()
	{
		int p = 54;

		p -= puntuationFace(F);
		p -= puntuationFace(B);
		p -= puntuationFace(U);
		p -= puntuationFace(D);
		p -= puntuationFace(L);
		p -= puntuationFace(R);

		return p;
	}

	private int puntuationFace (int[][] f)
	{
		int p = 0;

		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
				if (f[1][1] == f[i][j])
				{
					p++;
				}
			}
		}

		return p;
	}

	//constructor para clonar un cubo dado
	public Cube_3x3x3(Cube_3x3x3 orig) /* SI TUVIERAMOS EL ARRAY 3D ESTO SERIA UN SIMPLE FOR ANIDADO 3 VECES :/  :sad: */
	{
		for (int i=0;i<N;i++)
		{
			for (int j=0;j<N;j++)
			{
				this.F[i][j] = orig.F[i][j];

			}
		}

		for (int i=0;i<N;i++)
		{
			for (int j=0;j<N;j++)
			{
				this.B[i][j] = orig.B[i][j];

			}
		}

		for (int i=0;i<N;i++)
		{
			for (int j=0;j<N;j++)
			{
				this.U[i][j] = orig.U[i][j];
			}
		}

		for (int i=0;i<N;i++)
		{
			for (int j=0;j<N;j++)
			{
				this.D[i][j] = orig.D[i][j];
			}
		}

		for (int i=0;i<N;i++)
		{
			for (int j=0;j<N;j++)
			{
				this.L[i][j] = orig.L[i][j];
			}
		}

		for (int i=0;i<N;i++)
		{
			for (int j=0;j<N;j++)
			{
				this.R[i][j] = orig.R[i][j];
			}
		}

	}

	@Override
	public boolean equals(Object obj) /* SI TUVIERAMOS EL ARRAY 3D ESTO SERIA UN SIMPLE FOR ANIDADO 3 VECES :/  :sad: */
	{
		boolean FFF = obj instanceof Cube_3x3x3;

		if(FFF)
		{
			Cube_3x3x3 lmao = (Cube_3x3x3) obj;

			for (int i=0;i<N&& FFF;i++)
			{
				for (int j=0;j<N&& FFF;j++)
				{
					if (this.F[i][j] != lmao.F[i][j])
					{
						FFF = false;
					}
				}
			}

			for (int i=0;i<N&& FFF;i++)
			{
				for (int j=0;j<N&& FFF;j++)
				{
					if (this.B[i][j] != lmao.B[i][j])
					{
						FFF = false;
					}
				}
			}

			for (int i=0;i<N&& FFF;i++)
			{
				for (int j=0;j<N&& FFF;j++)
				{
					if (this.U[i][j] != lmao.U[i][j])
					{
						FFF = false;
					}
				}
			}

			for (int i=0;i<N&& FFF;i++)
			{
				for (int j=0;j<N&& FFF;j++)
				{
					if (this.D[i][j] != lmao.D[i][j])
					{
						FFF = false;
					}
				}
			}

			for (int i=0;i<N;i++)
			{
				for (int j=0;j<N&& FFF;j++)
				{
					if (this.L[i][j] != lmao.L[i][j])
					{
						FFF = false;
					}
				}
			}

			for (int i=0;i<N;i++)
			{
				for (int j=0;j<N && FFF;j++)
				{
					if (this.R[i][j] != lmao.R[i][j])
					{
						FFF = false;
					}
				}
			}

		}

		return FFF;
	}

	@Override
	public int compareTo(Cube_3x3x3 o)
	{
		if (this.equals(o))
		{
			return 0;
		}
		else
		{
			return -o.puntuation()+this.puntuation();
		}

	}

	public String getID ()
	{
		String id = "";

		id += calculateFaceID(F);
		id += calculateFaceID(B);
		id += calculateFaceID(U);
		id += calculateFaceID(D);
		id += calculateFaceID(R);
		id += calculateFaceID(L);

		return id;
	}

	private String calculateFaceID (int[][] f)
	{
		String fid = "";

		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
				fid += Integer.toString(f[i][j]);
			}
		}

		return fid;
	}

}
