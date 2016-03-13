import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class EscapePlan {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int scenario = 1;
		while (true) {
			int numRobots = io.nextInt();
			if (numRobots == 0) {
				break;
			}
			double[][] robots = new double[numRobots][2];
			for (int i = 0; i < numRobots; i++) {
				robots[i] = new double[] {io.nextDouble()/10, io.nextDouble()/10};
			}
			int numHoles = io.nextInt();
			double[][] holes = new double[numHoles][2];
			for (int i = 0; i < numHoles; i++) {
				holes[i] = new double[] {io.nextDouble()/10, io.nextDouble()/10};
			}
			io.printf("Scenario %d%n", scenario++);
			for (int zz = 5; zz <= 20; zz*=2) {
				boolean[][] reached = new boolean[numRobots][numHoles];
				for (int r = 0; r < numRobots; r++) {
					for (int h = 0; h < numHoles; h++) {
						double dist = Math.pow(robots[r][0]-holes[h][0], 2)+Math.pow(robots[r][1]-holes[h][1], 2);
						reached[r][h] = dist <= zz*zz;
					}
				}
				int flow = maxFlow(reached);
				io.printf("In %d seconds %d robot(s) can escape%n", zz, flow);
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}

	public int maxFlow(boolean[][] mat) {
		int[] matched = new int[mat[0].length];
		Arrays.fill(matched, -1);
		int count = 0;
		for (int i = 0; i < mat.length; i++) {
			boolean[] seen = new boolean[matched.length];
			if (bfs(mat, i, seen, matched)) {
				count++;
			}
		}
		return count;
	}
	
	public boolean bfs(boolean[][] mat, int curr, boolean[] seen, int[] matched) {
		for (int i = 0; i < mat[curr].length; i++) {
			if (mat[curr][i] && !seen[i]) {
				seen[i] = true;
				if (matched[i] == -1 || bfs(mat, matched[i], seen, matched)) {
					matched[i] = curr;
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		new EscapePlan().go();
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
