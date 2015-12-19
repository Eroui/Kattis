import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Queens {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numQueens = in.nextInt();
		boolean[][] board = new boolean[numQueens][numQueens];
		boolean wrong = false;
//		in.nextInt();
//		in.nextInt();
		for (int i = 0; i < numQueens; i++) {
			if (!placePiece(board, in.nextInt(), in.nextInt())) {
				wrong = true;
				break;
			}
		}
		if (wrong) {
			out.println("INCORRECT");
		} else {
			out.println("CORRECT");
		}
		
		in.close();
	}
	
	public boolean placePiece(boolean[][] board, int r, int c) {
		if (board[r][c]) {
			return false;
		}
		for (int i = 0; i < board.length; i++) {
			board[i][c] = true;
			board[r][i] = true;
		}
		for (int i = Math.max(r-c, 0), e = Math.max(c-r, 0); i < board.length && e < board.length; i++, e++) {
			board[i][e] = true;
		}
		for (int i = Math.min(r+c, board.length-1), e = Math.max(c-(board.length-1-r), 0); i >= 0 && e < board.length; i--, e++) {
			board[i][e] = true;
		}
//		printMat(board);
//		out.println();
		return true;
	}
	
	public void printMat(boolean[][] mat) {
		for (boolean[] ar : mat) {
			out.println(Arrays.toString(ar).replaceAll("alse","").replaceAll("rue",""));
		}
	}
	
	public static void main(String[] args) {
		new Queens().go();
	}
}
