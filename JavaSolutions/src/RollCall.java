import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class RollCall {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		ArrayList<String[]> names = new ArrayList<>();
		HashSet<String> dupes = new HashSet<>();
		HashSet<String> firsts = new HashSet<>();
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("end")) {
				break;
			}
			String[] split = line.split(" ");
			names.add(split);
			if (firsts.contains(split[0])) {
				dupes.add(split[0]);
			} else {
				firsts.add(split[0]);
			}
		}
		Collections.sort(names, new Comparator<String[]>() {
			public int compare(String[] one, String[] two) {
				if (one[1].equals(two[1])) {
					return one[0].compareTo(two[0]);
				}
				return one[1].compareTo(two[1]);
			}
		});
		for (String[] name : names) {
			if (dupes.contains(name[0])) {
				io.printf("%s %s%n", name[0], name[1]);
			} else {
				io.println(name[0]);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new RollCall().go();
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
