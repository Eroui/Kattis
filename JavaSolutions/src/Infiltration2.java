import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Infiltration2 {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int currCase = 1;
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			int numCells = Integer.parseInt(next);
			boolean[][] mat = new boolean[numCells][numCells];
			boolean[] used = new boolean[numCells];
			int[] count = new int[numCells];
			for (int i = 0; i < numCells; i++) {
				char[] line = io.nextLine().toCharArray();
				for (int e = 0; e < numCells; e++) {
					if (line[e] == '1') {
						mat[i][e] = true;
						count[i]++;
					}
				}
				mat[i][i] = true;
				count[i]++;
			}
			int numFilled = 0;
			int numInfiltrated = 0;
			boolean[] currCaptured = new boolean[numCells];
			while (numFilled < numCells) {
				int nextCheck = 0;
				for (; nextCheck < numCells; nextCheck++) {
					if (!currCaptured[nextCheck]) {
						break;
					}
				}
				int maxIndex = -1;
				for (int i = 0; i < numCells; i++) {
					if (mat[i][nextCheck] && (maxIndex == -1 || count[i] > count[maxIndex])) {
						maxIndex = i;
					}
				}
				numFilled += count[maxIndex];
				numInfiltrated++;
				used[maxIndex] = true;
				boolean[] inverse = new boolean[numCells];
				for (int i = 0; i < numCells; i++) {
					inverse[i] = !mat[maxIndex][i];
					currCaptured[i] |= mat[maxIndex][i];
				}
				for (int i = 0; i < numCells; i++) {
					if (used[i]) {
						continue;
					}
					int c = 0;
					for (int e = 0; e < numCells; e++) {
						mat[i][e] &= inverse[e];
						if (mat[i][e]) {
							c++;
						}
					}
					count[i] = c;
				}
			}
			io.printf("Case %d: %d", currCase++, numInfiltrated);
			for (int i = 0; i < numCells; i++) {
				if (used[i]) {
					io.printf(" %d", i+1);
				}
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Infiltration2().go();
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
