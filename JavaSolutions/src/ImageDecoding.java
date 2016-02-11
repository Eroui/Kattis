import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ImageDecoding {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		boolean first = true;
		while (io.hasNext()) {
			int numLines = io.nextInt();
			if (numLines == 0) {
				break;
			}
			if (!first) {
				io.println();
			}
			int currSize = -1;
			boolean error = false;
			for (int i = 0; i < numLines; i++) {
				String[] line = io.nextLine().split(" ");
				char curr = line[0].charAt(0);
				int size = 0;
				for (int e = 1; e < line.length; e++) {
					int next = Integer.parseInt(line[e]);
					size += next;
					for (int w = 0; w < next; w++) {
						io.print(curr);
					}
					curr = curr == '#' ? '.' : '#';
				}
				io.println();
				if (currSize == -1) {
					currSize = size;
				} else if (size != currSize) {
					error = true;
				}
			}
			if (error) {
				io.println("Error decoding image");
			}
			first = false;
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ImageDecoding().go();
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
