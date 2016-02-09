import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Matrix {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int currCase = 1;
		while (io.hasNext()) {
			String sOne = io.next();
			if (sOne.equals("stop")) {
				break;
			}
			int a = Integer.parseInt(sOne);
			int b = io.nextInt();
			int c = io.nextInt();
			int d = io.nextInt();
			double det = (double)a*d-b*c;
			io.printf("Case %d:%n", currCase++);
			io.printf("%d %d%n", Math.round(d/det), Math.round(-b/det));
			io.printf("%d %d%n", Math.round(-c/det), Math.round(a/det));
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Matrix().go();
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
