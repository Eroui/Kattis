import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Pubs {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numSites = io.nextInt();
		int numWalkways = io.nextInt();
		Vertex[] sites = new Vertex[numSites];
		for (int i = 0; i < sites.length; i++) {
			sites[i] = new Vertex();
		}
		for (int i = 0; i < numWalkways; i++) {
			sites[io.nextInt()-1].add(sites[io.nextInt()-1]);
		}
		for (Vertex v : sites) {
			if (v.others.size() == 0) {
				io.println("Impossible");
				io.flush();
				io.close();
				return;
			}
			if (v.type == -1) {
				dfs(v, 0);
			}
		}
		for (int i = 0; i < sites.length; i++) {
			if (i != 0) {
				io.print(" ");
			}
			if (sites[i].type == 0) {
				io.print("pub");
			} else {
				io.print("house");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public void dfs(Vertex curr, int t) {
		curr.type = t;
		for (Vertex other : curr.others) {
			if (other.type == -1) {
				dfs(other, (t+1)%2);
			}
		}
	}
	
	public static void main(String[] args) {
		new Pubs().go();
	}
	
	private class Vertex {
		
		int type = -1;
		ArrayList<Vertex> others = new ArrayList<>();
		
		public void add(Vertex other) {
			others.add(other);
			other.others.add(this);
		}
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
