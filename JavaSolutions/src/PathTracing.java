import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PathTracing {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int yMax = 0, yMin = 0, xMax = 0, xMin = 0;
		int x = 0, y = 0;
		LinkedList<Integer> moves = new LinkedList<>();
		while (io.hasNext()) {
			String next = io.next();
			if (next.equals("end")) {
				break;
			}
			switch (next) {
			case "up":
				moves.add(2);
				y++;
				yMax = Math.max(yMax, y);
				break;
			case "down":
				moves.add(0);
				y--;
				yMin = Math.min(yMin, y);
				break;
			case "left":
				moves.add(1);
				x--;
				xMin = Math.min(xMin, x);
				break;
			case "right":
				moves.add(3);
				x++;
				xMax = Math.max(xMax, x);
				break;
			}
		}
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		char[][] mat = new char[yMax-yMin+3][xMax-xMin+3];
		for (char[] ar : mat) {
			Arrays.fill(ar, ' ');
		}
		for (int r = 0; r < mat.length; r++) {
			if (r == 0 || r == mat.length-1) {
				for (int c = 0; c < mat[r].length; c++) {
					mat[r][c] = '#';
				}
			} else {
				mat[r][0] = '#';
				mat[r][mat[r].length-1] = '#';
			}
		}
		int r = yMax-y+1;
		int c = x-xMin+1;
		int startR = r;
		int startC = c;
		while (!moves.isEmpty()) {
			int m = moves.removeLast();
			r += dr[m];
			c += dc[m];
			mat[r][c] = '*';
		}
		mat[startR][startC] = 'E';
		mat[r][c] = 'S';
		for (char[] ar : mat) {
			io.println(ar);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new PathTracing().go();
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
