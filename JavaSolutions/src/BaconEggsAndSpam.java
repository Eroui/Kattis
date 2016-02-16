import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BaconEggsAndSpam {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numOrders = io.nextInt();
			if (numOrders == 0) {
				break;
			}
			TreeMap<String, TreeSet<String>> map = new TreeMap<>();
			for (int i = 0; i < numOrders; i++) {
				String[] line = io.nextLine().split(" ");
				for (int e = 1; e < line.length; e++) {
					if (map.get(line[e]) == null) {
						map.put(line[e], new TreeSet<String>());
					}
					map.get(line[e]).add(line[0]);
				}
			}
			for (String s : map.keySet()) {
				io.printf("%s %s%n", s, map.get(s).toString().replaceAll("[\\[\\],]", ""));
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BaconEggsAndSpam().go();
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
