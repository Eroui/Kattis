import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class ArmyStrengthEasy {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numGodzilla = io.nextInt();
			int numMecha = io.nextInt();
			int[] godzilla = new int[numGodzilla];
			int[] mecha = new int[numMecha];
			for (int i = 0; i < numGodzilla; i++) {
				godzilla[i] = io.nextInt();
			}
			for (int i = 0; i < numMecha; i++) {
				mecha[i] = io.nextInt();
			}
			Arrays.sort(godzilla);
			Arrays.sort(mecha);
			int g = 0;
			int m = 0;
			while (g < godzilla.length && m < mecha.length) {
				if (godzilla[g] < mecha[m]) {
					g++;
				} else {
					m++;
				}
			}
			if (g == godzilla.length) {
				io.println("MechaGodzilla");
			} else {
				io.println("Godzilla");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new ArmyStrengthEasy().go();
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
