import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Karte {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		String line = io.nextLine();
		boolean[][] deck = new boolean[4][13];
		int[] taken = new int[4];
		boolean duplicate = false;
		while (!line.isEmpty()) {
			char cSuit = line.charAt(0);
			int val = Integer.parseInt(line.substring(1,3))-1;
			line = line.substring(3);
			int suit = 0;
			switch (cSuit) {
			case 'P':
				suit = 0;
				break;
			case 'K':
				suit = 1;
				break;
			case 'H':
				suit = 2;
				break;
			case 'T':
				suit = 3;
				break;
			}
			taken[suit]++;
			if (deck[suit][val]) {
				duplicate = true;
				break;
			}
			deck[suit][val] = true;
		}
		if (duplicate) {
			io.print("GRESKA");
		} else {
			io.printf("%d %d %d %d", 13-taken[0], 13-taken[1], 13-taken[2], 13-taken[3]);
		}
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Karte().go();
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
