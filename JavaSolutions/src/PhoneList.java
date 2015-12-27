import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class PhoneList {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		int zz = io.nextInt();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numNumbers = io.nextInt();
			boolean works = true;
			Tree tree = new Tree(-1);
			for (int i = 0; i < numNumbers; i++) {
				tree.add(io.next());
			}
			if (tree.numLeaves() == numNumbers) {
				io.println("YES");
			} else {
				io.println("NO");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new PhoneList().go();
	}
	
	private class Tree {
		int num;
		Tree[] nexts = new Tree[10];
		
		public Tree(int n) {
			num = n;
		}
		
		public void add(String s) {
			add(s, 0);
		}
		
		private void add(String s, int index) {
			if (index != s.length()) {
				int val = s.charAt(index)-'0';
				if (nexts[val] == null) {
					nexts[val] = new Tree(val);
				}
				nexts[val].add(s, index+1);
			}
		}
		
		public int numLeaves() {
			int sum = 0;
			boolean isLeaf = true;
			for (int i = 0; i < 10; i++) {
				if (nexts[i] != null) {
					sum += nexts[i].numLeaves();
					isLeaf = false;
				}
			}
			return isLeaf ? 1 : sum;
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
