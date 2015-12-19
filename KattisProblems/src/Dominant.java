import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Dominant {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		ArrayList<Multiset> dominants = new ArrayList<Multiset>();
		ArrayList<Multiset> bad = new ArrayList<Multiset>();
		while (in.hasNextLine()) {
			String next = in.nextLine().trim();
//			if (next.equals("")) {
//				break;
//			}
			Multiset curr = new Multiset(next);
			boolean add = true;
			for (Multiset other : bad) {
				if (other.isSupersetOf(curr)) {
					add = false;
					break;
				}
			}
			ListIterator<Multiset> it = dominants.listIterator();
			while (it.hasNext()) {
				Multiset nextSet = it.next();
				if (curr.isSupersetOf(nextSet)) {
					bad.add(nextSet);
					it.remove();
				}
			}
			for (Multiset other : dominants) {
				if (other.isSupersetOf(curr)) {
					add = false;
					break;
				}
			}
			if (add) {
				dominants.add(curr);
			} else {
				bad.add(curr);
			}
		}
		Collections.sort(dominants);
		out.println(dominants.toString().replaceAll("[\\[\\],]", "").replaceAll(" ", "\n"));
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Dominant().go();
	}
	
	private class Multiset implements Comparable<Multiset> {
		String original;
		char[] sorted;
		
		
		public Multiset(String s) {
			original = s;
			sorted = s.toCharArray();
			Arrays.sort(sorted);
		}
		
		public boolean isSupersetOf(Multiset other) {
			if (sorted.length <= other.sorted.length) {
				return false;
			}
			int i = 0;
			for (char c : other.sorted) {
				boolean found = false;
				for (; i < sorted.length; i++) {
					if (sorted[i] > c) {
						return false;
					} else if (sorted[i] == c) {
						i++;
						found = true;
						break;
					}
				}
				if (!found) {
					return false;
				}
			}
			return true;
		}
		
		public int compareTo(Multiset other) {
			return original.compareTo(other.original);
		}
		
		public String toString() {
			return original;
		}
	}
}
