import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class VisualGo {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numNodes = in.nextInt();
		int numEdges = in.nextInt();
		Vertex[] nodes = new Vertex[numNodes];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] = new Vertex(i);
		}
		for (int i = 0; i < numEdges; i++) {
			Vertex start = nodes[in.nextInt()];
			Vertex end = nodes[in.nextInt()];
			int weight = in.nextInt();
			Edge e = new Edge(start, end, weight);
			start.edges.add(e);
		}
		search(nodes, in.nextInt());
		out.println(nodes[in.nextInt()].numPaths);
		
		in.close();
	}
	
	public void search(Vertex[] nodes, int start) {
		LinkedList<Vertex> queue = new LinkedList<>();
		nodes[start].numPaths = 1;
		nodes[start].minDistance = 0;
		queue.addLast(nodes[start]);
		while (!queue.isEmpty()) {
			Vertex curr = queue.removeFirst();
			for (Edge e : curr.edges) {
				Vertex other = e.end;
				if (!other.checked) {
					other.checked = true;
					queue.addLast(other);
				}
				if (curr.minDistance + e.weight < other.minDistance) {
					other.minDistance = curr.minDistance + e.weight;
					other.numPaths = curr.numPaths;
				} else if (curr.minDistance + e.weight == other.minDistance) {
					other.numPaths += curr.numPaths;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new VisualGo().go();
	}

	private class Vertex implements Comparable<Vertex> {
		int numPaths = 0;
		long minDistance = Integer.MAX_VALUE;
		int index;
		boolean checked = false;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		public Vertex(int i) {
			index = i;
		}
		
		public int compareTo(Vertex other) {
			if (minDistance < other.minDistance) {
				return -1;
			} else if (minDistance > other.minDistance) {
				return 1;
			} else {
				return index - other.index;
			}
		}
		
		public String toString() {
			return String.format("%d: %d paths %d dist", index, numPaths, minDistance);
		}
	}
	
	private class Edge {
		Vertex start, end;
		int weight;
		
		public Edge(Vertex s, Vertex e, int w) {
			start = s;
			end = e;
			weight = w;
		}
	}
}