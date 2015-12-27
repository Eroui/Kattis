import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Tutorial {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int operations = io.nextInt();
		long n = io.nextInt();
		int alg = io.nextInt();
		boolean fast = true;
		long val = 1;
		switch (alg) {
		case 1:
			fast = checkFact(operations, 1, n);
			break;
		case 2:
			for (int i = 0; i < n; i++) {
				val *= 2;
				if (val > operations) {
					fast = false;
					break;
				}
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				val *= n;
				if (val > operations) {
					fast = false;
					break;
				}
			}
			break;
		case 4:
			for (int i = 0; i < 3; i++) {
				val *= n;
				if (val > operations) {
					fast = false;
					break;
				}
			}
			break;
		case 5:
			for (int i = 0; i < 2; i++) {
				val *= n;
				if (val > operations) {
					fast = false;
					break;
				}
			}
			break;
		case 6:
			fast = n * Math.log(n)/Math.log(2) <= operations;
			break;
		case 7:
			fast = n <= operations;
			break;
		}
		if (fast) {
			io.println("AC");
		} else {
			io.println("TLE");
		}
		
		io.flush();
		io.close();
	}
	
	public boolean checkFact(int operations, long n, long f) {
		if (n > operations) {
			return false;
		}
		if (f == 1) {
			return true;
		}
		return checkFact(operations, n*f, f-1);
	}
	
	public static void main(String[] args) {
		new Tutorial().go();
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
