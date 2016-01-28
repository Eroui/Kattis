import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Torn2Pieces {
	
	HashSet<Station> stations;
	
	public void go() {
		Kattio io = new Kattio(System.in);
		stations = new HashSet<>();
		int numPieces = io.nextInt();
		for (int i = 0; i < numPieces; i++) {
			String[] line = io.nextLine().split(" ");
			Station source = getStation(line[0]);
			for (int e = 1; e < line.length; e++) {
				source.add(getStation(line[e]));
			}
		}
		Station source = getStation(io.next());
		Station destination = getStation(io.next());
		dijkstra(source, destination);
		if (destination.prev == null) {
			io.println("no route found");
		} else {
			io.println(print(destination));
		}
		
		io.flush();
		io.close();
	}
	
	public void dijkstra(Station start, Station dest) {
		LinkedList<Station> queue = new LinkedList<>();
		queue.add(start);
		while (!queue.isEmpty()) {
			Station curr = queue.remove();
			curr.visited = true;
			for (Station s : curr.others) {
				if (!s.visited) {
					queue.add(s);
					s.prev = curr;
				}
			}
		}
	}
	
	public String print(Station s) {
		if (s.prev != null) {
			return String.format("%s %s", print(s.prev), s);
		} else {
			return s.toString();
		}
	}
	
	public Station getStation(String n) {
		for (Station s : stations) {
			if (s.name.equals(n)) {
				return s;
			}
		}
		Station s = new Station(n);
		stations.add(s);
		return s;
	}
	
	public static void main(String[] args) {
		new Torn2Pieces().go();
	}
	
	private class Station {
		
		String name;
		HashSet<Station> others;
		Station prev;
		boolean visited;
		
		public Station(String s) {
			name = s;
			others = new HashSet<>();
		}
		
		public void add(Station s) {
			others.add(s);
			s.others.add(this);
		}
		
		public String toString() {
			return name;
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
