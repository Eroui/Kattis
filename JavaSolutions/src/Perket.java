import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Perket {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numIngredients = io.nextInt();
		int[][] ingredients = new int[numIngredients][2];
		for (int i = 0; i < numIngredients; i++) {
			ingredients[i][0] = io.nextInt();
			ingredients[i][1] = io.nextInt();
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < numIngredients; i++) {
			min = Math.min(getMin(ingredients, i+1, ingredients[i][0], ingredients[i][1]), min);
		}
		io.println(min);
		
		io.flush();
		io.close();
	}
	
	public int getMin(int[][] ingredients, int i, int sour, int bitter) {
		int min = Math.abs(sour-bitter);
		for (; i < ingredients.length; i++) {
			min = Math.min(Math.min(getMin(ingredients, i+1, sour*ingredients[i][0], bitter+ingredients[i][1]), getMin(ingredients, i+1, sour, bitter)), min);
		}
		return min;
	}
	
	public static void main(String[] args) {
		new Perket().go();
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
