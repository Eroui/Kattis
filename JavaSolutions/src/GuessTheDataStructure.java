import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class GuessTheDataStructure {
	
	public void go() {
		Kattio io = new Kattio(System.in);
		while (io.hasNext()) {
			int numOps = io.nextInt();
			if (numOps == 0) {
				break;
			}
			Stack<Integer> stack = new Stack<>();
			LinkedList<Integer> queue = new LinkedList<>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(1000, new Comparator<Integer>() {
				public int compare(Integer one, Integer two) {
					return two - one;
				}
			});
			boolean isStack = true, isQueue = true, isPQ = true;
			for (int i = 0; i < numOps; i++) {
				int op = io.nextInt();
				int num = io.nextInt();
				if (op == 1) {
					stack.push(num);
					queue.add(num);
					pq.add(num);
				} else {
					if (stack.isEmpty()) {
						isStack = false;
						isQueue = false;
						isPQ = false;
					} else {
						isStack = isStack && stack.pop() == num;
						isQueue = isQueue && queue.removeFirst() == num;
						isPQ = isPQ && pq.remove() == num;
					}
				}
			}
			if (isStack && isQueue && isPQ || isStack && isQueue || isStack && isPQ || isQueue && isPQ) {
				io.println("not sure");
			} else if (isStack) {
				io.println("stack");
			} else if (isQueue) {
				io.println("queue");
			} else if (isPQ) {
				io.println("priority queue");
			} else {
				io.println("impossible");
			}
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new GuessTheDataStructure().go();
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
