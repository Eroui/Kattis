import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Bank {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numPeople = io.nextInt();
		int time = io.nextInt();
		int[] bestTimes = new int[time];
		int[][] people = new int[numPeople][2];
		for (int i = 0; i < numPeople; i++) {
			people[i][0] = io.nextInt();
			people[i][1] = io.nextInt();
		}
		Arrays.sort(people, new Comparator<int[]>() {
			public int compare(int[] one, int[] two) {
				if (one[1] == two[1]) {
					return two[0] - one[0];
				}
				return one[1] - two[1];
			}
		});
		for (int i = 0; i < numPeople; i++) {
			int minIndex = -1;
			int minMoney = Integer.MAX_VALUE;
			for (int t = people[i][1]; t >= 0; t--) {
				if (people[i][0] > bestTimes[t] && bestTimes[t] < minMoney) {
					minIndex = t;
					minMoney = bestTimes[t];
				}
			}
			if (minIndex != -1) {
				bestTimes[minIndex] = people[i][0];
			}
		}
		long sum = 0;
		for (int i = 0; i < bestTimes.length; i++) {
			sum += bestTimes[i];
		}
		io.println(sum);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Bank().go();
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
