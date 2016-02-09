import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Kolone {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numRow1 = io.nextInt();
		int numRow2 = io.nextInt();
		char[] row1 = new char[numRow1+numRow2];
		char[] row2 = new char[row1.length];
		Arrays.fill(row1, ' ');
		Arrays.fill(row2, ' ');
		int i = numRow1-1;
		for (char c : io.nextLine().toCharArray()) {
			row1[i--] = c;
		}
		i = numRow1;
		for (char c : io.nextLine().toCharArray()) {
			row2[i++] = c;
		}
		int t = io.nextInt();
		for (i = 0; i < t; i++) {
			boolean[] swap = new boolean[row1.length-1];
			for (int e = 0; e < row1.length-1; e++) {
				if (row1[e] != ' ' && row2[e+1] != ' ') {
					swap[e] = true;
				}
			}
			for (int e = 0; e < row1.length-1; e++) {
				if (swap[e]) {
					row1[e+1] = row1[e];
					row1[e] = ' ';
					row2[e] = row2[e+1];
					row2[e+1] = ' ';
				}
			}
//			out.println(Arrays.toString(row1) + " " + Arrays.toString(row2));
		}
		for (i = 0; i < row1.length; i++) {
			if (row1[i] != ' ') {
				io.print(row1[i]);
			} else {
				io.print(row2[i]);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Kolone().go();
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
