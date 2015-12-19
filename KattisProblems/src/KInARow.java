import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class KInARow {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numGames = in.nextInt();
		int xWon = 0;
		int yWon = 0;
		for (int zz = 0; zz < numGames; zz++) {
			int numCols = in.nextInt();
			int numRows = in.nextInt();
			int length = in.nextInt();
			in.nextLine();
			char[][] mat = new char[numRows][];
			for (int r = 0; r < numRows; r++) {
				mat[r] = in.nextLine().trim().toCharArray();
			}
			int won = checkWin(mat, length);
			if (won == 1) {
				xWon++;
			} else if (won == -1) {
				yWon++;
			}
		}
		out.printf("%d:%d", xWon, yWon);
		
		in.close();
	}
	
	public int checkWin(char[][] mat, int length) {
		int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
		for (int i = 0; i < dr.length; i++) {
			int maxR = Math.min(mat.length, mat.length-dr[i]*length+1);
			for (int r = Math.max(0, -dr[i]*length-1); r < maxR; r++) {
				int maxC = Math.min(mat[r].length, mat[r].length-dc[i]*length+1);
				for (int c = Math.max(0, -dc[i]*length-1); c < maxC; c++) {
					if (mat[r][c] == 'x' && checkDirection(mat, 'x', length, r, c, dr[i], dc[i])) {
						return 1;
					} else if (mat[r][c] == 'o' && checkDirection(mat, 'o', length, r, c, dr[i], dc[i])) {
						return -1;
					}
				}
			}
		}
		return 0;
	}
	
	public boolean checkDirection(char[][] mat, char ch, int length, int r, int c, int dr, int dc) {
		while (length > 0) {
			if (!inBounds(r, c, mat) || mat[r][c] != ch) {
				return false;
			}
			r += dr;
			c += dc;
			length--;
		}
		return true;
	}
	
	public boolean inBounds(int r, int c, char[][] mat) {
		return r >= 0 && c >= 0 && r < mat.length && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new KInARow().go();
	}
}
