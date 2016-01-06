import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class TouchscreenKeyboard {

	int[] locations = new int[26];
	
	public void go() {
		Kattio io = new Kattio(System.in);
		locations[0] = 10;
		locations[1] = 24;
		locations[2] = 22;
		locations[3] = 12;
		locations[4] = 2;
		locations[5] = 13;
		locations[6] = 14;
		locations[7] = 15;
		locations[8] = 7;
		locations[9] = 16;
		locations[10] = 17;
		locations[11] = 18;
		locations[12] = 26;
		locations[13] = 25;
		locations[14] = 8;
		locations[15] = 9;
		locations[16] = 0;
		locations[17] = 3;
		locations[18] = 11;
		locations[19] = 4;
		locations[20] = 6;
		locations[21] = 23;
		locations[22] = 1;
		locations[23] = 21;
		locations[24] = 5;
		locations[25] = 20;
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			String comp = io.next();
			int numWords = io.nextInt();
			ArrayList<Word> words = new ArrayList<>(numWords);
			for (int i = 0; i < numWords; i++) {
				words.add(new Word(io.next(), comp));
			}
			Collections.sort(words);
			io.println(words.toString().replaceAll("[\\[\\],]", "").replaceAll(" ", "\n").replaceAll("\\.", " "));
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new TouchscreenKeyboard().go();
	}
	
	private class Word implements Comparable<Word> {
		
		String word, orig;
		int dist;
		
		public Word(String w, String o) {
			word = w;
			orig = o;
			for (int i = 0; i < word.length(); i++) {
				int one = locations[word.charAt(i)-'a'];
				int two = locations[orig.charAt(i)-'a'];
				dist += Math.abs(one/10 - two/10) + Math.abs(one%10 - two%10);
			}
		}
		
		public int compareTo(Word other) {
			if (dist == other.dist) {
				return word.compareTo(other.word);
			}
			return dist - other.dist;
		}
		
		public String toString() {
			return word + "." + dist;
		}
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
