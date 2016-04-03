import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Grille {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int gridSize = io.nextInt();
		char[][] grille = new char[gridSize][];
		int numDots = 0;
		for (int i = 0; i < gridSize; i++) {
			grille[i] = io.nextLine().toCharArray();
			for (int e = 0; e < gridSize; e++) {
				if (grille[i][e] == '.') {
					numDots++;
				}
			}
		}
		String encrypted = io.next();
		if (gridSize%2 == 1 || numDots != gridSize*gridSize/4) {
			if (gridSize == 1 && grille[0][0] == '.') {
				io.print(encrypted);
			} else {
				io.print("invalid grille");
			}
			io.flush();
			io.close();
			return;
		}
		char[][] message = new char[gridSize][gridSize];
		for (char[] ar : message) {
			Arrays.fill(ar, ' ');
		}
		int i = 0;
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				if (grille[r][c] == '.') {
					message[r][c] = encrypted.charAt(i++);
				}
			}
		}
		for (int c = 0; c < gridSize; c++) {
			for (int r = gridSize-1; r >= 0; r--) {
				if (grille[r][c] == '.') {
					message[c][gridSize-1-r] = encrypted.charAt(i++);
				}
			}
		}
		for (int r = gridSize-1; r >= 0; r--) {
			for (int c = gridSize-1; c >= 0; c--) {
				if (grille[r][c] == '.') {
					message[gridSize-1-r][gridSize-1-c] = encrypted.charAt(i++);
				}
			}
		}
		for (int c = gridSize-1; c >= 0; c--) {
			for (int r = 0; r < gridSize; r++) {
				if (grille[r][c] == '.') {
					message[gridSize-1-c][r] = encrypted.charAt(i++);
				}
			}
		}
		boolean works = true;
		for (int r = 0; r < gridSize && works; r++) {
			for (int c = 0; c < gridSize; c++) {
				if (!Character.isLetter(message[r][c])) {
					works = false;
					break;
				}
			}
		}
		if (works) {
			for (char[] ar : message) {
				io.print(ar);
			}
		} else {
			io.println("invalid grille");
		}
 		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Grille().go();
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
