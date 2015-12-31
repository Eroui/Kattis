import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ACM2 {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numProblems = io.nextInt();
		int first = io.nextInt();
		int[] times = new int[numProblems];
		for (int i = 0; i < numProblems; i++) {
			times[i] = io.nextInt();
		}
		int temp = times[0];
		times[0] = times[first];
		times[first] = temp;
		int currTime = 0;
		int penaltyTime = 0;
		int i;
		Arrays.sort(times, 1, times.length);
		for (i = 0; i < numProblems; i++) {
			if (currTime + times[i] > 300) {
				break;
			} else {
				currTime += times[i];
				penaltyTime += currTime;
			}
		}
		io.printf("%d %d", i, penaltyTime);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ACM2().go();
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
