import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Stacking {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int currCase = 1;
		while (true) {
			String next = io.next();
			if (next == null || next.equals("end")) {
				break;
			}
			int numTowers = Integer.parseInt(next);
			ArrayList<Tower> towers = new ArrayList<>();
			for (int i = 0; i < numTowers; i++) {
				Tower t = new Tower();
				towers.add(t);
				int height = io.nextInt();
				for (int e = 0; e < height; e++) {
					int n = io.nextInt();
					if (t.last == null || t.last.val != n) {
						t.add(n);
					}
				}
			}
			Collections.sort(towers);
			int numOps = 0;
			for (int i = 0; i < towers.size(); i++) {
				io.println(towers);
				Tower curr = towers.get(i);
				Element elem = curr.last.prev;
				int split = curr.size;
				while (elem != null) {
					split--;
					int min = elem.val;
					int max = elem.next.val;
					boolean overlap = false;
					for (int e = 0; e < towers.size(); e++) {
						if (i != e && overlap(min, max, towers.get(e).first.val, towers.get(e).last.val)) {
							overlap = true;
							break;
						}
					}
					if (overlap) {
						towers.set(i, curr.split(split));
						towers.add(curr);
						numOps++;
						Collections.sort(towers);
						i = -1;
						break;
					}
					elem = elem.prev;
				}
			}
			io.printf("Case %d: %d%n", currCase++, numOps+towers.size()-1);
		}
		
		io.flush();
		io.close();
	}
	
	public boolean overlap(int a1, int b1, int a2, int b2) {
		if (a1 < a2) {
			return b1 > a2;
		} else if (a1 > a2) {
			return b2 > a1;
		} else {
			return b1 != a1 && b2 != a2;
		}
	}
	
	public static void main(String[] args) {
		new Stacking().go();
	}
	
	private class Tower implements Comparable<Tower> {
		
		Element first, last;
		int size;
		
		public void add(int n) {
			Element e = new Element(n);
			size++;
			if (first == null) {
				first = e;
				last = e;
			} else {
				e.prev = last;
				last.next = e;
				last = e;
			}
		}
		
		public Tower split(int i) {
			Element curr = first;
			int newSize = size-i;
			size = i;
			while (i > 0) {
				curr = curr.next;
				i--;
			}
			Tower t = new Tower();
			t.size = newSize;
			t.first = curr;
			t.last = last;
			last = curr.prev;
			curr.prev = null;
			last.next = null;
			return t;
		}
		
		public int compareTo(Tower other) {
			if (last.val == other.last.val) {
				Element one = last.prev;
				Element two = other.last.prev;
				while (true) {
					if (one == null) {
						return 1;
					} else if (two == null) {
						return -1;
					} else if (one.val != two.val) {
						return one.val - two.val;
					}
					one = one.prev;
					two = two.prev;
				}
			}
			return other.last.val - last.val;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			sb.append(first);
			Element curr = first.next;
			while (curr != null) {
				sb.append(",").append(curr);
				curr = curr.next;
			}
			sb.append(")");
			return sb.toString();
		}
	}
	
	private class Element {
		
		int val;
		Element next, prev;
		
		public Element(int v) {
			val = v;
		}
		
		public String toString() {
			return ""+val;
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
