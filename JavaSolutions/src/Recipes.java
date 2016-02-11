import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Recipes {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numIngredients = io.nextInt();
			double scaleFactor = io.nextDouble();
			scaleFactor = io.nextDouble()/scaleFactor;
			Ingredient[] ingredients = new Ingredient[numIngredients];
			int mainIndex = 0;
			for (int i = 0; i < numIngredients; i++) {
				ingredients[i] = new Ingredient(io.next(), io.nextDouble(), io.nextDouble());
				if (ingredients[i].percent == 100) {
					mainIndex = i;
				}
			}
			ingredients[mainIndex].amount *= scaleFactor;
			for (int i = 0; i < numIngredients; i++) {
				if (mainIndex != i) {
					ingredients[i].amount = ingredients[mainIndex].amount*ingredients[i].percent/100;
				}
			}
			io.printf("Recipe # %d%n", zzz+1);
			io.println(Arrays.toString(ingredients).replaceAll("[\\[\\],]", "").replaceAll(" ", "\n").replaceAll("-", " "));
			io.println("----------------------------------------");
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Recipes().go();
	}
	
	private class Ingredient {
		
		String name;
		double amount, percent;
		
		public Ingredient(String n, double a, double p) {
			name = n;
			amount = a;
			percent = p;
		}
		
		public String toString() {
			return String.format("%s-%.1f", name, amount);
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
