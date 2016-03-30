import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class A1Paper {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numPapers = io.nextInt();
		int[] papers = new int[numPapers];
		for (int i = 1; i < numPapers; i++) {
			papers[i] = io.nextInt();
		}
		double tape = getTape(1, papers, 2);
		if (tape == Double.POSITIVE_INFINITY) {
			io.println("impossible");
		} else {
			io.println(tape);
		}
		
		io.flush();
		io.close();
	}
	
	public double getTape(int i, int[] papers, int need) {
		if (papers.length-1 == i) {
			if (need > papers[i]) {
				return Double.POSITIVE_INFINITY;
			} else {
				return need/2*Math.pow(2, (double)-3/4-1.0/2*(i-1));
			}
		}
		double tape = getTape(i+1, papers, Math.max(0, need-papers[i])*2);
		return tape + need/2*Math.pow(2, (double)-3/4-1.0/2*(i-1));
	}
	
	public static void main(String[] args) {
		new A1Paper().go();
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
