import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class EightQueens {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		char[][] mat = new char[8][];
		int numQueens = 0;
		for (int r = 0; r < 8; r++) {
			mat[r] = io.next().trim().toCharArray();
		}
		boolean worked = true;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (mat[r][c] == '*') {
					numQueens++;
					if (!check(mat, r, c)) {
						worked = false;
						break;
					}
				}
			}
		}
		
		if (worked && numQueens == 8) {
			io.println("valid");
		} else {
			io.println("invalid");
		}
		io.flush();
		io.close();
	}
	
	public boolean check(char[][] board, int r, int c) {
		board[r][c] = '.';
		for (int i = 0; i < board.length; i++) {
			if (board[i][c] != '.' || board[r][i] != '.') {
				return false;
			}
		}
		for (int i = Math.max(r-c, 0), e = Math.max(c-r, 0); i < board.length && e < board.length; i++, e++) {
			if (board[i][e] != '.') {
				return false;
			}
		}
		for (int i = Math.min(r+c, board.length-1), e = Math.max(c-(board.length-1-r), 0); i >= 0 && e < board.length; i--, e++) {
			if (board[i][e] != '.') {
				return false;
			}
		}
		board[r][c] = '*';
		return true;
	}
	
	public static void main(String[] args) {
		new EightQueens().go();
	}
	
	private class Kattio extends PrintWriter {

	    private BufferedReader r;
	    private String line;
	    private StringTokenizer st;
	    private String token;
		
	    public Kattio(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
	    }
	    public Kattio(InputStream i, OutputStream o) {
			super(new BufferedOutputStream(o));
			r = new BufferedReader(new InputStreamReader(i));
	    }

	    public boolean hasNext() {
	    	return peekToken() != null;
	    }

	    public int nextInt() {
	    	return Integer.parseInt(nextToken());
	    }

	    public double nextDouble() { 
	    	return Double.parseDouble(nextToken());
	    }

	    public long nextLong() {
	    	return Long.parseLong(nextToken());
	    }

	    public String next() {
	    	return nextToken();
	    }

	    private String peekToken() {
			if (token == null) 
			    try {
					while (st == null || !st.hasMoreTokens()) {
					    line = r.readLine();
					    if (line == null) return null;
					    st = new StringTokenizer(line);
					}
					token = st.nextToken();
			    } catch (IOException e) { }
			return token;
	    }

	    private String nextToken() {
			String ans = peekToken();
			token = null;
			return ans;
	    }
	}
}
