import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class FunHouse {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int houseNum = 1;
		while (true) {
			int numCols = io.nextInt();
			int numRows = io.nextInt();
			if (numCols == 0 && numRows == 0) {
				break;
			}
			char[][] mat = new char[numRows][numCols];
			int startR = 0, startC = 0;
			for (int r = 0; r < numRows; r++) {
				char[] ar = io.nextLine().trim().toCharArray();
				for (int c = 0; c < numCols; c++) {
					mat[r][c] = ar[c];
					if (mat[r][c] == '*') {
						startR = r;
						startC = c;
					}
				}
			}
			int dir = 0;
			if (startR == 0) {
				dir = 2;
			} else if (startR == numRows-1) {
				dir = 0;
			} else if (startC == 0) {
				dir = 1;
			} else if (startC == numCols-1) {
				dir = 3;
			}
			int[] dr = {-1,0,1,0};
			int[] dc = {0,1,0,-1};
			int r = startR;
			int c = startC;
			while (mat[r][c] != 'x') {
				r += dr[dir];
				c += dc[dir];
				if (mat[r][c] == '/') {
					if (dir == 0 || dir == 2) {
						dir++;
					} else {
						dir--;
					}
				} else if (mat[r][c] == '\\') {
					if (dir == 0 || dir == 2) {
						dir = (dir+4-1)%4;
					} else {
						dir = (dir+1)%4;
					}
				}
			}
			mat[r][c] = '&';
			io.printf("HOUSE %d%n", houseNum++);
			for (char[] ar : mat) {
				io.println(ar);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new FunHouse().go();
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
