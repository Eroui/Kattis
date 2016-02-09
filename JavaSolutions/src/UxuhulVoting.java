import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class UxuhulVoting {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numPeople = io.nextInt();
			int[][] prefs = new int[8][2];
			for (int i = 0; i < numPeople; i++) {
				for (int e = 0; e < prefs.length; e++) {
					prefs[e][0] += io.nextInt()-1;
					prefs[e][1] = e;
				}
			}
			Arrays.sort(prefs, new Comparator<int[]>() {
				public int compare(int[] one, int[] two) {
					return one[0] - two[0];
				}
			});
			for (int[] ar : prefs) {
				if (numPeople%2 == 0 && (ar[1]==0 || ar[1]==3 || ar[1]==6 || ar[1]==5)) {
					io.println(String.format("%3s", Integer.toBinaryString(ar[1])).replace(' ', '0').replace('0', 'N').replace('1', 'Y'));
					break;
				} else if (numPeople%2 == 1 && (ar[1]==1 || ar[1]==2 || ar[1]==4)) {
					io.println(String.format("%3s", Integer.toBinaryString(ar[1])).replace(' ', '0').replace('0', 'N').replace('1', 'Y'));
					break;
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new UxuhulVoting().go();
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
