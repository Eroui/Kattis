import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class AlphabetSpam {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		char[] line = io.nextLine().toCharArray();
		int whitespace = 0;
		int lower = 0;
		int upper = 0;
		int symbols = 0;
		for (char c : line) {
			if (c == '_') {
				whitespace++;
			} else if (Character.isLowerCase(c)) {
				lower++;
			} else if (Character.isUpperCase(c)) {
				upper++;
			} else {
				symbols++;
			}
		}
		io.println((double)whitespace/line.length);
		io.println((double)lower/line.length);
		io.println((double)upper/line.length);
		io.println((double)symbols/line.length);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new AlphabetSpam().go();
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
