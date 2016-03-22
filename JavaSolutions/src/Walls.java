import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Walls {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int length = io.nextInt()*2;
		int width = io.nextInt()*2;
		int numCranes = io.nextInt();
		int reach = io.nextInt()*2;
		int[][] cranes = new int[numCranes][4];
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		for (int i = 0; i < numCranes; i++) {
			cranes[i][0] = io.nextInt()*2;
			cranes[i][1] = io.nextInt()*2;
			for (int e = 0; e < 4; e++) {
				int dist = (int)(Math.pow(cranes[i][0]-dx[e]*length/2, 2)
						+ Math.pow(cranes[i][1]-dy[e]*width/2, 2));
				if (dist <= reach*reach) {
					cranes[i][2]++;
					cranes[i][3] |= (int)Math.pow(2, e);
				}
			}
		}
		Arrays.sort(cranes, new Comparator<int[]>() {
			public int compare(int[] one, int[] two) {
				return two[2] - one[2];
			}
		});
		int reachable = 0;
		int numNeed = 0;
		boolean overlap = false;
		for (int i = 0; i < cranes.length; i++) {
			if ((cranes[i][3] | reachable) != reachable) {
				reachable |= cranes[i][3];
				if (cranes[i][2] != 2 || !overlap) {
					numNeed++;
				}
				if (cranes[i][2] == 2 && countBits(reachable) == 3) {
					overlap = true;
				}
			}
			if (reachable == 15) {
				break;
			}
		}
		if (reachable != 15) {
			io.println("Impossible");
		} else {
			io.println(numNeed);
		}
		
		io.flush();
		io.close();
	}
	
	public int countBits(int n) {
		int count = 0;
		while (n > 0) {
			if (n % 2 == 1) {
				count++;
			}
			n /= 2;
		}
		return count;
	}
	
	public static void main(String[] args) {
		new Walls().go();
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
