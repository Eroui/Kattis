import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class SlidingTiles {
	
	Kattio io;
	int leftBound, upBound, rightBound, downBound;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numOps = io.nextInt();
			if (numOps == -1) {
				break;
			}
			char[][] mat = new char[105][105];
			for (int i = 0; i < mat.length; i++) {
				Arrays.fill(mat[i], ' ');
			}
			leftBound = 50;
			upBound = 50;
			rightBound = 54;
			downBound = 54;
			for (int r = 50; r < 55; r++) {
				for (int c = 50; c < 55; c++) {
					mat[r][c] = (char)((r-50)*5+(c-50)+'A');
				}
			}
			for (int i = 0; i < numOps; i++) {
				char move = io.next().charAt(0);
				String dir = io.next();
				int r = 0;
				int c = 0;
				OUTER:
				for (c = leftBound; c <= rightBound; c++) {
					for (r = upBound; r <= downBound; r++) {
						if (mat[r][c] == move) {
							break OUTER;
						}
					}
				}
//				io.printf("%d %d %s %s%n", r, c, move, mat[r][c]);
				boolean shrink = true;
				switch (dir) {
				case "up":
					push(r,c,mat,-1,0);
					for (int e = leftBound; e <= rightBound; e++) {
						if (mat[downBound][e] != ' ') {
							shrink = false;
							break;
						}
					}
					if (shrink) {
						downBound--;
					}
					break;
				case "right":
					push(r,c,mat,0,1);
					for (int e = upBound; e <= downBound; e++) {
						if (mat[e][leftBound] != ' ') {
							shrink = false;
							break;
						}
					}
					if (shrink) {
						leftBound++;
					}
					break;
				case "down":
					push(r,c,mat,1,0);
					for (int e = leftBound; e <= rightBound; e++) {
						if (mat[upBound][e] != ' ') {
							shrink = false;
							break;
						}
					}
					if (shrink) {
						upBound++;
					}
					break;
				case "left":
					push(r,c,mat,0,-1);
					for (int e = upBound; e <= downBound; e++) {
						if (mat[e][rightBound] != ' ') {
							shrink = false;
							break;
						}
					}
					if (shrink) {
						rightBound--;
					}
					break;
				}
			}
			for (int r = upBound; r <= downBound; r++) {
				StringBuilder sb = new StringBuilder();
				for (int c = leftBound; c <= rightBound; c++) {
					sb.append(mat[r][c]);
				}
				io.println(sb.toString().replaceAll(" *$", ""));
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public void push(int r, int c, char[][] mat, int dr, int dc) {
		if (mat[r+dr][c+dc] != ' ') {
			push(r+dr,c+dc,mat,dr,dc);
		} else {
			rightBound = Math.max(c+dc,rightBound);
			upBound = Math.min(r+dr, upBound);
			leftBound = Math.min(c+dc,leftBound);
			downBound = Math.max(r+dr,downBound);
		}
		mat[r+dr][c+dc] = mat[r][c];
		mat[r][c] = ' ';
	}
	
	public static void main(String[] args) {
		new SlidingTiles().go();
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
