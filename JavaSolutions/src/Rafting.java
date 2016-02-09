import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Rafting {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numInner = io.nextInt();
			double[][] inner = new double[numInner][2];
			for (int i = 0; i < numInner; i++) {
				inner[i][0] = io.nextDouble();
				inner[i][1] = io.nextDouble();
			}
			int numOuter = io.nextInt();
			double[][] outer = new double[numOuter][2];
			for (int i = 0; i < numOuter; i++) {
				outer[i][0] = io.nextDouble();
				outer[i][1] = io.nextDouble();
			}
			double min = Double.MAX_VALUE;
			for (int i = 0; i < numInner; i++) {
				for (int e = 0; e < numOuter; e++) {
					min = Math.min(min, dist(inner[i], outer[i], outer[(i+1)%numOuter]));
				}
			}
			io.println(min/2);
		}
		
		io.flush();
		io.close();
	}
	
	public double dist(double[] p, double[] l1, double[] l2) {
		return 0;
	}
	
	public static void main(String[] args) {
		new Rafting().go();
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
