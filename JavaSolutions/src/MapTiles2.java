import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class MapTiles2 {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		String next = in.nextLine().trim();
		int mapSize = (int)Math.round(Math.pow(2, next.length()));
		int lowW = 0, highW = mapSize, lowH = 0, highH = mapSize;
		for (char c : next.toCharArray()) {
			int newH = (highH+lowH)/2;
			int newW = (highW+lowW)/2;
			switch(c) {
			case '0':
				highW = newW;
				highH = newH;
				break;
			case '1':
				lowW = newW;
				highH = newH;
				break;
			case '2':
				highW = newW;
				lowH = newH;
				break;
			case '3':
				lowW = newW;
				lowH = newH;
				break;
			}
		}
		out.printf("%d %d %d", next.length(), lowW, lowH);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new MapTiles2().go();
	}
}
