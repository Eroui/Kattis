import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Mancala {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int dataSet = io.nextInt();
			int numPebbles = io.nextInt();
			int size = 0;
			int[] board = new int[numPebbles];
			for (int p = 1; p <= numPebbles; p++) {
				for (int i = 0; i < board.length; i++) {
					if (board[i] == 0) {
						size = Math.max(size, i+1);
						for (int e = 0; e < i; e++) {
							board[e]--;
						}
						board[i] = i+1;
						break;
					}
				}
			}
			io.printf("%d %d", dataSet, size);
			for (int i = 0; i < size; i++) {
				if (i%10 == 0) {
					io.println();
				} else {
					io.print(" ");
				}
				io.print(board[i]);
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Mancala().go();
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
