import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class SidewaysSorting {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		boolean first = true;
		while (true) {
			int numRows = io.nextInt();
			int numCols = io.nextInt();
			if (numRows == 0 && numCols == 0) {
				break;
			}
			if (!first) {
				io.println();
			}
			char[][] mat = new char[numCols][numRows];
			for (int i = 0; i < numRows; i++) {
				char[] ar = io.nextLine().trim().toCharArray();
				for (int e = 0; e < numCols; e++) {
					mat[e][i] = ar[e];
				}
			}
			Arrays.sort(mat, new Comparator<char[]>() {
				public int compare(char[] one, char[] two) {
					String sOne = new String(one).toLowerCase();
					String sTwo = new String(two).toLowerCase();
					return sOne.compareTo(sTwo);
				}
			});
			for (int i = 0; i < numRows; i++) {
				for (int e = 0; e < numCols; e++) {
					io.print(mat[e][i]);
				}
				io.println();
			}
			first = false;
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new SidewaysSorting().go();
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
