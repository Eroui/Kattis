import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BreakingBad {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numItems = io.nextInt();
		Item[] items = new Item[numItems];
		HashMap<String, Item> map = new HashMap<>();
		for (int i = 0; i < numItems; i++) {
			String s = io.next();
			items[i] = new Item(s);
			map.put(s, items[i]);
		}
		int numPairs = io.nextInt();
		for (int i = 0; i < numPairs; i++) {
			Item one = map.get(io.next());
			Item two = map.get(io.next());
			one.add(two);
		}
		boolean works = true;
		for (Item i : items) {
			if (i.type == -1) {
				works &= bfs(i, 0);
			}
		}
		if (works) {
			boolean first = true;
			for (int i = 0; i < items.length; i++) {
				if (items[i].type == 0) {
					if (!first) {
						io.print(" ");
					}
					io.print(items[i].name);
					first = false;
				}
			}
			io.println();
			first = true;
			for (int i = 0; i < items.length; i++) {
				if (items[i].type == 1) {
					if (!first) {
						io.print(" ");
					}
					io.print(items[i].name);
					first = false;
				}
			}
		} else {
			io.println("impossible");
		}
		
		io.flush();
		io.close();
	}
	
	public boolean bfs(Item curr, int t) {
		curr.type = t;
		for (Item other : curr.others) {
			if (other.type != -1 && other.type != (t^1)) {
				return false;
			} else if (other.type == -1) {
				bfs(other, t^1);
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		new BreakingBad().go();
	}
	
	private class Item {
		
		String name;
		int type = -1;
		ArrayList<Item> others = new ArrayList<>();
		
		public Item(String n) {
			name = n;
		}
		
		public void add(Item other) {
			others.add(other);
			other.others.add(this);
		}
		
		public String toString() {
			return name + " " + type;
		}
		
		public int hashCode() {
			return name.hashCode();
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
