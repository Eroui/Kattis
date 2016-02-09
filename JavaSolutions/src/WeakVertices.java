import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class WeakVertices {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (io.hasNext()) {
			int size = io.nextInt();
			if (size == -1) {
				break;
			}
			int[][] adj = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int e = 0; e < size; e++) {
					adj[i][e] = io.nextInt();
				}
			}
			int[][] square = new int[size][size];
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					int sum = 0;
					for (int i = 0; i < size; i++) {
						sum += adj[r][i] * adj[i][c];
					}
					square[r][c] = sum;
				}
			}
			int[][] cubed = new int[size][size];
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					int sum = 0;
					for (int i = 0; i < size; i++) {
						sum += square[r][i] * adj[i][c];
					}
					cubed[r][c] = sum;
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				if (cubed[i][i] == 0) {
					sb.append(i).append(" ");
				}
			}
			io.println(sb.toString().trim());
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new WeakVertices().go();
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
