import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BusNumbers {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numBuses = io.nextInt();
		boolean[] buses = new boolean[1000];
		for (int i = 0; i < numBuses; i++) {
			buses[io.nextInt()-1] = true;
		}
		StringBuilder sb = new StringBuilder();
		boolean searching = false;
		int length = 0;
		for (int i = 0; i < buses.length; i++) {
			if (buses[i]) {
				searching = true;
				length++;
			} else if (!buses[i] && searching) {
				if (length > 2) {
					sb.append(i-length+1).append("-");
				} else if (length == 2) {
					sb.append(i-length+1).append(" ");
				}
				sb.append(i).append(" ");
				length = 0;
				searching = false;
			}
		}
		if (searching) {
			if (length > 2) {
				sb.append(1000-length+1).append("-");
			} else if (length == 2) {
				sb.append(1000-length+1).append(" ");
			}
			sb.append(1000).append(" ");
			length = 0;
			searching = false;
		}
		io.println(sb.toString().trim());
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BusNumbers().go();
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
