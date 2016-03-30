import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BuildDeps {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numFiles = io.nextInt();
		HashMap<String, Vertex> map = new HashMap<>();
		for (int i = 0; i < numFiles; i++) {
			String[] files = io.nextLine().split(" ");
			String dest = files[0].substring(0, files[0].length()-1);
			if (map.get(dest) == null) {
				map.put(dest, new Vertex(dest));
			}
			for (int e = 1; e < files.length; e++) {
				if (map.get(files[e]) == null) {
					map.put(files[e], new Vertex(files[e]));
				}
				map.get(files[e]).add(map.get(dest));
			}
		}
		String start = io.next();
		LinkedList<Vertex> queue = new LinkedList<>();
		queue.add(map.get(start));
		while (!queue.isEmpty()) {
			Vertex curr = queue.remove();
			if (curr.visited) {
				continue;
			}
			for (Vertex other : curr.others) {
				queue.add(other);
				other.inDegree++;
			}
		}
		queue.add(map.get(start));
		while (!queue.isEmpty()) {
			Vertex curr = queue.remove();
			io.println(curr.name);
			for (Vertex other : curr.others) {
				other.inDegree--;
				if (other.inDegree == 0) {
					queue.add(other);
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BuildDeps().go();
	}
	
	private class Vertex {
		
		String name;
		int inDegree;
		boolean visited;
		HashSet<Vertex> others;
		
		public Vertex(String n) {
			name = n;
			others = new HashSet<>();
		}
		
		public void add(Vertex other) {
			others.add(other);
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
