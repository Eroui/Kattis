import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Misa {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numRows = io.nextInt();
		int numCols = io.nextInt();
		char[][] mat = new char[numRows][];
		for (int i = 0; i < numRows; i++) {
			mat[i] = io.nextLine().trim().toCharArray();
		}
		int bestR = -1, bestC = -1;
		int bestSurround = 0;
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				if (mat[r][c] == '.') {
					int count = 0;
					count += r > 0 && mat[r-1][c] == 'o' ? 1 : 0;
					count += r > 0 && c > 0 && mat[r-1][c-1] == 'o' ? 1 : 0;
					count += r > 0 && c < numCols-1 && mat[r-1][c+1] == 'o' ? 1 : 0;
					count += c > 0 && mat[r][c-1] == 'o' ? 1 : 0;
					count += c < numCols-1 && mat[r][c+1] == 'o' ? 1 : 0;
					count += r < numRows-1 && c > 0 && mat[r+1][c-1] == 'o' ? 1 : 0;
					count += r < numRows-1 && mat[r+1][c] == 'o' ? 1 : 0;
					count += r < numRows-1 && c < numCols-1 && mat[r+1][c+1] == 'o' ? 1 : 0;
					if (count > bestSurround) {
						bestSurround = count;
						bestR = r;
						bestC = c;
					}
				}
			}
		}
		if (bestR != -1) {
			mat[bestR][bestC] = 'o';
		}
		io.println(findSurround(mat));
		
		io.flush();
		io.close();
	}
	
	public int findSurround(char[][] mat) {
		int count = 0;
		for (int r = 0; r < mat.length; r++) {
			for (int c = 0; c < mat[r].length; c++) {
				if (mat[r][c] == 'o') {
					count += c < mat[r].length-1 && mat[r][c+1] == 'o' ? 1 : 0;
					count += r < mat.length-1 && c > 0 && mat[r+1][c-1] == 'o' ? 1 : 0;
					count += r < mat.length-1 && mat[r+1][c] == 'o' ? 1 : 0;
					count += r < mat.length-1 && c < mat[r].length-1 && mat[r+1][c+1] == 'o' ? 1 : 0;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		new Misa().go();
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
