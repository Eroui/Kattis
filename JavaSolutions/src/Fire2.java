import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Fire2 {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numCols = io.nextInt();
			int numRows = io.nextInt();
			char[][] map = new char[numRows][];
			for (int i = 0; i < numRows; i++) {
				map[i] = io.nextLine().trim().toCharArray();
			}
			int startR = 0, startC = 0;
			int[][] fire = new int[numRows][numCols];
			int[][] player = new int[numRows][numCols];
			for (int i = 0; i < numRows; i++) {
				Arrays.fill(fire[i], Integer.MAX_VALUE);
				Arrays.fill(player[i], Integer.MAX_VALUE);
			}
			for (int r = 0; r < numRows; r++) {
				for (int c = 0; c < numCols; c++) {
					if (map[r][c] == '*') {
						map[r][c] = '.';
						floodFire(fire, map, r, c, 0);
					} else if (map[r][c] == '@') {
						startR = r;
						startC = c;
					}
				}
			}
			map[startR][startC] = '.';
			int best = floodPlayer(player, fire, map, startR, startC, 0);
			if (best == Integer.MAX_VALUE) {
				io.println("IMPOSSIBLE");
			} else {
				io.println(best+1);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public void floodFire(int[][] fire, char[][] map, int r, int c, int p) {
		if (inBounds(map, r, c) && (map[r][c] == '.' || map[r][c] == '@') && p < fire[r][c]) {
			fire[r][c] = p;
			floodFire(fire, map, r+1, c, p+1);
			floodFire(fire, map, r-1, c, p+1);
			floodFire(fire, map, r, c+1, p+1);
			floodFire(fire, map, r, c-1, p+1);
		}
	}
	
	public int floodPlayer(int[][] player, int[][] fire, char[][] map, int r, int c, int p) {
		if (inBounds(map,r,c) && map[r][c] == '.' && p < fire[r][c] && p < player[r][c]) {
			player[r][c] = p;
			if (r == 0 || r == map.length-1 || c == 0 || c == map[r].length-1) {
				return p;
			}
			return Math.min(Math.min(floodPlayer(player, fire, map, r+1, c, p+1), floodPlayer(player, fire, map, r-1, c, p+1)), 
				Math.min(floodPlayer(player, fire, map, r, c+1, p+1), floodPlayer(player, fire, map, r, c-1, p+1)));
		}
		return Integer.MAX_VALUE;
	}
	
	public void printMat(int[][] mat) {
		for (int[] ar : mat) {
			out.println(Arrays.toString(ar));
		}
	}
	
	public boolean inBounds(char[][] mat, int r, int c) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new Fire2().go();
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
