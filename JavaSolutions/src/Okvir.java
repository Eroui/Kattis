import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Okvir {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numRows = io.nextInt();
		int numCols = io.nextInt();
		int upMargin = io.nextInt();
		int leftMargin = io.nextInt();
		int rightMargin = io.nextInt();
		int downMargin = io.nextInt();
		char[][] board = new char[numRows+upMargin+downMargin][numCols+leftMargin+rightMargin];
		char[][] words = new char[numRows][];
		for (int r = 0; r < words.length; r++) {
			words[r] = io.next().toCharArray();
		}
		for (int r = 0; r < board.length; r++) {
			if (r % 2 == 0) {
				for (int c = 0; c < board[r].length; c+=2) {
					board[r][c] = '#';
				}
				for (int c = 1; c < board[r].length; c+=2) {
					board[r][c] = '.';
				}
			} else {
				for (int c = 0; c < board[r].length; c+=2) {
					board[r][c] = '.';
				}
				for (int c = 1; c < board[r].length; c+=2) {
					board[r][c] = '#';
				}
			}
		}
		for (int r = 0; r < words.length; r++) {
			for (int c = 0; c < words[r].length; c++) {
				board[r+upMargin][c+leftMargin] = words[r][c];
			}
		}
		for (char[] row : board) {
			io.println(row);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Okvir().go();
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
	    
	    public String nextLine() {
	    	token = null;
	    	st = null;
	    	try {
	    		return r.readLine();
	    	} catch (IOException e) {
	    		return null;
	    	}
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
