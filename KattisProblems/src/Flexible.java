import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Flexible {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int width = io.nextInt();
		int numPartitions = io.nextInt();
		int[] partitions = new int[numPartitions];
		for (int i = 0; i < numPartitions; i++) {
			partitions[i] = io.nextInt();
		}
		boolean[] possible = new boolean[width+1];
		possible[width] = true;
		for (int i = 0; i < numPartitions; i++) {
			possible[partitions[i]] = true;
			possible[width-partitions[i]] = true;
		}
		for (int i = 0; i < numPartitions; i++) {
			for (int e = i+1; e < numPartitions; e++) {
				possible[partitions[e] - partitions[i]] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < possible.length; i++) {
			if (possible[i]) {
				sb.append(i).append(" ");
			}
		}
		io.println(sb.toString().trim());
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Flexible().go();
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
