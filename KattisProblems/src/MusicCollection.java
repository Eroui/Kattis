import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class MusicCollection {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numSongs = in.nextInt();
			in.nextLine();
			String[] songs = new String[numSongs];
			Tree trie = new Tree();
			for (int i = 0; i < numSongs; i++) {
				songs[i] = in.nextLine().toUpperCase();
				trie.add(songs[i]);
			}
			for (int i = 0; i < numSongs; i++) {
				String sub = trie.findSubstring(songs[i]);
				if (sub == null) {
					out.println(":(");
				} else {
					out.println("\"sub\"");
				}
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new MusicCollection().go();
	}
	
	private class Tree {
		
		int count;
		char val;
		Tree[] next;
		
		public Tree() {
			next = new Tree[26];
			val = 0;
			count = 0;
		}
		
		public void add(String s) {
			if (s.length() != 0) {
				int index = s.charAt(0)-'A';
				if (next[index] == null) {
					next[index] = new Tree();
				}
				next[index].add(s.substring(1));
				next[index].val = (char)(index + 'A');
				next[index].count++;
			}
		}
		
		public String findSubstring(String s) {
			return findSubstring(s, null);
		}
		
		private String findSubstring(String s, String ans) {
			//TODO write recursion
			return "";
		}
	}
}
