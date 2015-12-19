import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class CD {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		while (io.hasNext()) {
			int numJack = io.nextInt();
			int numJill = io.nextInt();
			if (numJack == 0 && numJill == 0) {
				break;
			}
			int[] jack = new int[numJack];
			int[] jill = new int[numJill];
			for (int i = 0; i < numJack; i++) {
				jack[i] = io.nextInt();
			}
			for (int i = 0; i < numJill; i++) {
				jill[i] = io.nextInt();
			}
			int numSame = 0;
			int i = 0;
			int e = 0;
			while (i < jack.length && e < jill.length) {
				if (jack[i] == jill[e]){
					numSame++;
					e++;
					i++;
				} else if (jack[i] > jill[e]) {
					e++;
				} else {
					i++;
				}
			}
			io.println(numSame);
		}
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new CD().go();
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
