import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class JuryJeopardy {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		io.println(zz);
		for (int zzz = 0; zzz < zz; zzz++) {
			char[] movement = io.nextLine().toCharArray();
			int x = 0, y = 0;
			int minX = 0, maxX = 0, minY = 0, maxY = 0;
			int dir = 1;
			int[] dx = {0,1,0,-1};
			int[] dy = {1,0,-1,0};
			for (char c : movement) {
				switch (c) {
				case 'F':
					break;
				case 'R':
					dir = (dir+1)%4;
					break;
				case 'L':
					dir = (dir+3)%4;
					break;
				case 'B':
					dir = (dir+2)%4;
					break;
				}
				x += dx[dir];
				y += dy[dir];
				minX = Math.min(x, minX);
				minY = Math.min(y, minY);
				maxX = Math.max(x, maxX);
				maxY = Math.max(y, maxY);
			}
			int numRows = maxY-minY+3;
			int numCols = maxX-minX+2;
			io.printf("%d %d%n", numRows, numCols);
			char[][] mat = new char[numRows][numCols];
			for (char[] ar : mat) {
				Arrays.fill(ar,'#');
			}
			x = 0;
			y = numRows-maxY-1;
			dir = 1;
			for (char c : movement) {
				switch (c) {
				case 'F':
					break;
				case 'R':
					dir = (dir+1)%4;
					break;
				case 'L':
					dir = (dir+3)%4;
					break;
				case 'B':
					dir = (dir+2)%4;
					break;
				}
				x += dx[dir];
				y += dy[dir];
				mat[numRows-y][x] = '.';
			}
			for (char[] ar : mat) {
				io.println(ar);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new JuryJeopardy().go();
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
