import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Vote {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numPeople = io.nextInt();
			int[] votes = new int[numPeople];
			int sum = 0;
			int bestPerson = 0;
			int bestVotes = -1;
			int numBest = 0;
			for (int i = 0; i < numPeople; i++) {
				votes[i] = io.nextInt();
				sum += votes[i];
				if (votes[i] > bestVotes) {
					numBest = 1;
					bestPerson = i;
					bestVotes = votes[i];
				} else if (votes[i] == bestVotes) {
					numBest++;
				}
			}
			boolean foundMajority = false;
			for (int i = 0; i < numPeople; i++) {
				if (votes[i] > sum/2) {
					io.printf("majority winner %d%n", i+1);
					foundMajority = true;
					break;
				} 
			}
			if (numBest > 1) {
				io.println("no winner");
			} else if (!foundMajority) {
				io.printf("minority winner %d%n", bestPerson+1);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Vote().go();
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
