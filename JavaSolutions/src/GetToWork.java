import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class GetToWork {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numTowns = io.nextInt();
			int workTown = io.nextInt()-1;
			int numWorkers = io.nextInt();
			int[] townsPop = new int[numTowns];
			int[][] cars = new int[numTowns][numWorkers];
			for (int i = 0; i < numWorkers; i++) {
				int town = io.nextInt()-1;
				int car = io.nextInt();
				cars[town][townsPop[town]] = car;
				townsPop[town]++;
			}
			io.printf("Case #%d: ", zzz+1);
			int[] carsNeeded = new int[numTowns];
			boolean possible = true;
			for (int i = 0; i < numTowns; i++) {
				if (i == workTown) {
					continue;
				}
				Arrays.sort(cars[i]);
				int count = 0;
				while (townsPop[i] > 0 && count < cars[i].length) {
					townsPop[i] -= cars[i][cars[i].length-1-count];
					count++;
				}
				if (townsPop[i] > 0) {
					possible = false;
					break;
				} else {
					carsNeeded[i] = count;
				}
			}
			if (possible) {
				io.println(Arrays.toString(carsNeeded).replaceAll("[\\[\\],]", ""));
			} else {
				io.println("IMPOSSIBLE");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new GetToWork().go();
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
