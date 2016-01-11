import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PervasiveHeartMonitor {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		String next;
		while (true) {
			next = io.nextLine();
			if (next == null) {
				break;
			}
			String[] line = next.split(" ");
			StringBuilder sb = new StringBuilder();
			double total = 0;
			int n = 0;
			for (String s : line) {
				try {
					total += Double.parseDouble(s);
					n++;
				} catch (NumberFormatException e) {
					sb.append(s).append(" ");
				}
			}
			io.printf("%.6f %s%n", total/n, sb.toString().trim());
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new PervasiveHeartMonitor().go();
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
