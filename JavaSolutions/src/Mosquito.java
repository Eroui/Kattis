import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Mosquito {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		while (io.hasNext()) {
			int mosquito = io.nextInt();
			if (mosquito == -1) {
				break;
			}
			int pupae = io.nextInt();
			int larvae = io.nextInt();
			int eggs = io.nextInt();
			int lRate = io.nextInt();
			int pRate = io.nextInt();
			int n = io.nextInt();
			for (int i = 0; i < n; i++) {
				int hatched = pupae / pRate;
				pupae = larvae / lRate;
				larvae = mosquito * eggs;
				mosquito = hatched;
			}
			io.println(mosquito);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Mosquito().go();
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
