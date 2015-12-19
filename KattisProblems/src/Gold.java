import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Gold {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numCols = in.nextInt();
		int numRows = in.nextInt();
		in.nextLine();
		char[][] mat = new char[numRows][];
		int startR = 0, startC = 0;
		for (int i = 0; i < numRows; i++) {
			mat[i] = in.nextLine().trim().toCharArray();
			for (int e = 0; e < numCols; e++) {
				if (mat[i][e] == 'P') {
					startR = i;
					startC = e;
					mat[i][e] = '.';
				}
			}
		}
		int gold = findGold(mat, startR, startC);
		out.println(gold);
		
		in.close();
	}
	
	public int findGold(char[][] mat, int r, int c) {
		if (!inBounds(r, c, mat) || mat[r][c] == '#') {
			return 0;
		}
		int sum = 0;
		if (mat[r][c] == 'G') {
			sum++;
		}
		mat[r][c] = '#';
		if (trap(mat, r, c)) {
			return sum;
		}
		return sum + findGold(mat, r+1, c) + findGold(mat, r-1, c) + findGold(mat, r, c+1) + findGold(mat, r, c-1);
	}
	
	public boolean trap(char[][] mat, int r, int c) {
		return r > 0 && mat[r-1][c] == 'T'
				|| c > 0 && mat[r][c-1] == 'T'
				|| r < mat.length-1 && mat[r+1][c] == 'T'
				|| c < mat[r].length-1 && mat[r][c+1] == 'T';
	}
	
	public boolean inBounds(int r, int c, char[][] mat) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new Gold().go();
	}
}
