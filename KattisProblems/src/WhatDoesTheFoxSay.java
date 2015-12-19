import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class WhatDoesTheFoxSay {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			ArrayList<String> message = new ArrayList<String>(100);
			for (String s : in.nextLine().split(" ")) {
				message.add(s);
			}
			ArrayList<String> animals = new ArrayList<String>();
			String line = in.nextLine();
			while (!line.equals("what does the fox say?")) {
				animals.add(line.split(" goes ")[1]);
				line = in.nextLine();
			}
			for (String s : animals) {
				message.removeAll(animals);
			}
			out.println(message.toString().replaceAll("[\\[\\],]", ""));
		}
	}
	
	public static void main(String[] args) {
		new WhatDoesTheFoxSay().go();
	}
}
