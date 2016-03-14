import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PrintingCosts {
	
	Kattio io;
	
	public void go() {
		HashMap<Character, Integer> costs = new HashMap<>();
		try {
			Scanner file = new Scanner(new File("printingcosts.txt"));	// has character costs
			while (file.hasNext()) {
				costs.put(file.next().charAt(0), file.nextInt());
			}
			costs.put(' ', 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		io = new Kattio(System.in);
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("end")) {
				break;
			}
			int total = 0;
			for (char c : line.toCharArray()) {
				total += costs.get(c);
			}
			io.println(total);
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new PrintingCosts().go();
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
