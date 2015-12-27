import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class CookieSelection {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		String next = "";
		Cookie middle = null;
		int size = 0;
		while (true) {
			next = io.nextLine();
			if (next == null) {
				break;
			}
			if (next.equals("!")) {
				break;
			}
			if (next.charAt(0) == '#') {
				if (size == 0) {
					continue;
				}
				io.println(middle.val);
				if (size == 1) {
					middle = null;
				} else if (size == 2) {
					middle.prev.next = null;
					middle = middle.prev;
				} else {
					middle.prev.next = middle.next;
					middle.next.prev = middle.prev;
					if (size % 2 == 0) {
						middle = middle.prev;
					} else {
						middle = middle.next;
					}
				}
				size--;
			} else {
				Cookie toAdd = new Cookie(Integer.parseInt(next));
				size++;
				if (middle == null) {
					middle = toAdd;
					continue;
				}
				middle.add(toAdd);
				if (toAdd.val >= middle.val && size % 2 == 0) {
					middle = middle.next;
				} else if (toAdd.val < middle.val && size % 2 == 1) {
					middle = middle.prev;
				}
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new CookieSelection().go();
	}
	
	private class Cookie {
		Cookie next, prev;
		int val;
		
		public Cookie(int v) {
			val = v;
		}
		
		public void add(Cookie other) {
			if (other.val >= val) {
				addFor(other);
			} else {
				addBack(other);
			}
		}
		
		private void addFor(Cookie other) {
			Cookie curr = this;
			while (curr.next != null) {
				if (curr.next.val > other.val) {
					break;
				}
				curr = curr.next;
			}
			if (curr.next != null) {
				other.next = curr.next;
				other.prev = curr;
				curr.next.prev = other;
				curr.next = other;
			} else {
				curr.next = other;
				other.prev = curr;
			}
		}
		
		private void addBack(Cookie other) {
			Cookie curr = this;
			while (curr.prev != null) {
				if (curr.prev.val <= other.val) {
					break;
				}
				curr = curr.prev;
			}
			if (curr.prev != null) {
				other.next = curr;
				other.prev = curr.prev;
				curr.prev.next = other;
				curr.prev = other;
			} else {
				curr.prev = other;
				other.next = curr;
			}
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
