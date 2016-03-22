import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ErdosNumbers {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		LinkedList<Vertex> nodes = new LinkedList<>();
		HashMap<String, Vertex> map = new HashMap<>();
		while (true) {
			String next = io.nextLine();
			if (next == null || next.equals("end")) {
				break;
			}
			String[] names = next.split(" ");
			Vertex start = map.get(names[0]);
			if (start == null) {
				start = new Vertex(names[0]);
				map.put(names[0], start);
			}
			nodes.add(start);
			for (int i = 1; i < names.length; i++) {
				Vertex curr = map.get(names[i]);
				if (curr == null) {
					curr = new Vertex(names[i]);
					map.put(names[i], curr);
				}
				start.add(curr);
			}
		}
		LinkedList<Vertex> queue = new LinkedList<>();
		queue.add(map.get("PAUL_ERDOS"));
		queue.peek().dist = 0;
		while (!queue.isEmpty()) {
			Vertex curr = queue.remove();
			for (Vertex other : curr.others) {
				if (curr.dist+1 < other.dist) {
					other.dist = curr.dist+1;
					queue.add(other);
				}
			}
		}
		for (Vertex curr : nodes) {
			if (curr.dist == Integer.MAX_VALUE) {
				io.printf("%s no-connection%n", curr.name);
			} else {
				io.printf("%s %d%n", curr.name, curr.dist);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ErdosNumbers().go();
	}
	
	private class Vertex {
		
		String name;
		int dist = Integer.MAX_VALUE;
		ArrayList<Vertex> others;
		
		public Vertex(String n) {
			name = n;
			others = new ArrayList<>();
		}
		
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
