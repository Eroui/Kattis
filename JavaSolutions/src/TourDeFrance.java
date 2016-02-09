import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class TourDeFrance {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (io.hasNext()) {
			int numFront = io.nextInt();
			if (numFront == 0) {
				break;
			}
			int numRear = io.nextInt();
			int[] front = new int[numFront];
			int[] rear = new int[numRear];
			double[] ratios = new double[numFront*numRear];
			for (int i = 0; i < numFront; i++) {
				front[i] = io.nextInt();
			}
			for (int i = 0; i < numRear; i++) {
				rear[i] = io.nextInt();
			}
			int i = 0;
			for (int f : front) {
				for (int r : rear) {
					ratios[i++] = (double)r/f;
				}
			}
			Arrays.sort(ratios);
			double max = 0;
			for (i = 1; i < ratios.length; i++) {
				max = Math.max(max, ratios[i]/ratios[i-1]);
			}
			io.printf("%.2f%n", max);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new TourDeFrance().go();
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
