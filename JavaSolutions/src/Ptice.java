import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Ptice {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numQuestions = io.nextInt();
		int[] correct = new int[3];
		int best = 0;
		String questions = io.next();
		String adrian = "ABC";
		String bruno = "BABC";
		String goran = "CCAABB";
		for (int i = 0; i < numQuestions; i++) {
			char c = questions.charAt(i);
			if (adrian.charAt(i%3) == c) {
				correct[0]++;
				best = Math.max(correct[0], best);
			}
			if (bruno.charAt(i%4) == c) {
				correct[1]++;
				best = Math.max(correct[1], best);
			}
			if (goran.charAt(i%6) == c) {
				correct[2]++;
				best = Math.max(correct[2], best);
			}
		}
		io.println(best);
		if (correct[0] == best) {
			io.println("Adrian");
		}
		if (correct[1] == best) {
			io.println("Bruno");
		}
		if (correct[2] == best) {
			io.println("Goran");
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Ptice().go();
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
