import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Chess {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int startR = io.next().charAt(0)-'A';
			int startC = io.nextInt()-1;
			int endR = io.next().charAt(0)-'A';
			int endC = io.nextInt()-1;
			if (Math.abs(endC-startC+endR-startR) % 2 == 1 || startC < 0 || startC > 7 || startR < 0 || startR > 7
					 || endC < 0 || endC > 7 || endR < 0 || endR > 7) {
				io.println("Impossible");
				continue;
			} else if (startR == endR && startC == endC) {
				io.printf("0 %C %d%n", startR+'A', startC+1);
			} else if (startR+startC == endR+endC || endR-startR == endC-startC) {
				io.printf("1 %C %d %C %d%n", startR+'A', startC+1, endR+'A', endC+1);
			} else {
				int move1R = 0;
				int move1C = 0;
				int move2R = 0;
				int move2C = 0;
				if ((startR+startC) % 2 == 0) {
					move1R = (startR+startC)/2;
					move1C = move1R;
					move2R = (endR+endC)/2;
					move2C = move2R;
				} else {
					int off1 = (7-startR-startC)/2;
					int off2 = (endR+endC-7)/2;
					move1R = startR+off1;
					move1C = startC+off1;
					move2R = endR-off2;
					move2C = endC-off2;
				}
				if (move1R == startR && move1C == startC) {
					io.printf("2 %C %d %C %d %C %d%n", startR+'A', startC+1, move2R+'A', move2C+1, endR+'A', endC+1);
				} else if (move2R == endR && move2C == endC) {
					io.printf("2 %C %d %C %d %C %d%n", startR+'A', startC+1, move1R+'A', move1C+1, endR+'A', endC+1);
				} else { 
					io.printf("3 %C %d %C %d %C %d %C %d%n", startR+'A', startC+1, move1R+'A', move1C+1, move2R+'A', move2C+1, endR+'A', endC+1);
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Chess().go();
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
