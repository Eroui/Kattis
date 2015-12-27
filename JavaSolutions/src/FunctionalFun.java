import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class FunctionalFun {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			HashMap<String, String> map = new HashMap<>();
			HashMap<String, Integer> codomain = new HashMap<>();
			HashMap<String, Boolean> domain = new HashMap<>();
			in.next();
			Scanner line = new Scanner(in.nextLine());
			while (line.hasNext()) {
				domain.put(line.next(), false);
			}
			in.next();
			line = new Scanner(in.nextLine());
			while (line.hasNext()) {
				codomain.put(line.next(), 0);
			}
			int numMappings = in.nextInt();
			boolean isFunction = true;
			for (int z = 0; z < numMappings; z++) {
				String start = in.next();
				in.next();
				String end = in.next();
				if (domain.get(start)) {
					isFunction = false;
				} else {
					domain.put(start, true);
				}
				codomain.put(end, codomain.get(end)+1);
			}
			if (!isFunction) {
				out.println("not a function");
				continue;
			}
			boolean isSurjective = true;
			boolean isInjective = true;
			for (String s : codomain.keySet()) {
				if (codomain.get(s) == 0) {
					isSurjective = false;
				}
				if (codomain.get(s) > 1) {
					isInjective = false;
				}
			}
			if (isSurjective) {
				if (isInjective) {
					out.println("bijective");
				} else {
					out.println("surjective");
				}
			} else {
				if (isInjective) {
					out.println("injective");
				} else {
					out.println("neither injective nor surjective");
				}
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new FunctionalFun().go();
	}
}
