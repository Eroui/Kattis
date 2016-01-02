import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Chopin {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int currCase = 0;
		while (io.hasNext()) {
			currCase++;
			String key = io.next();
			String tone = io.next();
			switch (key) {
			case "A#":
				io.printf("Case %d: %s %s%n", currCase, "Bb", tone);
				break;
			case "Bb":
				io.printf("Case %d: %s %s%n", currCase, "A#", tone);
				break;
			case "C#":
				io.printf("Case %d: %s %s%n", currCase, "Db", tone);
				break;
			case "Db":
				io.printf("Case %d: %s %s%n", currCase, "C#", tone);
				break;
			case "D#":
				io.printf("Case %d: %s %s%n", currCase, "Eb", tone);
				break;
			case "Eb":
				io.printf("Case %d: %s %s%n", currCase, "D#", tone);
				break;
			case "F#":
				io.printf("Case %d: %s %s%n", currCase, "Gb", tone);
				break;
			case "Gb":
				io.printf("Case %d: %s %s%n", currCase, "F#", tone);
				break;
			case "G#":
				io.printf("Case %d: %s %s%n", currCase, "Ab", tone);
				break;
			case "Ab":
				io.printf("Case %d: %s %s%n", currCase, "G#", tone);
				break;
			default:
				io.printf("Case %d: UNIQUE%n", currCase);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Chopin().go();
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
