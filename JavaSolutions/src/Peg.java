import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Peg {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		char[][] mat = new char[7][7];
		for (int i = 0; i < 7; i++) {
			Arrays.fill(mat[i], ' ');
			char[] ar = in.nextLine().toCharArray();
			for (int e = 0; e < ar.length; e++) {
				mat[i][e] = ar[e];
			}
		}
		int sum = 0;
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 7; c++) {
				if (mat[r][c] == 'o') {
					if (inBounds(r-2, c, mat) && mat[r-2][c] == '.' && mat[r-1][c] == 'o') {
						sum++;
					}
					if (inBounds(r+2, c, mat) && mat[r+2][c] == '.' && mat[r+1][c] == 'o') {
						sum++;
					}
					if (inBounds(r, c-2, mat) && mat[r][c-2] == '.' && mat[r][c-1] == 'o') {
						sum++;
					}
					if (inBounds(r, c+2, mat) && mat[r][c+2] == '.' && mat[r][c+1] == 'o') {
						sum++;
					}
				}
			}
		}
		out.println(sum);
	}
	
	public boolean inBounds(int r, int c, char[][] mat) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new Peg().go();
	}
}
