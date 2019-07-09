package Alternative;

public class Cube {

	private Face[] cube;

	public void Cube() {
		char[] color = {'u', 'l', 'f', 'r', 'b', 'd'};
		int N = color.length;

		cube = new Face[N];

		for (int i=0; i<N; i++) {
			cube[i] = new Face(3, color[i]);
		}
	}



}
