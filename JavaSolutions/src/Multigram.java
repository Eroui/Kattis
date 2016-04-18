import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Multigram {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		String word = io.next();
		int i;
		for (i = word.length(); i > 1; i--) {
			if (word.length() % i != 0) {
				continue;
			}
			int[] freq = new int[26];
			int[] prevFreq = freq;
			boolean works = true;
			for (int e = 0; e < i; e++) {
				freq = new int[26];
				for (int w = 0; w < word.length()/i; w++) {
					freq[word.charAt(w+e*word.length()/i)-'a']++;
				}
				if (e != 0 && !Arrays.equals(prevFreq, freq)) {
					works = false;
					break;
				}
				prevFreq = freq;
			}
			if (works) {
				break;
			}
		}
		if (i == 1) {
			io.println(-1);
		} else {
			io.println(word.substring(0,word.length()/i));
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Multigram().go();
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
