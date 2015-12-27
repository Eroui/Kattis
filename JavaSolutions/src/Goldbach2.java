import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Goldbach2 {
	
	public void go() {
		int[] primes = new int[3432];
		int p = 0;
		for (int i = 2; i < 32000; i++) {
			boolean isPrime = true;
			for (int e = 0; e < p; e++) {
				if (i % primes[e] == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes[p++] = i;
			}
		}
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int num = io.nextInt();
			StringBuilder sb = new StringBuilder();
			int count = 0;
			for (int i = 0; i < primes.length; i++) {
				if (primes[i] >= num) {
					break;
				}
				for (int e = i; e < primes.length; e++) {
					if (primes[i] + primes[e] == num) {
						sb.append(String.format("%d+%d%n", primes[i], primes[e]));
						count++;
					} else if (primes[i] + primes[e] > num) {
						break;
					}
				}
			}
			io.printf("%d has %d representation(s)%n%s", num, count, sb.toString());
			if (zzz < zz-1) {
				io.println();
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Goldbach2().go();
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
