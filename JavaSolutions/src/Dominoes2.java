import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Dominoes2 {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numDominoes = io.nextInt();
			int numEdges = io.nextInt();
			int numTapped = io.nextInt();
			Domino[] dominoes = new Domino[numDominoes];
			for (int i = 0; i < numDominoes; i++) {
				dominoes[i] = new Domino(i);
			}
			for (int i = 0; i < numEdges; i++) {
				int from = io.nextInt()-1;
				int to = io.nextInt()-1;
				dominoes[from].add(dominoes[to]);
			}
			int numKnocked = 0;
			for (int i = 0; i < numTapped; i++) {
				int tap = io.nextInt()-1;
				LinkedList<Domino> queue = new LinkedList<>();
				queue.add(dominoes[tap]);
				while (!queue.isEmpty()) {
					Domino curr = queue.removeFirst();
					if (curr.visited) {
						continue;
					}
					curr.visited = true;
					numKnocked++;
					for (Domino other : curr.others) {
						if (!other.visited) {
							queue.add(other);
						}
					}
				}
			}
			io.println(numKnocked);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Dominoes2().go();
	}
	
	private class Domino {
		
		int index;
		boolean visited;
		ArrayList<Domino> others;
		
		public Domino(int i) {
			index = i;
			visited = false;
			others = new ArrayList<>();
		}
		
		public void add(Domino other) {
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
