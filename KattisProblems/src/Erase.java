import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Erase {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numPasses = io.nextInt();
		char[] before = io.next().toCharArray();
		char[] after = io.next().toCharArray();
		if (numPasses % 2 == 0) {
			boolean same = true;
			for (int i = 0; i < before.length; i++) {
				if (before[i] != after[i]) {
					same = false;
					break;
				}
			}
			if (same) {
				io.println("Deletion succeeded");
			} else {
				io.println("Deletion failed");
			}
		} else {
			boolean same = true;
			for (int i = 0; i < before.length; i++) {
				if (before[i] == after[i]) {
					same = false;
					break;
				}
			}
			if (same) {
				io.println("Deletion succeeded");
			} else {
				io.println("Deletion failed");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Erase().go();
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
	    	peekToken();
	    	return line;
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
