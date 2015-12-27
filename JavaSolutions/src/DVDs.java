import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class DVDs {
	//JAVA SLOWWWWWWWWWW
	public void go() {
//        Scanner in = new Scanner(System.in);
//        int zz = in.nextInt();
//        in.nextLine();
//        StringBuilder sb = new StringBuilder(zz*10);
//        for (int zzz = 0; zzz < zz; zzz++) {
//            int numCDs = in.nextInt();
//            int currCD = 1;
//            for (int i = 0; i < numCDs; i++) {
//                if (in.nextInt() == currCD) {
//                    currCD++;
//                }
//            }
//            sb.append(numCDs-currCD+1).append("\n");
//        }
//        out.print(sb);
//        
//        in.close();
        
		Kattio io = new Kattio(System.in);
        int zz = io.nextInt();
        for (int zzz = 0; zzz < zz; zzz++) {
            int numCDs = io.nextInt();
            int currCD = 1;
            for (int i = 0; i < numCDs; i++) {
                if (io.nextInt() == currCD) {
                    currCD++;
                }
            }
            io.println(numCDs-currCD+1);
        }
        
        io.flush();
        io.close();
    }
	
	public static void main(String[] args) {
		new DVDs().go();
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
