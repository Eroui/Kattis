import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Measurement {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		HashMap<String, String> names = new HashMap<>();
		names.put("thou", "th");
		names.put("inch", "in");
		names.put("foot", "ft");
		names.put("yard", "yd");
		names.put("chain", "ch");
		names.put("furlong", "fur");
		names.put("mile", "mi");
		names.put("league", "lea");
		HashMap<String, Integer> convert = new HashMap<>();
		convert.put("in", 1000);
		convert.put("ft", 12);
		convert.put("yd", 3);
		convert.put("ch", 22);
		convert.put("fur", 10);
		convert.put("mi", 8);
		convert.put("lea", 3);
		String[] units = {"th","in","ft","yd","ch","fur","mi","lea"};
		double val = io.nextDouble();
		String from = io.next();
		if (names.containsKey(from)) {
			from = names.get(from);
		}
		io.next();
		String into = io.next();
		if (names.containsKey(into)) {
			into = names.get(into);
		}
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < units.length; i++) {
			if (units[i].equals(from)) {
				startIndex = i;
			} else if (units[i].equals(into)) {
				endIndex = i;
			}
		}
		if (startIndex > endIndex) {
			while (startIndex != endIndex) {
				val *= convert.get(units[startIndex]);
				startIndex--;
			}
		} else {
			while (startIndex != endIndex) {
				val /= convert.get(units[startIndex+1]);
				startIndex++;
			}
		}
		io.println(val);
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Measurement().go();
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
