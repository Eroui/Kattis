import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ForestFires {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);	
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			int[][] residual = new int[100][100];
			for (int i = 0; i < residual.length; i++) {
				Arrays.fill(residual[i], -1);
			}
			int r = Integer.parseInt(next);
			r = (r*5171+13297) % 50021;
			int numIterations = io.nextInt();
			int[] trees = new int[numIterations];
			int count = 0;
			for (int i = 0; i < numIterations; i++) {
				int square = r % 10000;
				r = (r*5171+13297) % 50021;
				while (residual[square/100][square%100] != -1) {
					square = r % 10000;
					r = (r*5171+13297) % 50021;
				}
				int surround = square;
				if (square/100 > 0) {
					surround = Math.max(surround, residual[square/100-1][square%100]);
				}
				if (square/100 < 99) {
					surround = Math.max(surround, residual[square/100+1][square%100]);
				}
				if (square%100 > 0) {
					surround = Math.max(surround, residual[square/100][square%100-1]);
				}
				if (square%100 < 99) {
					surround = Math.max(surround, residual[square/100][square%100+1]);
				}
				residual[square/100][square%100] = surround;
				flood(square/100+1, square%100, surround, residual);
				flood(square/100-1, square%100, surround, residual);
				flood(square/100, square%100+1, surround, residual);
				flood(square/100, square%100-1, surround, residual);
				trees[i] = square;
				int a = trees[r % (i+1)];
				r = (r*5171+13297) % 50021;
				int b = trees[r % (i+1)];
				r = (r*5171+13297) % 50021;
				if (residual[a/100][a%100] == residual[b/100][b%100] && residual[a/100][a%100] != -1) {
					count++;
				}
				if (i % 100 == 99) {
					if (i != 99) {
						io.print(" ");
					}
					io.print(count);
					count = 0;
				}
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public void flood(int x, int y, int rep, int[][] mat) {
		if (inBounds(x,y,mat) && mat[x][y] != -1 && mat[x][y] != rep) {
			mat[x][y] = rep;
			flood(x-1,y,rep,mat);
			flood(x+1,y,rep,mat);
			flood(x,y-1,rep,mat);
			flood(x,y+1,rep,mat);
		}
	}
	
	public boolean inBounds(int r, int c, int[][] mat) {
		return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
	}
	
	public static void main(String[] args) {
		new ForestFires().go();
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
