import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class TwoKnights {
	
	Kattio io;
	char[][] lowerBoard = new char[8][14];
	char[][] upperBoard = new char[8][14];
	HashMap<Character, int[]> map = new HashMap<>();
	int[] dr = {-2,-2,-1,1,2,2,1,-1};
	int[] dc = {-1,1,2,2,1,-1,-2,-2};
	int[][] shift = new int[4][2];
	int[][] space = new int[6][2];
	
	public void go() {
		io = new Kattio(System.in);
		String stringBoard = "qwertyuiopasdfghjkl;zxcvbnm,./^^      ^^";
		for (int i = 0; i < 8; i++) {
			Arrays.fill(lowerBoard[i], '*');
			Arrays.fill(upperBoard[i], '*');
		}
		for (int r = 2; r < 6; r++) {
			for (int c = 2; c < 12; c++) {
				lowerBoard[r][c] = stringBoard.charAt(c-2+(r-2)*10);
				map.put(lowerBoard[r][c], new int[]{r,c});
			}
		}
		stringBoard = "QWERTYUIOPASDFGHJKL:ZXCVBNM<>?^^      ^^";
		for (int r = 2; r < 6; r++) {
			for (int c = 2; c < 12; c++) {
				upperBoard[r][c] = stringBoard.charAt(c-2+(r-2)*10);
				map.put(upperBoard[r][c], new int[]{r,c});
			}
		}
		shift[0] = new int[]{5,2};
		shift[1] = new int[]{5,3};
		shift[2] = new int[]{5,10};
		shift[3] = new int[]{5,11};
		space[0] = new int[]{5,4};
		space[1] = new int[]{5,5};
		space[2] = new int[]{5,6};
		space[3] = new int[]{5,7};
		space[4] = new int[]{5,8};
		space[5] = new int[]{5,9};
		while (true) {
			String next = io.nextLine();
			if (next.equals("*")) {
				break;
			}
			io.println(isPossible(-1,-1,-1,-1,next.toCharArray(),next.length()-1) ? '1' : '0');
		}
		
		io.flush();
		io.close();
	}
	
	public boolean isPossible(int r1, int c1, int r2, int c2, char[] line, int i) {
		if (r1 != -1 && r1 == r2 && c1 == c2) {
			return false;
		}
		if (i == -1 && r1 == 5 && c1 == 3 && r2 == 5 && c2 == 11) {
			return true;
		}
//		io.print(line[i]);
		int rLet = map.get(line[i])[0];
		int cLet = map.get(line[i])[1];
		boolean find = false;
		if (line[i] == ' ') {
			for (int p = 0; p < 6 && !find; p++) {
				if (reach(r1,c1,space[p])) {
					find |= isPossible(space[p][0], space[p][1], r2, c2, line, i-1);
				}
				if (!find && reach(r2,c2,space[p])) {
					find |= isPossible(r1, c1, space[p][0], space[p][1], line, i-1);
				}
			}
		} else if (isUpper(line[i])) {
			for (int s = 0; s < 4 && !find; s++) {
				if (((shift[s][0] == r1 && shift[s][1] == c1) || reach(r1,c1,shift[s])) && reach(r2,c2,map.get(line[i]))) {
					find |= isPossible(shift[s][0], shift[s][1], rLet, cLet, line, i-1);
				}
				if (!find && ((shift[s][0] == r2 && shift[s][1] == c2) || reach(r2,c2,shift[s])) && reach(r1,c1,map.get(line[i]))) {
					find |= isPossible(rLet, cLet, shift[s][0], shift[s][1], line, i-1);
				}
			}
		} else {
			if (reach(r1,c1,map.get(line[i])) && !onShift(r2,c2)) {
				find |= isPossible(rLet, cLet, r2, c2, line, i-1);
			}
			if (!find && reach(r2,c2,map.get(line[i])) && !onShift(r1,c1)) {
				find |= isPossible(r1, c1, rLet, cLet, line, i-1);
			}
		}
		return find;
	}
	
	public boolean onShift(int r, int c) {
		for (int s = 0; s < 4; s++) {
			if (shift[s][0] == r && shift[s][1] == c) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUpper(char c) {
		return upperBoard[map.get(c)[0]][map.get(c)[1]] == c;
	}
	
	public boolean reach(int r, int c, int[] dest) {
		if (r == -1) {
			return true;
		}
		boolean canShift = false;
		int shiftSpot = -1;
		for (int i = 0; i < 8 && !canShift; i++) {
			for (int s = 0; s < 4 && !canShift; s++) {
				if (r+dr[i] == shift[s][0] && c+dc[i] == shift[s][1]) {
					canShift = true;
					shiftSpot = s;
				}
			}
		}
		if (canShift) {
			for (int i = 0; i < 8; i++) {
				if (dest[0]+dr[i] == shift[shiftSpot][0] && dest[1]+dc[i] == shift[shiftSpot][1]) {
					return true;
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			if (r+dr[i] == dest[0] && c+dc[i] == dest[1]) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		new TwoKnights().go();
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
