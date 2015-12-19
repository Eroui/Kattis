import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Incognito {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numItems = in.nextInt();
			HashMap<String, Integer> map = new HashMap<>();
			for (int i = 0; i < numItems; i++) {
				in.next();
				String next = in.next();
				if (map.get(next) == null) {
					map.put(next, 0);
				}
				map.put(next, map.get(next)+1);
			}
//			out.println(map);
			int res = 0;
			for (String s : map.keySet()) {
				res += res*map.get(s) + map.get(s);
			}
			out.println(res);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Incognito().go();
	}
}
