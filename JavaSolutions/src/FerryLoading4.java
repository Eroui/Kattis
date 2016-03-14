import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class FerryLoading4 {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int boatLength = io.nextInt()*100;
			int numCars = io.nextInt();
			ArrayList<Integer> carsLeft = new ArrayList<>(numCars);
			ArrayList<Integer> carsRight = new ArrayList<>(numCars);
			for (int i = 0; i < numCars; i++) {
				int carLength = io.nextInt();
				switch (io.next()) {
				case "left":
					carsLeft.add(carLength);
					break;
				case "right":
					carsRight.add(carLength);
					break;
				}
			}
//			Collections.sort(carsLeft);
//			Collections.sort(carsRight);
			int leftGroups = 0, rightGroups = 0;
			int currSize = 0;
			for (int i = 0; i < carsLeft.size(); i++) {
				if (currSize + carsLeft.get(i) <= boatLength) {
					currSize += carsLeft.get(i);
				} else {
					currSize = carsLeft.get(i);
					leftGroups++;
				}
			}
			if (currSize != 0) {
				leftGroups++;
			}
			currSize = 0;
			for (int i = 0; i < carsRight.size(); i++) {
				if (currSize + carsRight.get(i) <= boatLength) {
					currSize += carsRight.get(i);
				} else {
					currSize = carsRight.get(i);
					rightGroups++;
				}
			}
			if (currSize != 0) {
				rightGroups++;
			}
			if (leftGroups > rightGroups) {
				io.println(leftGroups*2-1);
			} else {
				io.println(rightGroups*2);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new FerryLoading4().go();
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
