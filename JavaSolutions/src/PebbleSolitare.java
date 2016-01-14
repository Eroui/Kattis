import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PebbleSolitare {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			char[] ar = io.nextLine().toCharArray();
			int count = 0;
			for (char c : ar) {
				if (c == 'o') {
					count++;
				}
			}
			io.println(findBest(ar, count));
		}
		
		io.flush();
		io.close();
	}
	
	public int findBest(char[] ar, int count) {
		int best = count;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == 'o') {
				if (i < ar.length-2 && ar[i+1] == 'o' && ar[i+2] == '-') {
					ar[i] = '-';
					ar[i+1] = '-';
					ar[i+2] = 'o';
					best = Math.min(findBest(ar, count-1), best);
					ar[i] = 'o';
					ar[i+1] = 'o';
					ar[i+2] = '-';
				}
				if (i > 2 && ar[i-1] == 'o' && ar[i-2] == '-') {
					ar[i] = '-';
					ar[i-1] = '-';
					ar[i-2] = 'o';
					best = Math.min(findBest(ar, count-1), best);
					ar[i] = 'o';
					ar[i-1] = 'o';
					ar[i-2] = '-';
				}
			}
		}
		return best;
	}

	public static void main(String[] args) {
		new PebbleSolitare().go();
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
