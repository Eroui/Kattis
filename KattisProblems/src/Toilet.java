import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Toilet {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		char[] line = io.nextLine().toCharArray();
		int numUp = line[0] == 'D' ? 1 : line[1] == 'D' ? 2 : 0;
		int numDown = line[0] == 'U' ? 1 : line[1] == 'U' ? 2 : 0;
		int numChange = line[0] != line[1] ? 1 : 0;
		for (int i = 2; i < line.length; i++) {
			if (line[i] == 'D') {
				numUp += 2;
			} else {
				numDown += 2;
			}
			if (line[i] != line[i-1]) {
				numChange++;
			}
		}
		io.println(numUp);
		io.println(numDown);
		io.println(numChange);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Toilet().go();
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
