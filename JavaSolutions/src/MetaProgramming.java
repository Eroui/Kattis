import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class MetaProgramming {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		String line;
		HashMap<String, Integer> vals = new HashMap<>();
		while (true) {
			line = io.nextLine();
			if (line == null || line.equals("end")) {
				break;
			}
			if (line.startsWith("define")) {
				String num = line.split(" ")[1];
				vals.put(line.substring(line.indexOf(num) + num.length() + 1), Integer.parseInt(num));
			} else {
				String[] split = line.substring(5).split(" [<>=] ");
				if (vals.get(split[0]) == null || vals.get(split[1]) == null) {
					io.println("undefined");
				} else if (line.contains("<")) {
					io.println(vals.get(split[0]) < vals.get(split[1]));
				} else if (line.contains(">")) {
					io.println(vals.get(split[0]) > vals.get(split[1]));
				} else {
					io.println(vals.get(split[0]) == vals.get(split[1]));
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new MetaProgramming().go();
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
