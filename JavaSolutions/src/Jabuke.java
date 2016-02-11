import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

import java.awt.Polygon;

public class Jabuke {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int[] xpoints = new int[3];
		int[] ypoints = new int[3];
		for (int i = 0; i < 3; i++) {
			xpoints[i] = io.nextInt();
			ypoints[i] = io.nextInt();
		}
		double area = Math.abs(xpoints[0]*(ypoints[1]-ypoints[2]) + xpoints[1]*(ypoints[2]-ypoints[0]) + xpoints[2]*(ypoints[0]-ypoints[1]))/2.0;
		int numPoints = io.nextInt();
		int inside = 0;
		for (int i = 0; i < numPoints; i++) {
			int x = io.nextInt();
			int y = io.nextInt();
			double a1 = Math.abs(x*(ypoints[1]-ypoints[2]) + xpoints[1]*(ypoints[2]-y) + xpoints[2]*(y-ypoints[1]))/2.0;
			double a2 = Math.abs(xpoints[0]*(y-ypoints[2]) + x*(ypoints[2]-ypoints[0]) + xpoints[2]*(ypoints[0]-y))/2.0;
			double a3 = Math.abs(xpoints[0]*(ypoints[1]-y) + xpoints[1]*(y-ypoints[0]) + x*(ypoints[0]-ypoints[1]))/2.0;
			if (Math.abs(a1+a2+a3-area) < 0.001) {
				inside++;
			}
		}
		io.printf("%.1f%n", area);
		io.println(inside);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Jabuke().go();
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
