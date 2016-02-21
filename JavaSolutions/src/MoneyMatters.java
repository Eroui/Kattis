import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class MoneyMatters {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numPeople = io.nextInt();
		int numEdges = io.nextInt();
		int[] people = new int[numPeople];
		for (int i = 0; i < numPeople; i++) {
			people[i] = io.nextInt();
		}
		HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
		for (int i = 0; i < numEdges; i++) {
			int a = io.nextInt();
			int b = io.nextInt();
			if (map.get(a) == null) {
				map.put(a, new HashSet<Integer>());
			}
			if (map.get(b) == null) {
				map.put(b, new HashSet<Integer>());
			}
			map.get(a).add(b);
			map.get(b).add(a);
		}
		boolean[] visited = new boolean[numPeople];
		boolean even = true;
		for (int i = 0; i < numPeople && even; i++) {
			if (!visited[i]) {
				even = bfs(people, map, visited, i);
			}
		}
		if (even) {
			io.println("POSSIBLE");
		} else {
			io.println("IMPOSSIBLE");
		}
		
		io.flush();
		io.close();
	}
	
	public boolean bfs(int[] people, HashMap<Integer, HashSet<Integer>> map, boolean[] visited, int i) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(i);
		int total = 0;
		while (!queue.isEmpty()) {
			int curr = queue.remove();
			if (visited[curr]) {
				continue;
			}
			visited[curr] = true;
			total += people[curr];
			for (Integer other : map.get(curr)) {
				if (!visited[other]) {
					queue.add(other);
				}
			}
		}
		return total == 0;
	}
	
	public static void main(String[] args) {
		new MoneyMatters().go();
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
