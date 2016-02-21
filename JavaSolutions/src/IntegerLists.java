import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class IntegerLists {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			char[] ops = io.nextLine().trim().toCharArray();
			String[] sNums = new String[io.nextInt()];
			String[] ar = io.nextLine().replaceAll("[\\[\\]]", "").split(",");
			for (int i = 0; i < sNums.length; i++) {
				sNums[i] = ar[i];
			}
			int s = 0, e = sNums.length;
			boolean forward = true;
			for (char c : ops) {
				switch (c) {
				case 'R':
					forward ^= true;
					break;
				case 'D':
					if (forward) {
						s++;
					} else {
						e--;
					}
					break;
				}
				if (s > e) {
					break;
				}
			}
			if (s > e) {
				io.println("error");
			} else {
				io.print("[");
				boolean first = true;
				if (forward) {
					for (int i = s; i < e; i++) {
						if (!first) {
							io.print(",");
						}
						io.print(sNums[i]);
						first = false;
					}
				} else {
					for (int i = e-1; i >= s; i--) {
						if (!first) {
							io.print(",");
						}
						io.print(sNums[i]);
						first = false;
					}
				}
				io.println("]");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new IntegerLists().go();
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
