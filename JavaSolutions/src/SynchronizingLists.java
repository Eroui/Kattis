import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class SynchronizingLists {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		boolean first = true;
		while (io.hasNext()) {
			int size = io.nextInt();
			if (size == 0) {
				break;
			}
			if (!first) {
				io.println();
			}
			int[] orig = new int[size];
			int[] sort1 = new int[size];
			int[] sort2 = new int[size];
			HashMap<Integer, Integer> map = new HashMap<>(5000);
			for (int i = 0; i < size; i++) {
				int next = io.nextInt();
				orig[i] = next;
				sort1[i] = next;
			}
			for (int i = 0; i < size; i++) {
				sort2[i] = io.nextInt();
			}
			Arrays.sort(sort1);
			Arrays.sort(sort2);
			for (int i = 0; i < size; i++) {
				map.put(sort1[i], sort2[i]);
			}
			for (int i : orig) {
				io.println(map.get(i));
			}
			first = false;
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new SynchronizingLists().go();
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
