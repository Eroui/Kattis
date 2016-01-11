import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PermutedArithmeticSequence {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numVals = io.nextInt();
			int[] ar = new int[numVals];
			for (int i = 0; i < numVals; i++) {
				ar[i] = io.nextInt();
			}
			boolean arithmetic = isArithmetic(ar);
			if (arithmetic) {
				io.println("arithmetic");
				continue;
			}
			Arrays.sort(ar);
			arithmetic = isArithmetic(ar);
			if (arithmetic) {
				io.println("permuted arithmetic");
			} else {
				io.println("non-arithmetic");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public boolean isArithmetic(int[] ar) {
		if (ar.length <= 1) {
			return true;
		}
		int diff = ar[1] - ar[0];
		for (int i = 0; i < ar.length-1; i++) {
			if (ar[i+1] - ar[i] != diff) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		new PermutedArithmeticSequence().go();
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
