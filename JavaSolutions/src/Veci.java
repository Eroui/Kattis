import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Veci {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int num = io.nextInt();
		ArrayList<Integer> digits = new ArrayList<>(10);
		for (int i = (num+"").length(); i > 0; i--) {
			digits.add(num % (int)Math.pow(10, i) / (int)Math.pow(10, i-1));
		}
		HashSet<Integer> combos = new HashSet<>();
		getCombos(digits, 0, combos);
		int min = Integer.MAX_VALUE;
		for (int i : combos) {
			if (i > num) {
				min = Math.min(i, min);
			}
		}

//		io.println(digits);
		if (min == Integer.MAX_VALUE) {
			io.println(0);
		} else {
			io.println(min);
		}
		
		io.flush();
		io.close();
	}
	
	public void getCombos(ArrayList<Integer> digits, int num, HashSet<Integer> combos) {
		if (digits.isEmpty()) {
			combos.add(num);
		} else {
			for (int i = 0; i < digits.size(); i++) {
				int n = digits.remove(i);
				num *= 10;
				num += n;
				getCombos(digits, num, combos);
				num /= 10;
				digits.add(i, n);
			}
		}
	}
	
	public static void main(String[] args) {
		new Veci().go();
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
