import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Towering {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int[] boxes = new int[6];
		for (int i = 0; i < 6; i++) {
			boxes[i] = io.nextInt();
		}
		Arrays.sort(boxes);
		for (int i = 0; i < 3; i++) {
			int temp = boxes[i];
			boxes[i] = boxes[5-i];
			boxes[5-i] = temp;
		}
		int heightOne = io.nextInt();
		int heightTwo = io.nextInt();
		OUTER:
		for (int i = 0; i < 4; i++) {
			for (int e = i+1; e < 5; e++) {
				for (int w = e+1; w < 6; w++) {
					if (boxes[i] + boxes[e] + boxes[w] == heightOne) {
						io.printf("%d %d %d", boxes[i], boxes[e], boxes[w]);
						for (int q = 0; q < 6; q++) {
							if (q != i && q != e && q != w) {
								io.printf(" %d", boxes[q]);
							}
						}
						break OUTER;
					}
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Towering().go();
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
