import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Perica {
	
	Kattio io;
	long MOD = 1000000007;
	long x = 0, y = 0;
	
	public void go() {
		io = new Kattio(System.in);
		int numKeys = io.nextInt();
		int numPick = io.nextInt();
		if (numPick <= numKeys) {
			int[] keys = new int[numKeys];
			for (int i = 0; i < numKeys; i++) {
				keys[i] = io.nextInt();
			}
			Arrays.sort(keys);
			double sum = 0;
			for (int i = numKeys-1; i >= numPick; i--) {
				double toAdd = (keys[i] * choose(i, numPick-1)) % (MOD*1000);
				sum = (sum + toAdd) % (MOD*1000);
			}
			sum = (sum + keys[numPick-1]) % MOD;
			io.println(Math.round(sum) % MOD);
		} else {
			io.println(0);
		}
		
		io.flush();
		io.close();
	}
	
	public long choose(int n, int k) {
		long ans = 1;
		for (int i = n; i > n-k; i--) {
			ans = (ans * i) % MOD;
		}
		long den = 1;
		for (int i = k; i > 1; i--) {
			den = (den * i) % MOD;
		}
		long inv = inverse(den, MOD);
		return (ans * inv) % MOD;
	}
	
	public long xgcd(long a, long b) {
		out.println(a + " " + b);
		if (b == 0) {
			x = 1;
			y = 0;
			return a;
		}
		long gcd = xgcd(b, a%b);
		long temp = y;
		y = x - (a/b)*y;
		x = temp;
		return gcd;
	}
	
	public long inverse(long num, long mod) {
		long a = 1;
		long b = num;
		while (b != 1) {
			long mult = mod/b+1;
			a = (a * mult) % mod;
			b = (b * mult) % mod;
		}
		return a;
	}
	
	public static void main(String[] args) {
		new Perica().go();
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
