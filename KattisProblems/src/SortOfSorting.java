import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class SortOfSorting {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		boolean isFirst = true;
		while (in.hasNextInt()) {
			int numNames = in.nextInt();
			if (numNames == 0) {
				break;
			}
			if (!isFirst) {
				out.println();
			}
			SemiString[] names = new SemiString[numNames];
			for (int i = 0; i < numNames; i++) {
				names[i] = new SemiString(in.next());
			}
			Arrays.sort(names);
			out.println(Arrays.toString(names).replaceAll("[\\[\\],]", "").replaceAll(" ", "\n"));
			isFirst = false;
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new SortOfSorting().go();
	}
	
	private class SemiString implements Comparable<SemiString> {
		String val;
		
		public SemiString(String s) {
			val = s;
		}
		
		public int compareTo(SemiString other) {
			if (val.length() == 0) {
				if (other.val.length() == 0) {
					return 0;
				} else {
					return -1;
				}
			} else if (val.length() == 1) {
				if (other.val.length() == 0) {
					return 1;
				} else {
					return val.charAt(0) - other.val.charAt(0);
				}
			} else {
				if (other.val.length() == 0) {
					return 1;
				} else if (other.val.length() == 1) {
					if (val.charAt(0) != other.val.charAt(0)) {
						return val.charAt(0) - other.val.charAt(0);
					} else {
						return 1;
					}
				} else if (val.charAt(0) == other.val.charAt(0)) {
					return val.charAt(1) - other.val.charAt(1);
				} else {
					return val.charAt(0) - other.val.charAt(0);
				}
			}
		}
		
		public String toString() {
			return val;
		}
	}
}
