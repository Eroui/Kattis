import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Subway {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			Tree one = new Tree();
			Tree two = new Tree();
			String line = in.nextLine();
			int depth = 0;
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '0') {
					one.add(depth);
					depth++;
				} else {
					depth--;
				}
			}
			line = in.nextLine();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '0') {
					two.add(depth);
					depth++;
				} else {
					depth--;
				}
			}
			if (one.equals(two)) {
				out.println("same");
			} else {
				out.println("different");
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Subway().go();
	}
	
	private class Tree implements Comparable<Tree> {
		int depth;
		ArrayList<Tree> nexts;
		
		public Tree() {
			depth = 0;
			nexts = new ArrayList<Tree>();
		}
		
		public Tree(int d) {
			depth = d;
			nexts = new ArrayList<Tree>();
		}
		
		public void add(int i) {
			if (i == depth) {
				nexts.add(new Tree(depth+1));
			} else {
				nexts.get(nexts.size()-1).add(i);
			}
		}
		
		public boolean equals(Object o) {
			if (o instanceof Tree) {
				Tree other = (Tree)o;
				sort();
				other.sort();
				return nexts.equals(other.nexts);
			}
			return false;
		}
		
		private void sort() {
			for (Tree t : nexts) {
				t.sort();
			}
			Collections.sort(nexts);
		}
		
		public int compareTo(Tree other) {
			if (other.nexts.size() == nexts.size()) {
				for (int i = 0; i < nexts.size(); i++) {
					if (other.nexts.get(i).compareTo(nexts.get(i)) != 0) {
						return nexts.get(i).compareTo(other.nexts.get(i));
					}
				}
				return 0;
			}
			return nexts.size() - other.nexts.size();
		}
	}
}
