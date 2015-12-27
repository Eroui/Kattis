import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class RockPaperScissors {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		boolean first = true;
		while (io.hasNext()) {
			int numPlayers = io.nextInt();
			if (numPlayers == 0) {
				break;
			}
			int numGames = io.nextInt() * numPlayers * (numPlayers-1) / 2;
			int[] won = new int[numPlayers];
			int[] lost = new int[numPlayers];
			for (int i = 0; i < numGames; i++) {
				int one = io.nextInt()-1;
				String op1 = io.next();
				int two = io.nextInt()-1;
				String op2 = io.next();
				switch (op1) {
				case "rock":
					if (op2.equals("rock")) {
					} else if (op2.equals("paper")) {
						lost[one] += 1;
						won[two] += 1;
					} else {
						won[one] += 1;
						lost[two] += 1;
					}
					break;
				case "paper":
					if (op2.equals("rock")) {
						won[one] += 1;
						lost[two] += 1;
					} else if (op2.equals("paper")) {
					} else {
						lost[one] += 1;
						won[two] += 1;
					}
					break;
				case "scissors":
					if (op2.equals("rock")) {
						lost[one] += 1;
						won[two] += 1;
					} else if (op2.equals("paper")) {
						won[one] += 1;
						lost[two] += 1;
					} else {
					}
					break;
				}
			}
			if (!first) {
				io.println();
			}
			for (int i = 0; i < numPlayers; i++) {
				if (won[i] + lost[i] == 0) {
					io.println("-");
				} else {
					io.printf("%.3f%n", (double)won[i]/(won[i]+lost[i]));
				}
			}
			first = false;
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new RockPaperScissors().go();
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
	    	peekToken();
	    	return line;
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
