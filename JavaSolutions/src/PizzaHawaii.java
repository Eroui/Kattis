import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PizzaHawaii {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numPizzas = io.nextInt();
			TreeMap<String, TreeSet<String>> map = new TreeMap<>();
			String[][][] pizzas = new String[numPizzas][2][];
			for (int i = 0; i < numPizzas; i++) {
				io.next();
				pizzas[i][0] = new String[io.nextInt()];
				for (int e = 0; e < pizzas[i][0].length; e++) {
					pizzas[i][0][e] = io.next();
				}
				pizzas[i][1] = new String[io.nextInt()];
				HashSet<String> toppings = new HashSet<>();
				for (int e = 0; e < pizzas[i][1].length; e++) {
					pizzas[i][1][e] = io.next();
					toppings.add(pizzas[i][1][e]);
				}
				for (int e = 0; e < pizzas[i][0].length; e++) {
					if (map.get(pizzas[i][0][e]) == null) {
						map.put(pizzas[i][0][e], new TreeSet<String>(toppings));
					} else {
						map.get(pizzas[i][0][e]).addAll(toppings);
					}
				}
			}
			
			for (String fTopping : map.keySet()) {
				for (int i = 0; i < numPizzas; i++) {
					boolean foundTopping = false;
					for (int e = 0; e < pizzas[i][0].length; e++) {
						if (pizzas[i][0][e].equals(fTopping)) {
							foundTopping = true;
							break;
						}
					}
					if (!foundTopping) {
						for (String top : pizzas[i][1]) {
							map.get(fTopping).remove(top);
						}
					} else {
						HashSet<String> toppings = new HashSet<>();
						for (String top : pizzas[i][1]) {
							toppings.add(top);
						}
						map.get(fTopping).retainAll(toppings);
					}
				}
			}
			
			for (String fTopping : map.keySet()) {
				for (String top : map.get(fTopping)) {
					io.printf("(%s, %s)%n", fTopping, top);
				}
			}
			
			if (zzz < zz-1) {
				io.println();
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new PizzaHawaii().go();
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
