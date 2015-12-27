import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Subway2 {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		Vertex start = new Vertex(in.nextInt(), in.nextInt());
		Vertex end = new Vertex(in.nextInt(), in.nextInt());
		start.add(end, true);
		ArrayList<ArrayList<Vertex>> subways = new ArrayList<>();
		ArrayList<Vertex> subway = null;
		while (in.hasNextInt()) {
			int x = in.nextInt();
			int y = in.nextInt();
			if (subway == null) {
				subway = new ArrayList<Vertex>();
			}
			if (x == -1 && y == -1) {
				subways.add(subway);
				subway = null;
			} else {
				Vertex v = new Vertex(x,y);
				if (!subway.isEmpty()) {
					v.add(subway.get(subway.size()-1), false);
				}
				for (int i = 0; i < subway.size()-1; i++) {
					v.add(subway.get(i), true);
				}
				subway.add(v);
				for (ArrayList<Vertex> sb : subways) {
					for (Vertex v2 : sb) {
						v.add(v2, true);
					}
				}
				v.add(start, true);
				v.add(end, true);
			}
		}
		dijsktra(start);
		out.println(Math.round(end.best));
		
		in.close();
	}
	
	public void dijsktra(Vertex start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(start);
		start.best = 0;
		while (!pq.isEmpty()) {
			Vertex curr = pq.remove();
			curr.visited = true;
			for (Edge e : curr.edges) {
				if (!e.end.visited && curr.best+e.dist < e.end.best) {
					e.end.best = curr.best+e.dist;
					pq.remove(e.end);
					pq.add(e.end);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Subway2().go();
	}
	
	private class Vertex implements Comparable<Vertex> {
		int x, y;
		boolean visited = false;
		double best = Double.MAX_VALUE;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		public Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void add(Vertex other, boolean walk) {
			double dist;
			if (walk) {
				dist = Math.hypot(x - other.x, y - other.y)*60/10000;
			} else {
				dist = Math.hypot(x - other.x, y - other.y)*60/40000;
			}
			edges.add(new Edge(this, other, dist));
			other.edges.add(new Edge(other, this, dist));
		}
		
		public int compareTo(Vertex other) {
			if (best < other.best) {
				return -1;
			} else if (best > other.best) {
				return 1;
			}
			return 0;
		}
	}
	
	private class Edge {
		Vertex start, end;
		double dist;
		
		public Edge(Vertex s, Vertex e, double d) {
			start = s;
			end = e;
			dist = d;
		}
	}
}
