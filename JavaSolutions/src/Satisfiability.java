import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Satisfiability {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int n = io.nextInt();
			int numClauses = io.nextInt();
			int[] clauses = new int[numClauses];
			int[] mask = new int[numClauses];
			boolean hasTrue = false;
			for (int i = 0; i < numClauses; i++) {
				String[] split = io.nextLine().split(" ");
				int clause = -1;
				int tUsed = 0;
				int fUsed = 0;
				for (String s : split) {
					if (s.charAt(0) != 'v') {
						int bit;
						int bitmask;
						if (s.charAt(0) == '~') {
							bit = Integer.parseInt(s.substring(2))-1;
							bitmask = (int)Math.pow(2, bit);
							fUsed |= bitmask;
							clause &= -1-bitmask;
						} else {
							bit = Integer.parseInt(s.substring(1))-1;
							bitmask = (int)Math.pow(2, bit);
							tUsed |= bitmask;
						}
						mask[i] |= bitmask;
					}
				}
				clauses[i] = clause;
				if ((tUsed & fUsed) != 0) {
					hasTrue = true;
				}
			}
			if (hasTrue) {
				io.println("satisfiable");
				continue;
			}
			boolean works = true;
			for (int i = (int)Math.pow(2, n)-1; i >= 0; i--) {
				works = true;
				for (int e = 0; e < numClauses; e++) {
					if ((~(clauses[e] ^ i) & mask[e]) == 0) {
						works = false;
						break;
					}
				}
				if (works) {
					io.println("satisfiable");
					break;
				}
			}
			if (!works) {
				io.println("unsatisfiable");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Satisfiability().go();
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
