import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Artichoke {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int p = io.nextInt();
		int a = io.nextInt();
		int b = io.nextInt();
		int c = io.nextInt();
		int d = io.nextInt();
		int n = io.nextInt();
		double[] prices = new double[n];
		for (int i = 1; i <= n; i++) {
			prices[i-1] = p * (Math.sin(a * i + b) + Math.cos(c * i + d) + 2);
		}
		double maxDecline = 0;
		int highest = 0;
		for (int i = 1; i < n; i++) {
			maxDecline = Math.max(maxDecline, prices[highest]-prices[i]);
			if (prices[i] > prices[highest]) {
				highest = i;
			}
		}
		io.println(maxDecline);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Artichoke().go();
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
