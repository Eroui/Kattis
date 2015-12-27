import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class TrainPassengers {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int capacity = io.nextInt();
		int numStations = io.nextInt();
		long curr = 0;
		boolean possible = true;
		for (int i = 0; i < numStations; i++) {
			int off = io.nextInt();
			int on = io.nextInt();
			int left = io.nextInt();
			curr -= off;
			curr += on;
			if (curr > capacity || curr < 0 || curr < capacity && left > 0 || i == numStations-1 && (left > 0 || on > 0)) {
				possible = false;
				break;
			}
		}
		if (possible && curr == 0) {
			io.println("possible");
		} else {
			io.println("impossible");
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new TrainPassengers().go();
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
