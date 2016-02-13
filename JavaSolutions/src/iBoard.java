import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class iBoard {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("endendend")) {
				break;
			}
			int numOne = 0;
			int numZero = 0;
			for (int c : line.toCharArray()) {
				for (int i = 0; i < 7; i++) {
					if (c % 2 == 0) {
						numZero++;
					} else {
						numOne++;
					}
					c /= 2;
				}
			}
			if (numOne % 2 == 0 && numZero % 2 == 0) {
				io.println("free");
			} else {
				io.println("trapped");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new iBoard().go();
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
