import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BitByBit {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numOps = io.nextInt();
			if (numOps == 0) {
				break;
			}
			int[] num = new int[32];
			Arrays.fill(num, -1);
			for (int zz = 0; zz < numOps; zz++) {
				String op = io.next();
				int i, j;
				switch (op) {
				case "SET":
					num[io.nextInt()] = 1;
					break;
				case "CLEAR":
					num[io.nextInt()] = 0;
					break;
				case "AND":
					i = io.nextInt();
					j = io.nextInt();
					if (num[i] == 0 || num[j] == 0) {
						num[i] = 0;
					} else if (num[i] == 1 && num[j] == 1) {
						num[i] = 1;
					} else {
						num[i] = -1;
					}
					break;
				case "OR":
					i = io.nextInt();
					j = io.nextInt();
					if (num[i] == 1 || num[j] == 1) {
						num[i] = 1;
					} else if (num[i] == 0 && num[j] == 0) {
						num[i] = 0;
					} else {
						num[i] = -1;
					}
					break;
				}
			}
			io.println(new StringBuilder(Arrays.toString(num).replaceAll("[\\[\\], ]", "").replaceAll("-1", "?")).reverse());
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BitByBit().go();
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
