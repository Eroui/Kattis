import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Pivot {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numVals = io.nextInt();
		int[] vals = new int[numVals];
		int[] maxes = new int[numVals];
		int[] mins = new int[numVals];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < vals.length; i++) {
			vals[i] = io.nextInt();
			if (vals[i] > max) {
				max = vals[i];
			}
			maxes[i] = max;
		}
		int min = Integer.MAX_VALUE;
		for (int i = mins.length-1; i >= 0; i--) {
			if (vals[i] < min) {
				min = vals[i];
			}
			mins[i] = min;
		}
		int pivots = 0;
		for (int i = 0; i < vals.length; i++) {
			if ((i == 0 || vals[i] > maxes[i-1]) && (i == vals.length-1 || vals[i] < mins[i+1])) {
				pivots++;
			}
		}
		io.println(pivots);
//		out.println(Arrays.toString(mins));
//		out.println(Arrays.toString(maxes));
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Pivot().go();
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
