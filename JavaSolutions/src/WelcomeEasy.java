import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class WelcomeEasy {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		String welcome = "welcome to code jam";
		for (int zzz = 0; zzz < zz; zzz++) {
			String next = io.nextLine();
			int[][] dp = new int[welcome.length()][next.length()];
			for (int w = welcome.length()-1; w >= 0; w--) {
				int sum = 0;
				for (int i = next.length()-(welcome.length()-1-w)-1; i >= 0; i--) {
					if (next.charAt(i) == welcome.charAt(w)) {
						if (w == welcome.length()-1) {
							sum += 1;
						} else {
							sum += dp[w+1][i+1];
						}
					}
					sum %= 10000;
					dp[w][i] = sum;
				}
			}
//			for (int[] ar : dp) {
//				for (int i : ar) {
//					io.printf("%-4d", i);
//				}
//				io.println();
//			}
			io.printf("Case #%d: %04d%n", zzz+1, dp[0][0]);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new WelcomeEasy().go();
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
