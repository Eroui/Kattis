import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Judging {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numSubmissions = io.nextInt();
		HashMap<String, Integer> kattis = new HashMap<>();
		HashMap<String, Integer> dom = new HashMap<>();
		HashSet<String> all = new HashSet<>();
		for (int i = 0; i < numSubmissions; i++) {
			String next = io.next();
			if (kattis.get(next) == null) {
				kattis.put(next, 0);
			}
			kattis.put(next, kattis.get(next)+1);
			all.add(next);
		}
		for (int i = 0; i < numSubmissions; i++) {
			String next = io.next();
			if (dom.get(next) == null) {
				dom.put(next, 0);
			}
			dom.put(next, dom.get(next)+1);
			all.add(next);
		}
		int numDiff = 0;
		for (String s : all) {
			if (dom.get(s) == null) {
				dom.put(s, 0);
			}
			if (kattis.get(s) == null) {
				kattis.put(s, 0);
			}
			numDiff += Math.abs(dom.get(s)-kattis.get(s));
		}
		io.println(numSubmissions-numDiff/2);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Judging().go();
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
	    	peekToken();
	    	return line;
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
