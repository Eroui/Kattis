import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Help2 {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String[] first = in.nextLine().split(" ");
			String[] second = in.nextLine().split(" ");
			if (first.length != second.length) {
				out.println("-");
				continue;
			}
			HashMap<String, String> mapOne = new HashMap<>();
			HashMap<String, String> mapTwo = new HashMap<>();
			boolean works = true;
			for (int i = 0; i < first.length; i++) {
				if (first[i].matches("<.*>")) {
					if (second[i].matches("<.*>")) {
						continue;
					}
					if (mapOne.get(first[i]) == null) {
						mapOne.put(first[i], second[i]);
					} else if (!mapOne.get(first[i]).equals(second[i])){
						works = false;
						break;
					}
				} else if (second[i].matches("<.*>")) {
					if (mapTwo.get(second[i]) == null) {
						mapTwo.put(second[i], first[i]);
					} else if (!mapTwo.get(second[i]).equals(first[i])){
						works = false;
						break;
					}
				}
			}
			if (works) {
				char rep = 'a';
				for (int i = 0; i < first.length; i++) {
					if (first[i].matches("<.*>")) {
						if (mapTwo.get(second[i]) != null) {
							mapOne.put(first[i], mapTwo.get(second[i]));
						} else if (mapOne.get(first[i]) == null) {
							mapOne.put(first[i], ""+rep);
	//						rep++;
						}
					}
				}
				rep = 'a';
				for (int i = 0; i < first.length; i++) {
					if (second[i].matches("<.*>")) {
						if (mapOne.get(first[i]) != null) {
							mapTwo.put(second[i], mapOne.get(first[i]));
						} else if (mapTwo.get(second[i]) == null) {
							mapTwo.put(second[i], ""+rep);
	//						rep++;
						}
					}
				}
				String outputOne = Arrays.toString(first).replaceAll("[\\[\\],]", "");
				for (String s : mapOne.keySet()) {
					outputOne = outputOne.replaceAll(s, mapOne.get(s));
				}
				String outputTwo = Arrays.toString(second).replaceAll("[\\[\\],]", "");
				for (String s : mapTwo.keySet()) {
					outputTwo = outputTwo.replaceAll(s, mapTwo.get(s));
				}
				if (outputOne.equals(outputTwo)) {
					out.println(outputOne);
				} else {
					out.println("-");
				}
			} else {
				out.println("-");
			}
		}
	}
	
	public static void main(String[] args) {
		new Help2().go();
	}
}
