import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Skener {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numRows = io.nextInt();
		int numCols = io.nextInt();
		int rowMult = io.nextInt();
		int colMult = io.nextInt();
		char[][] mat = new char[numRows][];
		for (int i = 0; i < numRows; i++) {
			mat[i] = io.next().toCharArray();
		}
		for (int r = 0; r < numRows; r++) {
			for (int i = 0; i < rowMult; i++) {
				for (int c = 0; c < numCols; c++) {
					for (int e = 0; e < colMult; e++) {
						io.print(mat[r][c]);
					}
				}
				io.println();
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Skener().go();
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
