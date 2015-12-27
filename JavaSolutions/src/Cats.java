import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Cats {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int amountMilk = in.nextInt();
			int numCats = in.nextInt();
			if (numCats == 1) {
				out.println("yes");
				continue;
			}
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			boolean[] visited = new boolean[numCats];
			int numCombinations = numCats * (numCats-1) / 2;
			for (int n = 0; n < numCombinations; n++) {
				pq.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
			}
			Edge curr = pq.remove();
			visited[curr.start] = true;
			visited[curr.end] = true;
			int numVisited = 2;
			long sum = curr.weight;
			LinkedList<Edge> toRecheck = new LinkedList<Edge>();
			while (numVisited < numCats) {
				curr = pq.remove();
				if (!visited[curr.start] && !visited[curr.end]) {
					toRecheck.add(curr);
				} else if (visited[curr.start] && visited[curr.end]) {
					continue;
				} else if (visited[curr.start] || visited[curr.end]) {
					numVisited++;
					visited[curr.start] = true;
					visited[curr.end] = true;
					sum += curr.weight;
//					out.println(toRecheck);
					ListIterator<Edge> it = toRecheck.listIterator();
					while (it.hasNext()) {
						curr = it.next();
						if (visited[curr.start] && visited[curr.end]) {
							it.remove();
						} else if (visited[curr.start] || visited[curr.end]) {
							it.remove();
							numVisited++;
							visited[curr.start] = true;
							visited[curr.end] = true;
							sum += curr.weight;
							while (it.hasPrevious()) {
								it.previous();
							}
						}
					}
				}
			}
			if (sum + numCats <= amountMilk) {
				out.println("yes");
			} else {
				out.println("no");
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Cats().go();
	}
	
	class Edge implements Comparable<Edge> {
		int start, end, weight;
		
		public Edge(int s, int e, int w) {
			start = s;
			end = e;
			weight = w;
		}
		
		public int compareTo(Edge other) {
			return weight - other.weight;
		}
		
		public String toString() {
			return ""+weight;
		}
	}
}
