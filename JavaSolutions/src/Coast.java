import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Coast {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numRows = io.nextInt();
		int numCols = io.nextInt();
		char[][] mat = new char[numRows][];
		boolean[][] water = new boolean[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			mat[i] = io.nextLine().trim().toCharArray();
		}
		for (int r = 0; r < numRows; r++) {
			if (r == 0 || r == numRows-1) {
				for (int c = 0; c < numCols; c++) {
					findWater(mat, water, r, c);
				}
			} else {
				findWater(mat, water, r, 0);
				findWater(mat, water, r, numCols-1);
			}
		}
		int coastSize = 0;
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				if (mat[r][c] == '1') {
					if (r == 0) {
						coastSize++;
					}
					if (r == numRows-1) {
						coastSize++;
					}
					if (c == 0) {
						coastSize++;
					}
					if (c == numCols-1) {
						coastSize++;
					}
					coastSize += r > 0 && water[r-1][c] ? 1 : 0;
					coastSize += c > 0 && water[r][c-1] ? 1 : 0;
					coastSize += r < numRows-1 && water[r+1][c] ? 1 : 0;
					coastSize += c < numCols-1 && water[r][c+1] ? 1 : 0;
				}
			}
		}
		io.println(coastSize);
		
		io.flush();
		io.close();
	}
	
	public void findWater(char[][] mat, boolean[][] water, int r, int c) {
		if (inBounds(mat, r, c) && mat[r][c] == '0' && !water[r][c]) {
			water[r][c] = true;
			findWater(mat, water, r+1, c);
			findWater(mat, water, r-1, c);
			findWater(mat, water, r, c+1);
			findWater(mat, water, r, c-1);
		}
	}
	
	public boolean inBounds(char[][] mat, int r, int c) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new Coast().go();
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
