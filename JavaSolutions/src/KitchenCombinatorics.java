import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class KitchenCombinatorics {
	
	Kattio io;
	int[] ingredients;
	BigInteger[] dishes;
	boolean[][] ingUsed;
	boolean[][] incompatible;
	
	public void go() {
		io = new Kattio(System.in);
		int numIngredients = io.nextInt();
		int numStarters = io.nextInt();
		int numMains = io.nextInt();
		int numDesserts = io.nextInt();
		int numBadPairs = io.nextInt();
		ingredients = new int[numIngredients];
		for (int i = 0; i < numIngredients; i++) {
			ingredients[i] = io.nextInt();
		}
		dishes = new BigInteger[numStarters+numMains+numDesserts];
		ingUsed = new boolean[dishes.length][numIngredients];
		incompatible = new boolean[dishes.length][dishes.length];
		for (int i = 0; i < dishes.length; i++) {
			int ingNeed = io.nextInt();
			BigInteger possible = BigInteger.ONE;
			for (int e = 0; e < ingNeed; e++) {
				int next = io.nextInt()-1;
				ingUsed[i][next] = true;
				possible = possible.multiply(new BigInteger(""+ingredients[next]));
			}
			dishes[i] = possible;
		}
//		io.println(Arrays.toString(dishes));
		BigInteger allStarters = BigInteger.ZERO;
		BigInteger allMains = BigInteger.ZERO;
		BigInteger allDesserts = BigInteger.ZERO;
		for (int i = 0; i < numStarters; i++) {
			allStarters = allStarters.add(dishes[i]);
		}
		for (int i = 0; i < numMains; i++) {
			allMains = allMains.add(dishes[i+numStarters]);
		}
		for (int i = 0; i < numDesserts; i++) {
			allDesserts = allDesserts.add(dishes[i+numStarters+numMains]);
		}
		BigInteger bad = BigInteger.ZERO;
		for (int i = 0; i < numBadPairs; i++) {
			int d1 = io.nextInt()-1;
			int d2 = io.nextInt()-1;
			BigInteger pair = dishes[d1].multiply(dishes[d2]);
			BigInteger combo;
			if (d1 < numStarters) {
				if (d2 < numStarters+numMains) {
					combo = pair.multiply(allDesserts);
					for (int e = numStarters+numMains; e < dishes.length; e++) {
						combo = removeOverlap(combo, d1, d2, e);
					}
				} else {
					combo = pair.multiply(allMains);
					for (int e = numStarters; e < numStarters+numMains; e++) {
						combo = removeOverlap(combo, d1, d2, e);
					}
				}
			} else if (d1 < numStarters+numMains) {
				if (d2 < numStarters) {
					combo = pair.multiply(allDesserts);
					for (int e = numStarters+numMains; e < dishes.length; e++) {
						combo = removeOverlap(combo, d1, d2, e);
					}
				} else {
					combo = pair.multiply(allStarters);
					for (int e = 0; e < numStarters; e++) {
						combo = removeOverlap(combo, d1, d2, e);
					}
				}
			} else {
				if (d2 < numStarters) {
					combo = pair.multiply(allMains);
					for (int e = numStarters; e < numStarters+numMains; e++) {
						combo = removeOverlap(combo, d1, d2, e);
					}
				} else {
					combo = pair.multiply(allStarters);
					for (int e = 0; e < numStarters; e++) {
						combo = removeOverlap(combo, d1, d2, e);
					}
				}
			}
			bad = bad.add(combo);
		}
//		io.println(bad);
		BigInteger allCombos = BigInteger.ZERO;
		for (int i = 0; i < numStarters; i++) {
			for (int e = numStarters; e < numStarters+numMains; e++) {
				for (int w = numStarters+numMains; w < dishes.length; w++) {
					BigInteger combo = dishes[i].multiply(dishes[e]).multiply(dishes[w]);
//					io.println(combo + " " + allCombos);
					combo = removeOverlap(combo, i, e, w);
					allCombos = allCombos.add(combo);
				}
			}
		}
		allCombos = allCombos.subtract(bad);
		
		if (allCombos.compareTo(new BigInteger("1000000000000000000")) > 0) {
			io.println("too many");
		} else {
			io.println(allCombos);
		}
		
		io.flush();
		io.close();
	}
	
	public BigInteger removeOverlap(BigInteger combo, int d1, int d2, int d3) {
		for (int i = 0; i < ingUsed[0].length; i++) {
			if ((ingUsed[d1][i] && ingUsed[d2][i]) || (ingUsed[d1][i] && ingUsed[d3][i])
					|| (ingUsed[d2][i] && ingUsed[d3][i])) {
				combo = combo.divide(new BigInteger(""+ingredients[i]));
				if (ingUsed[d1][i] && ingUsed[d2][i] && ingUsed[d3][i]) {
					combo = combo.divide(new BigInteger(""+ingredients[i]));
				}
			}
		}
		return combo;
	}
	
	public static void main(String[] args) {
		new KitchenCombinatorics().go();
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
