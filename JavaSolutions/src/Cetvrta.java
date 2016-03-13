import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Cetvrta {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int x1 = io.nextInt();
		int y1 = io.nextInt();
		int x2 = io.nextInt();
		int y2 = io.nextInt();
		int x3 = io.nextInt();
		int y3 = io.nextInt();
		int x4, y4;
		if (x1 == x2) {
			x4 = x3;
		} else if (x1 == x3) {
			x4 = x2;
		} else {
			x4 = x1;
		}
		if (y1 == y2) {
			y4 = y3;
		} else if (y1 == y3) {
			y4 = y2;
		} else {
			y4 = y1;
		}
		io.printf("%d %d", x4, y4);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Cetvrta().go();
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
