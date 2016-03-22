import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Savez {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numStrings = io.nextInt();
		String[] strings = new String[numStrings];
		int[] dp = new int[numStrings];
		for (int i = 0; i < numStrings; i++) {
			strings[i] = io.next();
			dp[i] = 1;
		}
		int max = 0;
		for (int i = 0; i < numStrings; i++) {
			for (int e = i-1; e >= 0; e--) {
				if (strings[i].startsWith(strings[e]) && strings[i].endsWith(strings[e]) && dp[e]+1 > dp[i]) {
					dp[i] = dp[e]+1;
					max = Math.max(dp[i], max);
				}
			}
		}
		io.println(max);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Savez().go();
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
