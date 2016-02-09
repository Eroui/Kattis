import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class NotAmused {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int day = 1;
		while (io.hasNext()) {
			io.next();
			HashMap<String, Integer> lastIn = new HashMap<>();
			TreeMap<String, Integer> totalTime = new TreeMap<>();
			while (true) {
				String command = io.next();
				if (command.equals("CLOSE")) {
					break;
				} else if (command.equals("ENTER")) {
					lastIn.put(io.next(), io.nextInt());
				} else {
					String name = io.next();
					if (totalTime.get(name) == null) {
						totalTime.put(name, 0);
					}
					totalTime.put(name, totalTime.get(name)+(io.nextInt()-lastIn.get(name)));
				}
			}
			if (day != 1) {
				io.println();
			}
			io.printf("Day %d%n", day++);
			for (String s : totalTime.keySet()) {
				io.printf("%s $%.2f%n", s, totalTime.get(s)*(.1));
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new NotAmused().go();
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
