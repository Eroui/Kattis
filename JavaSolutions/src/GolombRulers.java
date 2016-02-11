import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class GolombRulers {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("end")) {
				break;
			}
			String[] split = line.split(" ");
			int[][] ticks = new int[split.length][split.length];
			for (int i = 0; i < ticks[0].length; i++) {
				ticks[0][i] = Integer.parseInt(split[i]);
			}
			Arrays.sort(ticks[0]);
			boolean[] lengths = new boolean[ticks[0][ticks.length-1]];
			boolean isRuler = true;
			OUTER:
			for (int r = 1; r < ticks.length; r++) {
				for (int c = 0; c < ticks.length-r; c++) {
					ticks[r][c] = ticks[0][c+r] - ticks[0][c];
					if (lengths[Math.abs(ticks[r][c])-1]) {
						isRuler = false;
						break OUTER;
					}
					lengths[ticks[r][c]-1] = true;
				}
			}
			if (!isRuler) {
				io.println("not a ruler");
				continue;
			}
			boolean isPerfect = true;
			for (int i = 0; i < lengths.length; i++) {
				if (!lengths[i]) {
					if (isPerfect) {
						io.print("missing");
						isPerfect = false;
					}
					io.printf(" %d", i+1);
				}
			}
			if (isPerfect) {
				io.println("perfect");
			} else {
				io.println();
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new GolombRulers().go();
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
