import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Banjo {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			double x1 = io.nextInt();
			double y1 = io.nextInt();
			double x2 = io.nextInt();
			double y2 = io.nextInt();
			double xc = io.nextInt();
			double yc = io.nextInt();
			double r = io.nextInt();
			double t = io.nextInt();
			if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0 && xc == 0 && yc == 0 && r == 0 && t == 0) {
				break;
			}
			x2 -= x1;
			y2 -= y1;
			xc -= x1;
			yc -= y1;
			x1 = 0;
			y1 = 0;
			double rotate = -Math.atan2(y2, x2);
			double tempX = x2*Math.cos(rotate) - y2*Math.sin(rotate);
			double tempY = x2*Math.sin(rotate) + y2*Math.cos(rotate);
			x2 = tempX;
			y2 = tempY;
			tempX = xc*Math.cos(rotate) - yc*Math.sin(rotate);
			tempY = xc*Math.sin(rotate) + yc*Math.cos(rotate);
			xc = tempX;
			yc = tempY;
			yc = Math.abs(yc);
//			double circleAngle = Math.asin(t/2/r)*2;
//			double distance = 0;
//			if (yc >= r) {
//				distance = x2;
//			} else if (t == 0) {
//				double leftAngle = Math.acos(r*r/(xc*xc+yc*yc));
//				double rightAngle = Math.acos(r*r/(Math.pow(x2-xc, 2)+Math.pow(y2-yc, 2)));
//				circleAngle = Math.PI-leftAngle-rightAngle;
//				rotate = Math.atan2(yc, xc);
//				distance += Math.sqrt(Math.pow(xc-r*Math.cos(leftAngle+rotate), 2) + Math.pow(yc-r*Math.sin(leftAngle+rotate), 2));
//				distance += Math.sqrt(Math.pow(xc-r*Math.cos(leftAngle+rotate)-x2, 2) + Math.pow(yc-r*Math.sin(leftAngle+rotate)-y2, 2));
//				distance += r*circleAngle;
//			} else {
//				double leftAngle = Math.PI/2-circleAngle/2;
//				double rightAngle = Math.PI/2-circleAngle/2;
//				double edgeX = -r*Math.cos(leftAngle)+xc;
//				double edgeY = -r*Math.sin(leftAngle)+yc;
//				double d1 = Math.hypot(xc, yc);
//				double theta1 = Math.asin(r*r*Math.sin(circleAngle)/t/d1);
//				double theta2 = Math.asin(r*Math.sin(Math.PI/2-leftAngle)/Math.hypot(edgeX, edgeY));
//				while (theta2 < theta1) {
//					distance += t;
//					leftAngle -= circleAngle;
//					edgeX = -r*Math.cos(leftAngle)+xc;
//					edgeY = -r*Math.sin(leftAngle)+yc;
//					theta2 = Math.asin(r*Math.sin(Math.PI/2-leftAngle)/Math.hypot(edgeX, edgeY));
//				}
//				distance += Math.hypot(edgeX, edgeY);
//				edgeX = r*Math.cos(rightAngle)+xc;
//				edgeY = -r*Math.sin(rightAngle)+yc;
//				double d2 = Math.hypot(x2-xc, y2-yc);
//				theta1 = Math.asin(r*r*Math.sin(circleAngle)/t/d2);
//				theta2 = Math.asin(r*Math.sin(Math.PI/2-rightAngle)/Math.hypot(edgeX-xc, edgeY-yc));
//				while (theta2 < theta1) {
//					distance += t;
//					rightAngle -= circleAngle;
//					edgeX = r*Math.cos(rightAngle)+xc;
//					edgeY = -r*Math.sin(rightAngle)+yc;
//					theta2 = Math.asin(r*Math.sin(Math.PI/2-rightAngle)/Math.hypot(edgeX-xc, edgeY-yc));
//				}
//				distance += Math.hypot(edgeX-xc, edgeY-yc);
//				distance += t;
//			}
//			io.printf("%.2f%n", distance);
			
			double distance = 0;
			double circleAngle = Math.asin(t/2/r)*2;
			if (yc >= r || r*2 <= t) {
				distance = x2;
			} else if (t == 0) {
				double leftAngle = Math.acos(r*r/(xc*xc+yc*yc));
				double rightAngle = Math.acos(r*r/(Math.pow(x2-xc, 2)+Math.pow(y2-yc, 2)));
				circleAngle = Math.PI-leftAngle-rightAngle;
				rotate = Math.atan2(yc, xc);
				distance += Math.sqrt(Math.pow(xc-r*Math.cos(leftAngle+rotate), 2) + Math.pow(yc-r*Math.sin(leftAngle+rotate), 2));
				distance += Math.sqrt(Math.pow(xc-r*Math.cos(leftAngle+rotate)-x2, 2) + Math.pow(yc-r*Math.sin(leftAngle+rotate)-y2, 2));
				distance += r*circleAngle;
			} else {
				double d1 = Math.hypot(xc, yc);
				double lower = 0;
				double upper = Math.asin(r/d1);
				double angle = (lower+upper)/2;
				double inPit = 2*Math.sqrt(r*r-d1*d1*Math.pow(Math.sin(angle), 2));
				int it = 0;
				while (Math.abs(inPit-t) > 0.00001) {
					it++;
					if (inPit > t) {
						lower = angle;
					} else {
						upper = angle;
					}
					angle = (lower+upper)/2;
					inPit = 2*Math.sqrt(r*r-d1*d1*Math.pow(Math.sin(angle), 2));
					if (it == 500) {
//						break;
					}
				}
//				io.println(it + " " + d1);
				double gamma1 = Math.min(Math.PI-Math.asin(d1*Math.sin(angle)/r)-angle, Math.asin(d1*Math.sin(angle)/r)-angle);
				double d2 = Math.hypot(xc-x2, yc-y2);
				lower = 0;
				upper = Math.asin(r/d2);
				angle = (lower+upper)/2;
				inPit = 2*Math.sqrt(r*r-d2*d2*Math.pow(Math.sin(angle), 2));
				it = 0;
				while (Math.abs(inPit-t) > 0.00001) {
					it++;
					if (inPit > t) {
						lower = angle;
					} else {
						upper = angle;
					}
					angle = (lower+upper)/2;
					inPit = 2*Math.sqrt(r*r-d2*d2*Math.pow(Math.sin(angle), 2));
					if (it == 500) {
//						break;
					}
				}
//				io.println(it + " " + d2);
				double gamma2 = Math.min(Math.PI-Math.asin(d2*Math.sin(angle)/r)-angle, Math.asin(d2*Math.sin(angle)/r)-angle);
				double crossAngle = Math.PI-gamma1-gamma2;
				int numCrossing = (int)Math.floor(crossAngle/circleAngle+0.01);
//				io.println(gamma1/Math.PI*180 + " " + gamma2/Math.PI*180);
//				io.println(crossAngle + " " + circleAngle);
//				io.println(numCrossing);
				distance += Math.hypot(xc-r*Math.cos(Math.PI/2-circleAngle*numCrossing/2), yc-r*Math.sin(Math.PI/2-circleAngle*numCrossing/2));
				distance += Math.hypot(xc+r*Math.cos(Math.PI/2-circleAngle*numCrossing/2)-x2, yc-r*Math.sin(Math.PI/2-circleAngle*numCrossing/2));
				distance += t*numCrossing;
			}
			io.printf("%.2f%n", distance);
			
//			if (yc-targetY >= 0 || xc < 0 || xc > x2 || t > r*2) {
//				io.printf("%.2f%n", x2-x1);
//			} else {
//				double distance = 0;
//				distance += Math.sqrt(Math.pow(xc-t/2, 2) + Math.pow(yc-targetY, 2));
//				distance += t;
//				distance += Math.sqrt(Math.pow(x2-xc-t/2, 2) + Math.pow(yc-targetY, 2));
//				io.printf("%.2f%n", distance);
//			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Banjo().go();
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
