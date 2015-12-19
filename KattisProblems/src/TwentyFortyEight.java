import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class TwentyFortyEight {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int[][] board = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int e = 0; e < 4; e++) {
				board[i][e] = in.nextInt();
			}
		}
		int dir = in.nextInt();
		switch (dir) {
		case 0:
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board.length; c++) {
					if (board[r][c] != 0) {
						int j = c-1;
						while (j >= 0 && board[r][j] == 0) {
							j--;
						}
						if (j+1 != c) {
							board[r][j+1] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board.length-1; c++) {
					if (board[r][c] == board[r][c+1]) {
						board[r][c] *= 2;
						board[r][c+1] = 0;
					}
				}
			}
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board.length; c++) {
					if (board[r][c] != 0) {
						int j = c-1;
						while (j >= 0 && board[r][j] == 0) {
							j--;
						}
						if (j+1 != c) {
							board[r][j+1] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			break;
		case 1:
			for (int c = 0; c < board.length; c++) {
				for (int r = 0; r < board.length; r++) {
					if (board[r][c] != 0) {
						int j = r-1;
						while (j >= 0 && board[j][c] == 0) {
							j--;
						}
						if (j+1 != r) {
							board[j+1][c] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			for (int c = 0; c < board.length; c++) {
				for (int r = 0; r < board.length-1; r++) {
					if (board[r][c] == board[r+1][c]) {
						board[r][c] *= 2;
						board[r+1][c] = 0;
					}
				}
			}
			for (int c = 0; c < board.length; c++) {
				for (int r = 0; r < board.length; r++) {
					if (board[r][c] != 0) {
						int j = r-1;
						while (j >= 0 && board[j][c] == 0) {
							j--;
						}
						if (j+1 != r) {
							board[j+1][c] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			break;
		case 2:
			for (int r = 0; r < board.length; r++) {
				for (int c = board.length-1; c >= 0; c--) {
					if (board[r][c] != 0) {
						int j = c+1;
						while (j < board.length && board[r][j] == 0) {
							j++;
						}
						if (j-1 != c) {
							board[r][j-1] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			for (int r = 0; r < board.length; r++) {
				for (int c = board.length-1; c > 0; c--) {
					if (board[r][c] == board[r][c-1]) {
						board[r][c] *= 2;
						board[r][c-1] = 0;
					}
				}
			}
			for (int r = 0; r < board.length; r++) {
				for (int c = board.length-1; c >= 0; c--) {
					if (board[r][c] != 0) {
						int j = c+1;
						while (j < board.length && board[r][j] == 0) {
							j++;
						}
						if (j-1 != c) {
							board[r][j-1] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			break;
		case 3:
			for (int c = 0; c < board.length; c++) {
				for (int r = board.length-1; r >= 0; r--) {
					if (board[r][c] != 0) {
						int j = r+1;
						while (j < board.length && board[j][c] == 0) {
							j++;
						}
						if (j-1 != r) {
							board[j-1][c] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			for (int c = 0; c < board.length; c++) {
				for (int r = board.length-1; r > 0; r--) {
					if (board[r][c] == board[r-1][c]) {
						board[r][c] *= 2;
						board[r-1][c] = 0;
					}
				}
			}
			for (int c = 0; c < board.length; c++) {
				for (int r = board.length-1; r >= 0; r--) {
					if (board[r][c] != 0) {
						int j = r+1;
						while (j < board.length && board[j][c] == 0) {
							j++;
						}
						if (j-1 != r) {
							board[j-1][c] = board[r][c];
							board[r][c] = 0;
						}
					}
				}
			}
			break;
		}
		for (int[] ar : board) {
			out.println(Arrays.toString(ar).replaceAll("[\\[\\],]", ""));
		}
	}
	
	public static void main(String[] args) {
		new TwentyFortyEight().go();
	}
}
