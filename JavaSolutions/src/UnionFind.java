import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class UnionFind {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numElements = io.nextInt();
		int numOps = io.nextInt();
		int[] set = new int[numElements];
		Arrays.fill(set, -1);
		for (int i = 0; i < numOps; i++) {
			char op = io.next().charAt(0);
			int a = io.nextInt();
			int b = io.nextInt();
			if (op == '?') {
				if (search(set, a) == search(set, b)) {
					io.println("yes");
				} else {
					io.println("no");
				}
			} else {
				union(set, a, b);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public int search(int[] set, int i) {
		if (set[i] < 0) {
			return i;
		} else {
			set[i] = search(set, set[i]);
			return set[i];
		}
	}
	
	public void union(int[] set, int a, int b) {
		int aTop = search(set, a);
		int bTop = search(set, b);
		if (aTop != bTop) {
			set[bTop] += set[aTop];
			set[aTop] = bTop;
		}
	}
	
	public static void main(String[] args) {
		new UnionFind().go();
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
