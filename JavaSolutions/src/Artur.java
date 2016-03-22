import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Artur {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numSticks = io.nextInt();
		Line[] sticks = new Line[numSticks];
		for (int i = 0; i < numSticks; i++) {
			int x1 = io.nextInt();
			int y1 = io.nextInt();
			int x2 = io.nextInt();
			int y2 = io.nextInt();
			sticks[i] = new Line(x1, y1, x2, y2, i+1);
		}
		Arrays.sort(sticks);
		for (int i = 0; i < numSticks; i++) {
			if (i != 0) {
				io.print(" ");
			}
			io.print(sticks[i].i);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Artur().go();
	}
	
	private class Line implements Comparable<Line> {
		
		int x1, y1, x2, y2, i;
		double slope, yint;
		
		public Line(int x1, int y1, int x2, int y2, int i) {
			if (x1 != x2) {
				this.x1 = Math.min(x1, x2);
				this.y1 = this.x1 == x1 ? y1 : y2;
				this.x2 = Math.max(x1, x2);
				this.y2 = this.x2 == x2 ? y2 : y1;
				slope = (double)(y2-y1)/(double)(x2-x1);
				yint = y1-slope*x1;
			} else {
				this.x1 = x1;
				this.y1 = Math.min(y1, y2);
				this.x2 = x2;
				this.y2 = Math.max(y1, y2);
				slope = Double.POSITIVE_INFINITY;
			}
			this.i = i;
		}
		
		public int compareTo(Line other) {
			if (slope == Double.POSITIVE_INFINITY || other.slope == Double.POSITIVE_INFINITY) {
				return Math.min(y1, y2) - Math.min(other.y1, other.y2);
			} else if (x1 <= other.x1 && x2 >= other.x1) {
				double yLine = yint + slope*other.x1;
				if (yLine > other.y1) {
					return 1;
				} else {
					return -1;
				}
			} else if (x1 <= other.x2 && x2 >= other.x2) {
				double yLine = yint + slope*other.x2;
				if (yLine > other.y2) {
					return 1;
				} else {
					return -1;
				}
			} else if (x1 >= other.x1 && x2 <= other.x2) {
				double yLine = other.yint + other.slope*x1;
				if (yLine > y1) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return other.x1 - x1;
			}
		}
		
		public String toString() {
			return String.format("y=%.2fx+%.2f,%d", slope, yint, i);
		}
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
