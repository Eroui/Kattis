import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Trilemma {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int x1 = io.nextInt();
			int y1 = io.nextInt();
			int x2 = io.nextInt();
			int y2 = io.nextInt();
			int x3 = io.nextInt();
			int y3 = io.nextInt();
			double[] sides = new double[3];
			sides[0] = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
			sides[1] = Math.sqrt(Math.pow(x3-x2, 2) + Math.pow(y3-y2, 2));
			sides[2] = Math.sqrt(Math.pow(x1-x3, 2) + Math.pow(y1-y3, 2));
			Arrays.sort(sides);
			io.printf("Case #%d: ", zzz+1);
			if (same(sides[0]+sides[1], sides[2])) {
				io.println("not a triangle");
				continue;
			}
			if (same(sides[0], sides[1]) || same(sides[1], sides[2])) {
				io.print("isosceles ");
			} else {
				io.print("scalene ");
			}
			if (same(sides[0]*sides[0] + sides[1]*sides[1], sides[2]*sides[2])) {
				io.print("right ");
			} else if (sides[0]*sides[0] + sides[1]*sides[1] < sides[2]*sides[2]) {
				io.print("obtuse ");
			} else {
				io.print("acute ");
			}
			io.println("triangle");
		}
		
		io.flush();
		io.close();
	}
	
	public boolean same(double x, double y) {
		return Math.abs(x-y) < 0.0001;
	}
	
	public static void main(String[] args) {
		new Trilemma().go();
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
