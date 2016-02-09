import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PolyMul1 {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numDegrees1 = io.nextInt();
			int[] degrees1 = new int[numDegrees1+1];
			for (int i = 0; i < numDegrees1+1; i++) {
				degrees1[i] = io.nextInt();
			}
			int numDegrees2 = io.nextInt();
			int[] degrees2 = new int[numDegrees2+1];
			for (int i = 0; i < numDegrees2+1; i++) {
				degrees2[i] = io.nextInt();
			}
			int[] finalDegrees = new int[numDegrees1+numDegrees2+1];
			for (int i = 0; i < numDegrees1+1; i++) {
				for (int e = 0; e < numDegrees2+1; e++) {
					finalDegrees[i+e] += degrees1[i] * degrees2[e];
//					io.print(degrees1[i] * degrees2[e] + " ");
				}
			}
			io.println(finalDegrees.length-1);
			io.println(Arrays.toString(finalDegrees).replaceAll("[\\[\\],]", ""));
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new PolyMul1().go();
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
