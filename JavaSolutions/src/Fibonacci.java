import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Fibonacci {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int currCase = 1;
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			int N = Integer.parseInt(next);
			String pat = io.next();
			int find = -1;
			long result = 0;
			if(pat.contains("00")||pat.contains("111"))
				find = -1;
			else{
				String a = "0";
				String b = "1";
				if(pat.equals("0"))
					find = 0;
				else if(pat.equals("1"))
					find = 1;
				else{
					StringBuilder sb = new StringBuilder(b);
					find = 1;
					while(sb.indexOf(pat)==-1)
					{
						if(sb.length()>200000)
						{
							find = -1;
							break;
						}
						find++;
						sb.append(a);
						a = b;
						b = sb.toString();
					}
					if (find == -1) {
						io.printf("Case %d: %s%n", currCase++, 0);
						continue;
					}
				}
				StringBuilder sb = new StringBuilder(b);
//				io.println(sb);
				sb.append(a);
				a = b;
				b = sb.toString();
				String fib1 = b;
				sb.append(a);
				a = b;
				b = sb.toString();
				String fib2 = b;
				long one = 0;
				int index = fib1.indexOf(pat);
				while (index >= 0) {
					index = fib1.indexOf(pat, index+1);
					one++;
				}
				long two = 0;
				index = fib2.indexOf(pat);
				while (index >= 0) {
					index = fib2.indexOf(pat, index+1);
					two++;
				}
//				out.println(one + " " + two);
				long nextFib = 0;
				if (two == 4) {
					for (int i = 2; i < N-find; i++) {
						nextFib = one+two+1;
						one = two;
						two = nextFib;
					}
				} else if (one == 2) {
					for (int i = 2; i < N-find; i++) {
						nextFib = one+two+(i%2==0 ? 1 : 0);
						one = two;
						two = nextFib;
					}
				} else if (two == 3) {
					for (int i = 2; i < N-find; i++) {
						nextFib = one+two+(i%2==0 ? 0 : 1);
						one = two;
						two = nextFib;
					}
				} else {
					for (int i = 2; i < N-find; i++) {
						nextFib = one+two;
						one = two;
						two = nextFib;
					}
				}
				if (find == 0) {
					result = one;
				} else {
					result = nextFib;
				}
			}
//			io.println(pat+" found at "+find);
			if (find == -1) {
				io.printf("Case %d: %s%n", currCase++, 0);
			} else if (pat.equals("0") || pat.equals("1") || pat.equals("10")) {
				io.printf("Case %d: %s%n", currCase++, result);
			} else {
				io.printf("Case %d: %s%n", currCase++, result);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new Fibonacci().go();
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
