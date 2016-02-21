import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ThinkingOfANumber {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numBounds = io.nextInt();
			if (numBounds == 0) {
				break;
			}
			int min = 0;
			long max = Long.MAX_VALUE;
			long div = 1;
			for (int i = 0; i < numBounds; i++) {
				String op = io.next();
				io.next();
				switch (op) {
				case "less":
					max = Math.min(io.nextInt(), max);
					break;
				case "greater":
					min = Math.max(io.nextInt(), min);
					break;
				case "divisible":
					div = lcm(div, io.nextInt());
					break;
				}
			}
			if (max == Long.MAX_VALUE) {
				io.println("infinite");
				continue;
			}
			long start = min/div*div+div;
			boolean none = true;
			while (start < max) {
				if (!none) {
					io.print(" ");
				}
				io.print(start);
				start += div;
				none = false;
			}
			if (none) {
				io.println("none");
			} else {
				io.println();
			}
		}
		
		io.flush();
		io.close();
	}
	
	public long lcm(long one, long two) {
		return Math.abs(one*two)/gcd(one,two);
	}
	
	public long gcd(long one, long two) {
		if (two == 0) {
			return one;
		}
		return gcd(two, one%two);
	}
	
	public static void main(String[] args) {
		new ThinkingOfANumber().go();
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
