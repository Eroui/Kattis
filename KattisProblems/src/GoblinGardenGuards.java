import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class GoblinGardenGuards {
	
	//Used c++ to run within time limit, same algorithm
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int numGoblins = io.nextInt();
		int[][] goblins = new int[numGoblins][];
		for (int i = 0; i < numGoblins; i++) {
			goblins[i] = new int[] {io.nextInt(), io.nextInt()};
		}
		int numSprinklers = io.nextInt();
		int[][] sprinklers = new int[numSprinklers][];
		for (int i = 0; i < numSprinklers; i++) {
			sprinklers[i] = new int[] {io.nextInt(), io.nextInt(), io.nextInt()};
		}
		int count = 0;
		for (int g = 0; g < numGoblins; g++) {
			for (int s = 0; s < numSprinklers; s++) {
				int x = goblins[g][0] - sprinklers[s][0];
				int y = goblins[g][1] - sprinklers[s][1];
				if (x*x+y*y <= sprinklers[s][2]*sprinklers[s][2]) {
					count++;
					break;
				}
			}
		}
		io.print(numGoblins-count);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new GoblinGardenGuards().go();
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
