import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class HumanCannonball {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		double x1 = io.nextDouble();
		double y1 = io.nextDouble();
		double x2 = io.nextDouble();
		double y2 = io.nextDouble();
		int numCannons = io.nextInt();
		double[][] mat = new double[numCannons+2][numCannons+2];
		double[][] cannonLocs = new double[numCannons][2];
		for (int i = 0; i < numCannons; i++) {
			cannonLocs[i] = new double[]{io.nextDouble(), io.nextDouble()};
		}
		mat[0][1] = Math.hypot(Math.abs(x1-x2), Math.abs(y1-y2))/5;
		mat[1][0] = mat[0][1];
		for (int i = 0; i < numCannons; i++) {
			double dist = Math.hypot(Math.abs(x1-cannonLocs[i][0]), Math.abs(y1-cannonLocs[i][1]));
			mat[0][i+2] = dist/5;
			mat[i+2][0] = Math.abs(dist-50)/5+2;
		}
		for (int i = 0; i < numCannons; i++) {
			double dist = Math.hypot(Math.abs(x2-cannonLocs[i][0]), Math.abs(y2-cannonLocs[i][1]));
			mat[1][i+2] = dist/5;
			mat[i+2][1] = Math.abs(dist-50)/5+2;
		}
		for (int i = 0; i < numCannons; i++) {
			for (int e = 1; e < numCannons; e++) {
				double dist = Math.hypot(Math.abs(cannonLocs[e][0]-cannonLocs[i][0]),
						Math.abs(cannonLocs[e][1]-cannonLocs[i][1]));
				mat[i+2][e+2] = Math.abs(dist-50)/5+2;
				mat[e+2][i+2] = mat[i+2][e+2];
			}
		}
		double[] times = new double[numCannons+2];
		Arrays.fill(times, Double.MAX_VALUE);
		dijkstra(mat, times);
		io.println(times[1]);
		
		io.flush();
		io.close();
	}
	
	public void dijkstra(double[][] mat, double[] times) {
		boolean[] visited = new boolean[times.length];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.remove();
			if (visited[curr.index]) {
				continue;
			}
			visited[curr.index] = true;
			for (int i = 0; i < times.length; i++) {
				if (i != curr.index && !visited[i] && curr.dist+mat[curr.index][i] < times[i]) {
					times[i] = curr.dist+mat[curr.index][i];
					pq.add(new Node(i, times[i]));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new HumanCannonball().go();
	}
	
	private class Node implements Comparable<Node> {
		
		int index;
		double dist;
		
		public Node(int i, double d) {
			index = i;
			dist = d;
		}
		
		public int compareTo(Node other) {
			if (dist > other.dist) {
				return 1;
			} else if (dist < other.dist) {
				return -1;
			}
			return 0;
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
