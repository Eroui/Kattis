import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class AlmostPerfect {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			int num = Integer.parseInt(next);
			int sum = 1;
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					sum += i;
					sum += num/i;
				}
			}
			if ((int)Math.sqrt(num) == Math.sqrt(num)) {
				sum -= Math.sqrt(num);
			}
			if (sum == num) {
				io.printf("%d perfect%n", num);
			} else if (Math.abs(sum-num) <= 2) {
				io.printf("%d almost perfect%n", num);
			} else {
				io.printf("%d not perfect%n", num);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new AlmostPerfect().go();
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
