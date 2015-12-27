import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class SelectGroup {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		HashMap<String, TreeSet<String>> groups = new HashMap<>();
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			if (line[0].equals("group")) {
				TreeSet<String> g = new TreeSet<String>();
				for (int i = 3; i < line.length; i++) {
					g.add(line[i]);
				}
				groups.put(line[1], g);
			} else {
				ArrayList<String> ar = new ArrayList<String>();
				for (String s : line) {
					ar.add(s);
				}
				out.println(evaluate(ar, 0, groups).toString().replaceAll("[\\[\\],]", ""));
			}
		}
		
		in.close();
	}
	
	public TreeSet<String> evaluate(ArrayList<String> line, int index, HashMap<String, TreeSet<String>> groups) {
		if (line.get(index).equals("union")) {
			TreeSet<String> res = union(evaluate(line, index+1, groups), evaluate(line, index+2, groups));
			line.remove(index);
			line.remove(index);
			return res;
		} else if (line.get(index).equals("intersection")) {
			TreeSet<String> res =  intersection(evaluate(line, index+1, groups), evaluate(line, index+2, groups));
			line.remove(index);
			line.remove(index);
			return res;
		} else if (line.get(index).equals("difference")) {
			TreeSet<String> res =  difference(evaluate(line, index+1, groups), evaluate(line, index+2, groups));
			line.remove(index);
			line.remove(index);
			return res;
		} else {
			return new TreeSet<String>(groups.get(line.get(index)));
		}
	}
	
	public TreeSet<String> union(Set<String> one, Set<String> two) {
		TreeSet<String> res = new TreeSet<String>(one);
		res.addAll(two);
		return res;
	}
	
	public TreeSet<String> intersection(Set<String> one, Set<String> two) {
		TreeSet<String> res = new TreeSet<String>(one);
		res.retainAll(two);
		return res;
	}
	
	public TreeSet<String> difference(Set<String> one, Set<String> two) {
		TreeSet<String> res = new TreeSet<String>(one);
		res.removeAll(two);
		return res;
	}
	
	public static void main(String[] args) {
		new SelectGroup().go();
	}
}
