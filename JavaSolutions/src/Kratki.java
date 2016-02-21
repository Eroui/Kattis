import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Kratki {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int n = io.nextInt();
		long k = io.nextInt();
		if (k * k < n) {
			io.println("-1");
		} else {
			long i = k;
			boolean first = true;
			while (i < n) {
				for (int j = 0; j < k; j++) {
					if (!first) {
						io.print(" ");
					}
					io.print(i--);
					first = false;
				}
				i += 2*k;
			}
			i -= k;
			for (int j = n; j > i; j--) {
				if (!first) {
					io.print(" ");
				}
				io.print(j);
				first = false;
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Kratki().go();
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
