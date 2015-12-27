import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class QuickBrownFox {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			String line = io.nextLine();
			boolean[] alpha = new boolean[26];
			for (char c : line.toCharArray()) {
				if (Character.isLetter(c)) {
					alpha[Character.toLowerCase(c)-'a'] = true;
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < alpha.length; i++) {
				if (!alpha[i]) {
					if (sb.length() == 0) {
						sb.append("missing ");
					}
					sb.append((char)(i+'a'));
				}
			}
			if (sb.length() == 0) {
				io.println("pangram");
			} else {
				io.println(sb);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new QuickBrownFox().go();
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
	    	peekToken();
	    	return line;
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
