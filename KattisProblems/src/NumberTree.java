import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class NumberTree {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		String[] line = io.nextLine().split(" ");
		int height = Integer.parseInt(line[0]);
		if (line.length != 1) {
			String op = line[1];
			long offset = 0;
			for (char c : op.toCharArray()) {
				if (c == 'L') {
					offset = offset*2+1;
				} else {
					offset *= 2;
				}
			}
			io.println(Math.round(Math.pow(2, height+1))-Math.round(Math.pow(2, op.length()+1)-2)+offset-1);
		} else {
			io.println(Math.round(Math.pow(2, height+1))-1);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new NumberTree().go();
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
