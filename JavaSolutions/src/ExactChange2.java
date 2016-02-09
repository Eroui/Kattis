import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ExactChange2 {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int target = io.nextInt();
			int numCoins = io.nextInt();
			int[] coins = new int[numCoins];
			int sum = 0;
			for (int i = 0; i < numCoins; i++) {
				coins[i] = io.nextInt();
				sum += coins[i];
			}
			Arrays.sort(coins);
			int[] dp = new int[sum+1];
			for (int ii = coins.length-1; ii >= 0; ii--) {
                int c = coins[ii];
                for (int i = dp.length-1; i >= 0; i--) {
                    if (i == c) {
                        dp[i] = 1;
                    } else if (i - c > 0 && dp[i-c] != 0) {
                        dp[i] = dp[i-c]+1;
                    }
                }
            }
			for (int i = target; i < dp.length; i++) {
				if (dp[i] != 0) {
					io.printf("%d %d%n", i, dp[i]);
					break;
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ExactChange2().go();
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
