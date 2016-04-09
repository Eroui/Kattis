import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class SurveillanceSquared {
	
	Kattio io;
	double TWOPI = Math.PI*2;
	
	public void go() {
		io = new Kattio(System.in);
		boolean first = true;
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			if (!first) {
				io.println();
			}
			int numCams = Integer.parseInt(next);
			double[][] cams = new double[numCams][4];
			for (int i = 0; i < numCams; i++) {
				cams[i][0] = io.nextInt();
				cams[i][1] = io.nextInt();
				cams[i][2] = io.nextInt();
				cams[i][3] = io.nextInt();
			}
			for (int i = 0; i < numCams; i++) {
				double offset = Math.atan2(cams[i][3], cams[i][2]);
//				io.println(offset/Math.PI*180);
				int count = 0;
				for (int e = 0; e < numCams; e++) {
					if (i == e) {
						continue;
					}
					double angle = Math.atan2(cams[e][1]-cams[i][1], cams[e][0]-cams[i][0]);
//					io.println(angle/Math.PI*180);
					if (inRange(offset, angle)) {
						count++;
					}
				}
				io.println(count);
			}
			first = false;
		}
		
		io.flush();
		io.close();
	}
	
	public boolean inRange(double a1, double a2) {
		double diff = Math.min(Math.abs(a1-a2)%TWOPI,
				Math.min(Math.abs(a1-a2+TWOPI)%TWOPI,
						Math.abs(a1-a2-TWOPI)%TWOPI));
		return diff <= Math.PI/4;
	}
	
	public static void main(String[] args) {
		new SurveillanceSquared().go();
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
