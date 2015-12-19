import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class ASCIIFigureRotation {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		boolean isFirst = true;
		while (in.hasNextInt()) {
			int numLines = in.nextInt();
			in.nextLine();
			if (numLines == 0) {
				break;
			}
			if (!isFirst) {
				out.println();
			}
			char[][] mat = new char[numLines][];
			int maxCol = 0;
			for (int i = 0; i < numLines; i++) {
				mat[i] = in.nextLine().toCharArray();
				maxCol = Math.max(mat[i].length, maxCol);
			}
			for (int r = 0; r < mat.length; r++) {
				for (int c = 0; c < mat[r].length; c++) {
					if (mat[r][c] == '-') {
						mat[r][c] = '|';
					} else if (mat[r][c] == '|') {
						mat[r][c] = '-';
					}
				}
			}
			for (int c = 0; c < maxCol; c++) {
				StringBuilder sb = new StringBuilder();
				for (int r = mat.length-1; r >= 0; r--) {
					if (inBounds(r,c,mat)) {
						sb.append(mat[r][c]);
					} else {
						sb.append(' ');
					}
				}
				out.println(sb.toString().replaceAll(" +$", ""));
			}
			isFirst = false;
		}
		
		in.close();
	}
	
	public boolean inBounds(int r, int c, char[][] mat) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new ASCIIFigureRotation().go();
	}
}
