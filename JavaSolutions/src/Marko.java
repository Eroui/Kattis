import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Marko {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		HashMap<Character, Character> translate = new HashMap<>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] numpad = "22233344455566677778889999".toCharArray();
		for (int i = 0; i < 26; i++) {
			translate.put(alphabet[i], numpad[i]);
		}
		int numWords = io.nextInt();
		String[] words = new String[numWords];
		for (int i = 0; i < numWords; i++) {
			words[i] = io.next();
		}
		String presses = io.next();
		int numCorrect = 0;
		for (String s : words) {
			boolean works = true;
			for (int i = 0; i < s.length(); i++) {
				if (translate.get(s.charAt(i)) != presses.charAt(i)) {
					works = false;
					break;
				}
			}
			if (works) {
				numCorrect++;
			}
		}
		io.println(numCorrect);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Marko().go();
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
