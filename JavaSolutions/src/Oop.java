import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Oop {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numWords = io.nextInt();
		int numPatterns = io.nextInt();
		String[] words = new String[numWords];
		for (int i = 0; i < numWords; i++) {
			words[i] = io.next();
		}
		for (int i = 0; i < numPatterns; i++) {
			int sum = 0;
			String pattern = io.next();
			int star = pattern.indexOf('*');
			String start = pattern.substring(0, star);
			String end = pattern.substring(star+1);
			for (String w : words) {
				if (w.startsWith(start) && w.endsWith(end) && start.length()+end.length() <= w.length()) {
					sum++;
				}
			}
			io.println(sum);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Oop().go();
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
