import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ChemistsVows {
	
	Kattio io;
	
	public void go() {
		String elementsList = "H     He Li  Be    B   C   N   O   F   Ne  Na  Mg    Al  Si  P   S   Cl  Ar K   Ca  Sc  Ti  V   Cr  Mn  Fe  Co  Ni  Cu  Zn  Ga  Ge  As  Se  Br  Kr Rb  Sr  Y   Zr  Nb  Mo  Tc  Ru  Rh  Pd  Ag  Cd  In  Sn  Sb  Te  I   Xe Cs  Ba   Hf  Ta  W   Re  Os  Ir  Pt  Au  Hg  Tl  Pb  Bi  Po  At  Rn Fr  Ra   Rf  Db  Sg  Bh  Hs  Mt  Ds  Rg  Cn      Fl      Lv La  Ce  Pr  Nd  Pm  Sm  Eu  Gd  Tb  Dy  Ho  Er  Tm  Yb  Lu Ac  Th  Pa  U   Np  Pu  Am  Cm  Bk  Cf  Es  Fm  Md  No  Lr";
		HashSet<String> elements = new HashSet<>();
		for (String s : elementsList.split("\\s+")) {
			elements.add(s.toLowerCase());
		}
		io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			String word = io.next();
			boolean[] jumpOne = new boolean[word.length()];
			boolean[] jumpTwo = new boolean[word.length()];
			for (int i = word.length()-1; i >= 0; i--) {
				if (elements.contains(word.substring(i, i+1))
						&& (i < word.length()-1 && (jumpOne[i+1] || jumpTwo[i+1]) || i == word.length()-1)) {
					jumpOne[i] = true;
				}
				if (i < word.length()-1 && elements.contains(word.substring(i, i+2))
						&& (i < word.length()-2 && (jumpOne[i+2] || jumpTwo[i+2]) || i == word.length()-2)) {
					jumpTwo[i] = true;
				}
			}
			if (jumpOne[0] || jumpTwo[0]) {
				io.println("YES");
			} else {
				io.println("NO");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public boolean canMakeWord(HashSet<String> elements, String word, int i) {
		if (i == word.length()) {
			return true;
		}
		boolean works = false;
		if (elements.contains(word.substring(i,i+1))) {
			works |= canMakeWord(elements, word, i+1);
		}
		if (!works && i < word.length()-1 && elements.contains(word.substring(i, i+2))) {
			works |= canMakeWord(elements, word, i+2);
		}
		return works;
	}
	
	public static void main(String[] args) {
		new ChemistsVows().go();
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
