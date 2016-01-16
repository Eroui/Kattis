import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class HidingPlaces {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			String next = io.next();
			int startR = next.charAt(1)-'0'-1;
			int startC = (next.charAt(0)-'a');
			int[][] board = new int[8][8];
			for (int[] ar : board) {
				Arrays.fill(ar, Integer.MAX_VALUE);
			}
			int[] dr = {-1,-2,-2,-1,1,2,2,1};
			int[] dc = {-2,-1,1,2,2,1,-1,-2};
			LinkedList<int[]> queue = new LinkedList<>();
			queue.add(new int[]{startR, startC, 0});
			while (!queue.isEmpty()) {
				int[] curr = queue.remove();
				board[curr[0]][curr[1]] = curr[2];
				for (int i = 0; i < 8; i++) {
					if (curr[0]+dr[i] >= 0 && curr[0]+dr[i] < 8 && curr[1]+dc[i] >= 0 && curr[1]+dc[i] < 8
							&& curr[2]+1 < board[curr[0]+dr[i]][curr[1]+dc[i]]) {
						queue.add(new int[]{curr[0]+dr[i], curr[1]+dc[i], curr[2]+1});
					}
				}
			}
			int max = 0;
			for (int[] ar : board) {
				for (int i = 0; i < 8; i++) {
					max = Math.max(ar[i], max);
				}
			}
			io.print(max);
			for (int r = 7; r >= 0; r--) {
				for (int c = 0; c < 8; c++) {
					if (board[r][c] == max) {
						io.printf(" %c%c", (char)(c+'a'), (char)(r+'1'));
					}
				}
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new HidingPlaces().go();
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
