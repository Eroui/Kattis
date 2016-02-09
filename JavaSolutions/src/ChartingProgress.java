import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ChartingProgress {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		ArrayList<Integer> vals = new ArrayList<>(100);
		int size = 0;
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("") || line.equals("end")) {
				int offset = 0;
				for (int i = 0; i < vals.size(); i++) {
					int curr = vals.get(i);
					for (int e = 0; e < size-offset-curr; e++) {
						io.print(".");
					}
					for (int e = 0; e < curr; e++) {
						io.print("*");
					}
					for (int e = 0; e < offset; e++) {
						io.print(".");
					}
					io.println();
					offset += curr;
				}
				vals.clear();
				if (line == null || line.equals("end")) {
					break;
				}
				io.println();
			} else {
				vals.add(line.replaceAll("\\.", "").length());
				size = line.length();
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ChartingProgress().go();
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
