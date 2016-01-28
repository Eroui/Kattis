import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class CantinaOfBabel {
	
	HashSet<Language> all;
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numPeople = io.nextInt();
		all = new HashSet<>(numPeople);
		for (int i = 0; i < numPeople; i++) {
			String[] line = io.nextLine().split(" ");
			Language curr = getLanguage(line[1]);
			curr.count++;
			for (int e = 2; e < line.length; e++) {
				Language listen = getLanguage(line[e]);
				listen.add(curr);
			}
		}
		int max = 0;
		for (Language l : all) {
			findStrongConnected(l);
			max = Math.max(l.numStrongConnected, max);
		}
		io.println(numPeople - max);
		
		io.flush();
		io.close();
	}
	
	public void findStrongConnected(Language start) {
		Stack<Language> stack = new Stack<>();
		HashSet<Language> visited1 = new HashSet<>();
		HashSet<Language> visited2 = new HashSet<>();
		dfs1(start, stack, visited1);
		while (!stack.isEmpty()) {
			if (visited2.contains(stack.peek())) {
				stack.pop();
				continue;
			}
			HashSet<Language> strongConnected = new HashSet<>();
			int sum = dfs2(stack.pop(), visited1, visited2, strongConnected);
			for (Language l : strongConnected) {
				l.numStrongConnected = sum;
			}
		}
	}
	
	public void dfs1(Language curr, Stack<Language> stack, HashSet<Language> visited) {
		visited.add(curr);
		for (Language l : curr.others) {
			if (!visited.contains(l)) {
				dfs1(l, stack, visited);
			}
		}
		stack.push(curr);
	}
	
	public int dfs2(Language curr, HashSet<Language> visited1, HashSet<Language> visited2, HashSet<Language> strongConnected) {
		visited2.add(curr);
		strongConnected.add(curr);
		int sum = curr.count;
		for (Language l : curr.back) {
			if (!visited2.contains(l) && visited1.contains(l)) {
				sum += dfs2(l, visited1, visited2, strongConnected);
			}
		}
		return sum;
	}
	
	public Language getLanguage(String s) {
		for (Language l : all) {
			if (l.name.equals(s)) {
				return l;
			}
		}
		Language l = new Language(s);
		all.add(l);
		return l;
	}
	
	public static void main(String[] args) {
		new CantinaOfBabel().go();
	}
	
	private class Language {
		
		String name;
		int count, numStrongConnected;
		boolean visit;
		HashSet<Language> others;
		HashSet<Language> back;
		
		public Language(String n) {
			name = n;
			others = new HashSet<>();
			back = new HashSet<>();
		}
		
		public void add(Language l) {
			others.add(l);
			l.back.add(this);
		}
		
		public int hashCode() {
			return name.hashCode();
		}
		
		public boolean equals(Object obj) {
			if (obj instanceof Language) {
				Language other = (Language)obj;
				return name.equals(other.name);
			}
			return false;
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
