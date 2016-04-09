import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Cake {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numRows = io.nextInt();
		int numCols = io.nextInt();
		int numRoses = io.nextInt();
		int[][] roses = new int[numRoses][2];
		boolean[] rowHasRose = new boolean[numRows];
		for (int i = 0; i < numRoses; i++) {
			roses[i][0] = io.nextInt();
			roses[i][1] = io.nextInt();
			rowHasRose[roses[i][0]-1] = true;
		}
		Arrays.sort(roses, new Comparator<int[]>() {
			public int compare(int[] one, int[] two) {
				if (one[0] == two[0]) {
					return one[1] - two[1];
				}
				return one[0] - two[0];
			}
		});
//		for (int i = 0; i < numRoses; i++) {
//			io.println(Arrays.toString(roses[i]));
//		}
		int topBound = 1;
		int leftBound = 1;
		for (int i = 0; i < numRoses-1; i++) {
			io.printf("%d %d ", topBound, leftBound);
			int r;
			for (r = roses[i][0]; r < numRows; r++) {
				if (rowHasRose[r]) {
					break;
				}
			}
			io.printf("%d ", r);
			if (roses[i+1][0] != roses[i][0]) {
				io.printf("%d%n", numCols);
				topBound = roses[i+1][0];
				leftBound = 1;
			} else {
				io.printf("%d%n", roses[i][1]);
				leftBound = roses[i][1]+1;
			}
		}
		io.printf("%d %d %d %d%n", topBound, leftBound, numRows, numCols);
		io.println(0);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Cake().go();
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
