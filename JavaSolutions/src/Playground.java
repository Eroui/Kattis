import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Playground {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numWires = io.nextInt();
			if (numWires == 0) {
				break;
			}
			long[] wires = new long[numWires];
			for (int i = 0; i < numWires; i++) {
				wires[i] = (long)(io.nextDouble()*1000);
			}
			Arrays.sort(wires);
			long sum = 0;
			for (int i = 0; i < numWires-1; i++) {
				sum += wires[i];
			}
			boolean makeShape = false;
			for (int i = numWires-1; i > 0; i--) {
				if (sum >= wires[i]) {
					makeShape = true;
					break;
				}
				sum -= wires[i-1];
			}
			if (makeShape) {
				io.println("YES");
			} else {
				io.println("NO");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Playground().go();
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
