import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Ski {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numSlopes = io.nextInt();
		int numQueries = io.nextInt();
		int[] heights = new int[numSlopes+1];
		long[] fun = new long[numSlopes];
		int[] costs = new int[numSlopes];
		long[] sums = new long[numSlopes];
		for (int i = 0; i < heights.length; i++) {
			heights[i] = io.nextInt();
			if (i > 0) {
				fun[i-1] = heights[i-1] - heights[i];
				fun[i-1] *= Math.abs(fun[i-1]);
				costs[i-1] = heights[i-1] + heights[i];
			}
		}
		for (int q = 0; q < numQueries; q++) {
			int op = io.nextInt();
			if (op == -1) {
				break;
			}
			if (op == 0) {
				int i = io.nextInt();
				int newHeight = io.nextInt();
				heights[i] = newHeight;
				if (i > 0) {
					fun[i-1] = heights[i-1] - heights[i];
					fun[i-1] *= Math.abs(fun[i-1]);
					costs[i-1] = heights[i-1] + heights[i];
				}
				if (i < numSlopes) {
					fun[i] = heights[i] - heights[i+1];
					fun[i] *= Math.abs(fun[i]);
					costs[i] = heights[i] + heights[i+1];
				}
			} else {
				int cabin = io.nextInt();
				long budget = io.nextLong();
				long maxSum = 0;
				for (int i = 0; i < numSlopes; i++) {
					if (i >= cabin) {
						if (budget - costs[i] < 0) {
							break;
						}
						budget -= costs[i];
					}
					if (i > 0) {
						sums[i] = Math.max(sums[i-1]+fun[i], fun[i]);
					} else {
						sums[i] = fun[i];
					}
					maxSum = Math.max(maxSum, sums[i]);
				}
				io.println(maxSum);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Ski().go();
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
