import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Debugging {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numLines = io.nextInt();
		long runtime = io.nextInt();
		long printTime = io.nextLong();
		long totalTime = getBestTime(numLines, runtime, printTime);
		io.println(totalTime);

		io.flush();
		io.close();
	}
	
	public long getBestTime(int numLines, long runtime, long printTime) {
		if (numLines == 1) {
			return 0;
		}
		long minTime = Long.MAX_VALUE;
		int bestDiv = 1;
		for (int i = 2; i <= numLines; i++) {
			long time = 0;
			int tempNumLines = numLines;
			while (tempNumLines > 1) {
				time += printTime*(i-1)+runtime;
				tempNumLines = (tempNumLines+i-1)/i;
			}
			if (time < minTime) {
				minTime = time;
				bestDiv = i;
			}
		}
		minTime = printTime*(bestDiv-1)+runtime + getBestTime((numLines+bestDiv-1)/bestDiv, runtime, printTime);
		if (bestDiv > 2) {
			minTime = Math.min(minTime, printTime*(bestDiv-2)+runtime + getBestTime((numLines+bestDiv-2)/(bestDiv-1), runtime, printTime));
		}
		if (bestDiv < numLines) {
			minTime = Math.min(minTime, printTime*(bestDiv)+runtime + getBestTime((numLines+bestDiv)/(bestDiv+1), runtime, printTime));
		}
		return minTime;
	}
	
	public static void main(String[] args) {
		new Debugging().go();
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
