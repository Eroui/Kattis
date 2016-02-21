import java.io.*;
import java.util.*;
import java.util.regex.*;

import java.math.*;

import static java.lang.System.out;

public class RunningMoM {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		int numEdges = io.nextInt();
		HashMap<String, City> map = new HashMap<>();
		for (int i = 0; i < numEdges; i++) {
			String from = io.next();
			String to = io.next();
			if (map.get(from) == null) {
				map.put(from, new City(from));
			}
			if (map.get(to) == null) {
				map.put(to, new City(to));
			}
			map.get(from).add(map.get(to));
		}
		while (true) {
			String check = io.next();
			if (check == null || check.equals("endend")) {
				break;
			}
			City city = map.get(check);
			if (city.others.size() == 0) {
				io.printf("%s trapped%n", city.name);
			} else if (findCycle(city, new HashSet<City>(), new HashSet<City>())) {
				city.safe = true;
				io.printf("%s safe%n", city.name);
			} else {
				io.printf("%s trapped%n", city.name);
			}
		}
		
		io.flush();
		io.close();
	}
	
	public boolean findCycle(City curr, HashSet<City> prev, HashSet<City> visited) {
		visited.add(curr);
		prev.add(curr);
		if (curr.safe) {
			return true;
		}
		for (City other : curr.others) {
			if (prev.contains(other)) {
				return true;
			}
			if (!visited.contains(other)) {
				if (findCycle(other, prev, visited)) {
					other.safe = true;
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		new RunningMoM().go();
	}
	
	private class City {
		
		String name;
		HashSet<City> others = new HashSet<>();
		HashSet<City> back = new HashSet<>();
		boolean safe;
		
		public City(String n) {
			name = n;
		}
		
		public void add(City other) {
			others.add(other);
			other.back.add(this);
		}
		
		public int hashCode() {
			return name.hashCode();
		}
		
		public boolean equals(Object obj) {
			if (obj instanceof City) {
				City other = (City)obj;
				return name.equals(other.name);
			}
			return false;
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
