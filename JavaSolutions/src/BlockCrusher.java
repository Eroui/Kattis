import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BlockCrusher {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int height = io.nextInt();
			int width = io.nextInt();
			if (height == 0 && width == 0) {
				break;
			}
			char[][] wall = new char[height][width];
			Vertex source = new Vertex(width, 0, -1, -1);
			Vertex[] prevRow = new Vertex[width];
			Vertex[] currRow = new Vertex[width];
			String line = io.nextLine();
			wall[0] = line.toCharArray();
			int r = 0;
			for (int i = 0; i < width; i++) {
				currRow[i] = new Vertex(width, line.charAt(i)-'0', r, i);
				source.add(currRow[i]);
			}
			for (int i = 0; i < width-1; i++) {
				currRow[i].add(currRow[i+1]);
			}
			for (int i = 1; i < height; i++) {
				r++;
				prevRow = currRow;
				currRow = new Vertex[width];
				line = io.nextLine();
				wall[i] = line.toCharArray();
				for (int e = 0; e < width; e++) {
					currRow[e] = new Vertex(width, line.charAt(e)-'0', r, e);
					prevRow[e].add(currRow[e]);
					for (int w = e-1; w <= e+1; w++) {
						if (w >= 0 && w < width) {
							prevRow[w].add(currRow[e]);
						}
					}
				}
				for (int e = 0; e < width-1; e++) {
					currRow[e].add(currRow[e+1]);
				}
			}
			Vertex dest = new Vertex(width, 0, -1, -1);
			for (int i = 0; i < width; i++) {
				currRow[i].add(dest);
			}
			dijkstra(source);
			boolean[][] removed = new boolean[height][width];
			Vertex curr = dest;
			while (curr.prev != null) {
				if (curr.r >= 0) {
					removed[curr.r][curr.c] = true;
				}
				curr = curr.prev;
			}
			for (int i = 0; i < height; i++) {
				for (int e = 0; e < width; e++) {
					if (removed[i][e]) {
						io.print(" ");
					} else {
						io.print(wall[i][e]);
					}
				}
				io.println();
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public void dijkstra(Vertex source) {
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(source);
		source.dist = 0;
		while (!queue.isEmpty()) {
			Vertex curr = queue.remove();
			for (Vertex other : curr.others) {
				if (curr.dist+other.cost < other.dist) {
					other.dist = curr.dist+other.cost;
					if (queue.contains(other)) {
						queue.remove(other);
					}
					queue.add(other);
					other.prev = curr;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new BlockCrusher().go();
	}
	
	private class Vertex implements Comparable<Vertex> {
		
		int dist = Integer.MAX_VALUE;
		int cost;
		ArrayList<Vertex> others;
		Vertex prev;
		int r,c;
		
		public Vertex(int s, int co, int r, int c) {
			this.r = r;
			this.c = c;
			cost = co;
			others = new ArrayList<Vertex>(s);
		}
		
		public void add(Vertex other) {
			others.add(other);
			other.others.add(this);
		}
		
		public int compareTo(Vertex other) {
			return dist - other.dist;
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
