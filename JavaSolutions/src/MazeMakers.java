import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class MazeMakers {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numRows = io.nextInt();
			int numCols = io.nextInt();
			if (numRows == 0 && numCols == 0) {
				break;
			}
			int[][] mat = new int[numRows][numCols];
			int startR = -1, startC = -1, endR = -1, endC = -1;
			for (int r = 0; r < numRows; r++) {
				char[] line = io.nextLine().trim().toCharArray();
				for (int c = 0; c < numCols; c++) {
					char n = line[c];
					if (n-'0' > 9) {
						mat[r][c] = n-'A'+10;
					} else {
						mat[r][c] = n-'0';
					}
					if (r == 0 && bit(mat[r][c], 0) == 0 || r == numRows-1 && bit(mat[r][c], 2) == 0
							|| c == 0 && bit(mat[r][c], 3) == 0 || c == numCols-1 && bit(mat[r][c], 1) == 0) {
						if (startR == -1) {
							startR = r;
							startC = c;
						} else {
							endR = r;
							endC = c;
						}
					}
				}
			}
			if (startR == 0) {
				mat[startR][startC] |= 8;
			}
			if (startR == numRows-1) {
				mat[startR][startC] |= 2;
			}
			if (startC == 0) {
				mat[startR][startC] |= 1;
			}
			if (startC == numCols-1) {
				mat[startR][startC] |= 4;
			}
			if (endR == 0) {
				mat[endR][endC] |= 8;
			}
			if (endR == numRows-1) {
				mat[endR][endC] |= 2;
			}
			if (endC == 0) {
				mat[endR][endC] |= 1;
			}
			if (endC == numCols-1) {
				mat[endR][endC] |= 4;
			}
			boolean[][] checked = new boolean[numRows][numCols];
			boolean allCovered = true;
			boolean overlap = flood(mat, checked, startR, startC, -1, -1);
			for (int i = 0; i < mat.length; i++) {
				for (int e = 0; e < mat[0].length; e++) {
					allCovered &= checked[i][e];
				}
			}
			if (!checked[endR][endC]) {
				io.println("NO SOLUTION");
			} else if (!allCovered) {
				io.println("UNREACHABLE CELL");
			} else if (overlap) {
				io.println("MULTIPLE PATHS");
			} else {
				io.println("MAZE OK");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public boolean flood(int[][] mat, boolean[][] checked, int r, int c, int pr, int pc) {
		checked[r][c] = true;
		boolean overlap = false;
		if (bit(mat[r][c], 0) == 0) {
			if (pr != -1 && pr != r-1 && checked[r-1][c]) {
				overlap = true;
			}
			if (!checked[r-1][c]) {
				overlap |= flood(mat, checked, r-1, c, r, c);
			}
		}
		if (bit(mat[r][c], 1) == 0) {
			if (pr != -1 && pc != c+1 && checked[r][c+1]) {
				overlap = true;
			}
			if (!checked[r][c+1]) {
				overlap |= flood(mat, checked, r, c+1, r, c);
			}
		}
		if (bit(mat[r][c], 2) == 0) {
			if (pr != -1 && pr != r+1 && checked[r+1][c]) {
				overlap = true;
			}
			if (!checked[r+1][c]) {
				overlap |= flood(mat, checked, r+1, c, r, c);
			}
		}
		if (bit(mat[r][c], 3) == 0) {
			if (pr != -1 && pc != c-1 && checked[r][c-1]) {
				overlap = true;
			}
			if (!checked[r][c-1]) {
				overlap |= flood(mat, checked, r, c-1, r, c);
			}
		}
		return overlap;
	}
	
	public int bit(int i, int r) {
		return (i >> (3-r)) % 2;
	}
	
	public static void main(String[] args) {
		new MazeMakers().go();
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
