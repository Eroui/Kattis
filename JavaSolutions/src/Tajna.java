import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Tajna {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		String word = io.next();
		int numRows = 0;
		int numCols = 0;
		for (int i = (int)Math.floor(Math.sqrt(word.length())); i >= 1; i--) {
			if (word.length() % i == 0) {
				numRows = i;
				numCols = word.length()/i;
				break;
			}
		}
		char[][] mat = new char[numRows][numCols];
		for (int c = 0; c < numCols; c++) {
			for (int r = 0; r < numRows; r++) {
				mat[r][c] = word.charAt(c*numRows+r);
			}
		}
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				io.print(mat[r][c]);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Tajna().go();
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
