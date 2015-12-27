import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BachetsGame {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		while (io.hasNext()) {
			int tableStones = io.nextInt();
			boolean[] dp = new boolean[tableStones+1];
			int numStones = io.nextInt();
			int[] stones = new int[numStones];
			for (int i = 0; i < stones.length; i++) {
				stones[i] = io.nextInt();
			}
			Arrays.sort(stones);
			for (int i = 1; i < dp.length; i++) {
				boolean win = false;
				for (int s : stones) {
					if (i - s == 0) {
						win = true;
						break;
					}
					if (i - s >= 1 && !dp[i-s]) {
						win = true;
					}
				}
				dp[i] = win;
			}
			if (dp[tableStones]) {
				io.println("Stan wins");
			} else {
				io.println("Ollie wins");
			}
		}

		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BachetsGame().go();
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
