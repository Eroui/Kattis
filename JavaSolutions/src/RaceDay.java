import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class RaceDay {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		while (true) {
			int numRacers = io.nextInt();
			if (numRacers == 0) {
				break;
			}
			HashMap<Integer, Racer> racers = new HashMap<>();
			Racer[] ar = new Racer[numRacers];
			for (int i = 0; i < numRacers; i++) {
				String n1 = io.next();
				String n2 = io.next();
				int id = io.nextInt();
				racers.put(id, new Racer(n1,n2,id));
				ar[i] = racers.get(id);
			}
			for (int i = 0; i < numRacers*3; i++) {
				int id = io.nextInt();
				String type = io.next();
				String time = io.next();
				switch (type) {
				case "S1":
					racers.get(id).s1 = time;
					break;
				case "S2":
					racers.get(id).s2 = time;
					break;
				case "F":
					racers.get(id).sf = time;
					break;
				}
			}
			Arrays.sort(ar, new Comparator<Racer>() {
				public int compare(Racer one, Racer two) {
					return one.s1.compareTo(two.s1);
				}
			});
			ar[0].rank1 = 1;
			for (int i = 1; i < numRacers; i++) {
				if (ar[i-1].s1.equals(ar[i].s1)) {
					ar[i].rank1 = ar[i-1].rank1;
				} else {
					ar[i].rank1 = i+1;
				}
			}
			Arrays.sort(ar, new Comparator<Racer>() {
				public int compare(Racer one, Racer two) {
					return one.s2.compareTo(two.s2);
				}
			});
			ar[0].rank2 = 1;
			for (int i = 1; i < numRacers; i++) {
				if (ar[i-1].s2.equals(ar[i].s2)) {
					ar[i].rank2 = ar[i-1].rank2;
				} else {
					ar[i].rank2 = i+1;
				}
			}
			Arrays.sort(ar, new Comparator<Racer>() {
				public int compare(Racer one, Racer two) {
					return one.sf.compareTo(two.sf);
				}
			});
			ar[0].rankf = 1;
			for (int i = 1; i < numRacers; i++) {
				if (ar[i-1].sf.equals(ar[i].sf)) {
					ar[i].rankf = ar[i-1].rankf;
				} else {
					ar[i].rankf = i+1;
				}
			}
			Arrays.sort(ar);
			io.printf("%-20s%10s%10s%10s%10s%10s%10s%10s%n", "NAME", "BIB", "SPLIT1", "RANK",
					"SPLIT2", "RANK", "FINISH", "RANK");
			for (Racer r : ar) {
				io.println(r);
			}
			io.println();
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new RaceDay().go();
	}
	
	private class Racer implements Comparable<Racer> {
		
		String name1, name2, sid, s1, s2, sf;
		int rank1, rank2, rankf, id;
		
		public Racer(String n1, String n2, int i) {
			name1 = n1;
			name2 = n2;
			id = i;
			sid = String.format("%05d", id);
		}
		
		public int hashCode() {
			return id;
		}
		
		public int compareTo(Racer other) {
			if (name2.equals(other.name2)) {
				return name1.compareTo(other.name1);
			}
			return name2.compareTo(other.name2);
		}
		
		public String toString() {
			return String.format("%-20s%10s%10s%10s%10s%10s%10s%10s", String.format("%s, %s", name2, name1), sid, s1, rank1, s2, rank2, sf, rankf);
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
