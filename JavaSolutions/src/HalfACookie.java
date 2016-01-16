import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class HalfACookie {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		String line;
		while (io.hasNext()) {
			double r = io.nextDouble();
			if (r == -1) {
				break;
			}
			double x = io.nextDouble();
			double y = io.nextDouble();
			if (r*r <= x*x+y*y) {
				io.println("miss");
				continue;
			}
			x = Math.sqrt(x*x+y*y);
			y = 0;
			double angle = Math.acos(x/r);
			y = r * Math.sin(angle);
			double areaOne = angle * r*r - x*y;
			double areaTwo = Math.PI * r*r - areaOne;
			if (areaOne > areaTwo) {
				io.println(areaOne + " " + areaTwo);
			} else {
				io.println(areaTwo + " " + areaOne);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new HalfACookie().go();
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
