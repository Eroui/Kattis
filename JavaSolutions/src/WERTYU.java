import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class WERTYU {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		HashMap<Character, Character> change = new HashMap<>();
		change.put('1', '`');
		change.put('2', '1');
		change.put('3', '2');
		change.put('4', '3');
		change.put('5', '4');
		change.put('6', '5');
		change.put('7', '6');
		change.put('8', '7');
		change.put('9', '8');
		change.put('0', '9');
		change.put('-', '0');
		change.put('=', '-');
		change.put('W', 'Q');
		change.put('E', 'W');
		change.put('R', 'E');
		change.put('T', 'R');
		change.put('Y', 'T');
		change.put('U', 'Y');
		change.put('I', 'U');
		change.put('O', 'I');
		change.put('P', 'O');
		change.put('[', 'P');
		change.put(']', '[');
		change.put('\\',']');
		change.put('S', 'A');
		change.put('D', 'S');
		change.put('F', 'D');
		change.put('G', 'F');
		change.put('H', 'G');
		change.put('J', 'H');
		change.put('K', 'J');
		change.put('L', 'K');
		change.put(';', 'L');
		change.put('\'', ';');
		change.put('X', 'Z');
		change.put('C', 'X');
		change.put('V', 'C');
		change.put('B', 'V');
		change.put('N', 'B');
		change.put('M', 'N');
		change.put(',', 'M');
		change.put('.', ',');
		change.put('/', '.');
		while (true) {
			String line = io.nextLine();
			if (line == null || line.equals("end")) {
				break;
			}
			for (char c : line.toCharArray()) {
				if (c == ' ') {
					io.print(" ");
				} else {
					io.print(change.get(c));
				}
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new WERTYU().go();
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
