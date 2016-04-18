import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class CheckingForCorrectness {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			long one = Long.parseLong(next)%10000;
			char symbol = io.next().charAt(0);
			long two = io.nextLong();
			switch (symbol) {
			case '+':
				io.println((one+two)%10000);
				break;
			case '*':
				io.println((one*two)%10000);
				break;
			case '^':
				long ans = 1;
				while (two > 0) {
					if (two % 2 == 1) {
						ans = (ans * one) % 10000;
					}
					one = (one * one) % 10000;
					two /= 2;
				}
				io.println(ans);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new CheckingForCorrectness().go();
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
