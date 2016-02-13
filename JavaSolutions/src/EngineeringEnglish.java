import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class EngineeringEnglish {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		HashSet<String> used = new HashSet<String>();
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("...")) {
				break;
			}
			String[] split = line.split(" ");
			StringBuilder sb = new StringBuilder(line.length());
			for (String s : split) {
				if (used.contains(s.toLowerCase())) {
					sb.append(". ");
				} else {
					sb.append(s).append(" ");
					used.add(s.toLowerCase());
				}
			}
			io.println(sb.toString().trim());
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new EngineeringEnglish().go();
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
