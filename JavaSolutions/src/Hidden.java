import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Hidden {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		String pass = io.next();
		String message = io.next();
		boolean fails = false;
		int currIndex = 0;
		for (int i = 0; i < pass.length(); i++) {
			int cIndex = message.indexOf(pass.charAt(i), currIndex);
			int minOther = 100;
			for (int e = i+1; e < pass.length(); e++) {
				minOther = Math.min(minOther, message.indexOf(pass.charAt(e), currIndex));
			}
			if (minOther == -1 || cIndex > minOther) {
				fails = true;
				break;
			}
			currIndex = cIndex+1;
		}
		if (fails) {
			io.println("FAIL");
		} else {
			io.println("PASS");
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Hidden().go();
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
