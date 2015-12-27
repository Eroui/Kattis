import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class CountingStars {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int currCase = 1;
		while (in.hasNextInt()) {
			int numRows = in.nextInt();
			int numCols = in.nextInt();
			in.nextLine();
			char[][] mat = new char[numRows][];
			for (int i = 0; i < numRows; i++) {
				mat[i] = in.nextLine().trim().toCharArray();
			}
			int numStars = 0;
			for (int r = 0; r < numRows; r++) {
				for (int c = 0; c < numCols; c++) {
					if (mat[r][c] == '-') {
						fillStar(mat, r, c);
						numStars++;
					}
				}
			}
			out.printf("Case %d: %d%n", currCase++, numStars);
		}
		
		in.close();
	}
	
	public void fillStar(char[][] mat, int r, int c) {
		if (inBounds(r, c, mat) && mat[r][c] == '-') {
			mat[r][c] = '#';
			fillStar(mat, r+1, c);
			fillStar(mat, r-1, c);
			fillStar(mat, r, c+1);
			fillStar(mat, r, c-1);
		}
	}
	
	public boolean inBounds(int r, int c, char[][] mat) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new CountingStars().go();
	}
}
