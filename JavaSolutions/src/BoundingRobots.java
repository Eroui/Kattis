import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BoundingRobots {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		while (true) {
			int width = io.nextInt();
			int height = io.nextInt();
			if (width == 0 && height == 0) {
				break;
			}
			int numMoves = io.nextInt();
			int actualX = 0;
			int actualY = 0;
			int robotX = 0;
			int robotY = 0;
			for (int i = 0; i < numMoves; i++) {
				char direction = io.next().charAt(0);
				int move = io.nextInt();
				switch (direction) {
				case 'u':
					robotY += move;
					actualY = Math.min(actualY+move, height-1);
					break;
				case 'd':
					robotY -= move;
					actualY = Math.max(actualY-move, 0);
					break;
				case 'l':
					robotX -= move;
					actualX = Math.max(actualX-move, 0);
					break;
				case 'r':
					robotX += move;
					actualX = Math.min(actualX+move, width-1);
					break;
				}
			}
			io.printf("Robot thinks %d %d%n", robotX, robotY);
			io.printf("Actually at %d %d%n", actualX, actualY);
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BoundingRobots().go();
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
