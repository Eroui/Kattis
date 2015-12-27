import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Help2 {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			String[] one = io.nextLine().split(" ");
			String[] two = io.nextLine().split(" ");
			boolean foundTag = true;
			boolean foundWord = false;
			boolean works = one.length == two.length;
			while (foundTag && works) {
				foundTag = false;
				foundWord = false;
				for (int i = 0; i < one.length; i++) {
					if (isWord(one[i]) || isWord(two[i])) {
						if (isWord(one[i]) && isWord(two[i]) && !one[i].equals(two[i])) {
							works = false;
							break;
						} else if (isWord(one[i]) && !isWord(two[i])) {
							replaceAll(two[i], one[i], two);
							foundWord = true;
							foundTag = true;
						} else if (!isWord(one[i]) && isWord(two[i])){
							replaceAll(one[i], two[i], one);
							foundWord = true;
							foundTag = true;
						}
					} else {
						foundTag = true;
					}
				}
				if (!foundWord && foundTag) {
					foundTag = true;
					for (int i = 0; i < one.length; i++) {
						if (!isWord(one[i])) {
							replaceAll(one[i], "a", one);
							break;
						}
					}
				}
			}
			if (works) {
				io.println(Arrays.toString(one).replaceAll("[\\[\\],]", ""));
			} else {
				io.println("-");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public boolean isWord(String s) {
		return s.charAt(0) != '<';
	}
	
	public void replaceAll(String key, String rep, String[] ar) {
		for (int i = 0; i < ar.length; i++) {
			if (ar[i].equals(key)){
				ar[i] = rep;
			}
		}
	}
	
	public static void main(String[] args) {
		new Help2().go();
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
