import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Walkway {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numStones = io.nextInt();
			if (numStones == 0) {
				break;
			}
			HashMap<Integer, Vertex> map = new HashMap<>();
			for (int i = 0; i < numStones; i++) {
				int a = io.nextInt();
				int b = io.nextInt();
				int h = io.nextInt();
				int cost = (a+b)*h;
				if (map.get(a) == null) {
					map.put(a, new Vertex(a));
				}
				if (map.get(b) == null) {
					map.put(b, new Vertex(b));
				}
				map.get(a).add(map.get(b), cost);
			}
			int start = io.nextInt();
			int end = io.nextInt();
			PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
			queue.add(map.get(start));
			map.get(start).best = 0;
			while (!queue.isEmpty()) {
				Vertex curr = queue.remove();
				curr.visited = true;
				for (Edge e : curr.edges) {
					if (!e.other.visited && curr.best + e.cost < e.other.best) {
						e.other.best = curr.best + e.cost;
						queue.remove(e.other);
						queue.add(e.other);
					}
				}
			}
			io.printf("%.2f%n", map.get(end).best*.02/2);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Walkway().go();
	}
	
	private class Vertex implements Comparable<Vertex> {
		
		int size;
		boolean visited;
		int best;
		List<Edge> edges = new ArrayList<>();
		
		public Vertex(int s) {
			size = s;
			best = Integer.MAX_VALUE;
		}

		public void add(Vertex other, int cost) {
			for (Edge e : edges) {
				if (e.other.size == other.size && e.cost < cost) {
					return;
				}
			}
			edges.add(new Edge(other, cost));
			other.edges.add(new Edge(this, cost));
		}
		
		public int compareTo(Vertex other) {
			return best - other.best;
		}
	}
	
	private class Edge {
		
		Vertex other;
		int cost;
		
		public Edge(Vertex o, int c) {
			other = o;
			cost = c;
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
