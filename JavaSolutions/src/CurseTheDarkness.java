import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class CurseTheDarkness {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			double bookX = io.nextDouble();
			double bookY = io.nextDouble();
			int numCandles = io.nextInt();
			double minDist = Integer.MAX_VALUE;
			for (int i = 0; i < numCandles; i++) {
				double x = io.nextDouble();
				double y = io.nextDouble();
				minDist = Math.min(minDist, (bookX-x)*(bookX-x)+(bookY-y)*(bookY-y));
			}
			if (minDist <= 64) {
				io.println("light a candle");
			} else {
				io.println("curse the darkness");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new CurseTheDarkness().go();
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
