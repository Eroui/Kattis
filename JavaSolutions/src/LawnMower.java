import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class LawnMower {
	
	Kattio io;
	long mult = 100000000;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numAcross = io.nextInt();
			int numDown = io.nextInt();
			long width = (long)(io.nextDouble()*mult);
			if (numAcross == 0 && numDown == 0 && width == 0) {
				break;
			}
			ArrayList<long[]> across = new ArrayList<long[]>();
			ArrayList<long[]> down = new ArrayList<long[]>();
			for (int i = 0; i < numAcross; i++) {
				long[] interval = new long[2];
				long loc = (long)(io.nextDouble()*mult);
				interval[0] = loc-width/2;
				interval[1] = loc+width/2;
				for (int e = 0; e < across.size(); e++) {
					long[] curr = across.get(e);
					if (interval[0] >= curr[0] && interval[1] <= curr[1]) {
						break;
					} else if (interval[0] >= curr[0] && interval[0] <= curr[1]
							|| interval[0] <= curr[0] && interval[1] >= curr[0]) {
						across.remove(e);
						e--;
						interval[0] = Math.min(interval[0], curr[0]);
						interval[1] = Math.max(interval[1], curr[1]);
					}
				}
				across.add(interval);
			}
			for (int i = 0; i < numDown; i++) {
				long[] interval = new long[2];
				long loc = (long)(io.nextDouble()*mult);
				interval[0] = loc-width/2;
				interval[1] = loc+width/2;
				for (int e = 0; e < down.size(); e++) {
					long[] curr = down.get(e);
					if (interval[0] >= curr[0] && interval[1] <= curr[1]) {
						break;
					} else if (interval[0] >= curr[0] && interval[0] <= curr[1]
							|| interval[0] <= curr[0] && interval[1] >= curr[0]) {
						down.remove(e);
						e--;
						interval[0] = Math.min(interval[0], curr[0]);
						interval[1] = Math.max(interval[1], curr[1]);
					}
				}
				down.add(interval);
			}
//			for (long[] ar : across) {
//				io.print(Arrays.toString(ar));
//			}
//			io.println();
//			for (long[] ar : down) {
//				io.print(Arrays.toString(ar));
//			}
//			io.println();
			if (across.get(0)[0] <= 0 && across.get(0)[1] >= 75*mult && down.get(0)[0] <= 0 && down.get(0)[1] >= 100*mult) {
				io.println("YES");
			} else {
				io.println("NO");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new LawnMower().go();
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
