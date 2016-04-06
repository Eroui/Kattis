import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class BasicInterpreter {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		TreeMap<Integer, String[]> map = new TreeMap<>();
		HashMap<String, Integer> vars = new HashMap<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			vars.put(c+"", 0);
		}
		while (true) {
			String next = io.nextLine();
			if (next == null || next.equals("end")) {
				break;
			}
			String[] line = next.split(" ");
			int label = Integer.parseInt(line[0]);
			String command = line[1];
			switch (command) {
			case "LET":
				map.put(label, next.substring(line[0].length()+1).split(" "));
				break;
			case "IF":
				map.put(label, next.substring(line[0].length()+1).split(" "));
				break;
			case "PRINT":
				map.put(label, new String[]{"PRINT", next.substring(line[0].length()+7)});
				break;
			case "PRINTLN":
				map.put(label, new String[]{"PRINTLN", next.substring(line[0].length()+9)});
				break;
			}
		}
		int loop = Integer.MIN_VALUE;
		boolean didBranch = true;
		while (didBranch) {
			didBranch = false;
			OUTER:
			for (int label : map.keySet()) {
				if (label < loop) {
					continue;
				}
				String[] args = map.get(label);
				String command = args[0];
				switch (command) {
				case "LET":
					if (args.length == 4) {
						if (vars.get(args[3]) == null) {
							vars.put(args[1], Integer.parseInt(args[3]));
						} else {
							vars.put(args[1], vars.get(args[3]));
						}
					} else {
						char op = args[4].charAt(0);
						int one, two;
						if (vars.get(args[3]) == null) {
							one = Integer.parseInt(args[3]);
						} else {
							one = vars.get(args[3]);
						}
						if (vars.get(args[5]) == null) {
							two = Integer.parseInt(args[5]);
						} else {
							two = vars.get(args[5]);
						}
						switch (op) {
						case '+':
							vars.put(args[1], one+two);
							break;
						case '-':
							vars.put(args[1], one-two);
							break;
						case '*':
							vars.put(args[1], one*two);
							break;
						case '/':
							vars.put(args[1], one/two);
							break;
						}
					}
					break;
				case "IF":
					String op = args[2];
					int v1, v2;
					if (vars.get(args[1]) == null) {
						v1 = Integer.parseInt(args[1]);
					} else {
						v1 = vars.get(args[1]);
					}
					if (vars.get(args[3]) == null) {
						v2 = Integer.parseInt(args[3]);
					} else {
						v2 = vars.get(args[3]);
					}
					int target = Integer.parseInt(args[6]);
					didBranch = false;
					switch (op) {
					case "=":
						didBranch = v1 == v2;
						break;
					case ">":
						didBranch = v1 > v2;
						break;
					case "<":
						didBranch = v1 < v2;
						break;
					case "<>":
						didBranch = v1 != v2;
						break;
					case "<=":
						didBranch = v1 <= v2;
						break;
					case ">=":
						didBranch = v1 >= v2;
						break;
					}
					if (didBranch) {
						loop = target;
						break OUTER;
					}
					break;
				case "PRINT":
					if (args[1].startsWith("\"")) {
						io.print(args[1].substring(1, args[1].length()-1));
					} else if (vars.get(args[1]) == null) {
						io.print(args[1]);
					} else {
						io.print(vars.get(args[1]));
					}
					break;
				case "PRINTLN":
					if (args[1].startsWith("\"")) {
						io.println(args[1].substring(1, args[1].length()-1));
					} else if (vars.get(args[1]) == null) {
						io.println(args[1]);
					} else {
						io.println(vars.get(args[1]));
					}
					break;
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new BasicInterpreter().go();
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
