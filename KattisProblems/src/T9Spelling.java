import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class T9Spelling {
	
	public void go() {
		HashMap<Character, String> map = new HashMap<Character, String>();
		map.put('a', "2");
		map.put('b', "22");
		map.put('c', "222");
		map.put('d', "3");
		map.put('e', "33");
		map.put('f', "333");
		map.put('g', "4");
		map.put('h', "44");
		map.put('i', "444");
		map.put('j', "5");
		map.put('k', "55");
		map.put('l', "555");
		map.put('m', "6");
		map.put('n', "66");
		map.put('o', "666");
		map.put('p', "7");
		map.put('q', "77");
		map.put('r', "777");
		map.put('s', "7777");
		map.put('t', "8");
		map.put('u', "88");
		map.put('v', "888");
		map.put('w', "9");
		map.put('x', "99");
		map.put('y', "999");
		map.put('z', "9999");
		map.put(' ', "0");
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 1; zzz <= zz; zzz++) {
			StringBuilder sb = new StringBuilder();
			String line = in.nextLine();
			for (char c : line.toCharArray()) {
				String next = map.get(c);
				if (sb.length() != 0 && sb.charAt(sb.length()-1) == next.charAt(0)) {
					sb.append(" ");
				}
				sb.append(next);
			}
			out.printf("Case #%d: %s%n", zzz, sb.toString());
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new T9Spelling().go();
	}
}
