import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ChopWood {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numEdges = io.nextInt();
		int[] v = new int[numEdges];
		boolean[] taken = new boolean[numEdges+2];
		for (int i = 0; i < numEdges; i++) {
			v[i] = io.nextInt();
		}
		int[] u = new int[numEdges];
		boolean[] haveCopied = new boolean[numEdges+2];
		for (int i = numEdges-1; i > 0; i--) {
			if (v[i] != v[i-1] && !haveCopied[v[i-1]]) {
				u[i] = v[i-1];
			}
			taken[v[i]] = true;
			haveCopied[v[i]] = true;
		}
		taken[v[0]] = true;
		int nextOpen = 0;
		out.println(Arrays.toString(u));
		for (int i = 1; i < taken.length; i++) {
			if (!taken[i]) {
				while (u[nextOpen] != 0) {
					nextOpen++;
				}
				u[nextOpen] = i;
			}
		}
		for (int i = 0; i < u.length; i++) {
			boolean atEnd = true;
			for (int e = i+1; e < v.length; e++) {
				if (v[i] != v[e]) {
					Arrays.sort(u, i, e);
					i = e-1;
					atEnd = false;
					break;
				}
			}
			if (atEnd) {
				Arrays.sort(u, i, u.length);
			}
		}

		out.println(Arrays.toString(u));
		boolean good = true;
		for (int i = 0; i < u.length && good; i++) {
			good = u[i] < v[v.length-1];
			for (int e = u[i]-1; e > 0 && good; e--) {
				for (int w = i; w < v.length && good; w++) {
					if (v[w] == e) {
						break;
					} else if (u[w] == e) {
						good = false;
						break;
					}
				}
			}
		}
		if (good) {
			io.println(Arrays.toString(u).replaceAll("[\\[\\],]", "").replaceAll(" ", "\n"));
		} else {
			io.println("Error");
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ChopWood().go();
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
