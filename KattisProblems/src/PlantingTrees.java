import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class PlantingTrees {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numSeeds = in.nextInt();
		int[] times = new int[numSeeds];
		for (int i = 0; i < numSeeds; i++) {
			times[i] = in.nextInt();
		}
		Arrays.sort(times);
		int max = 0;
		for (int i = 0; i < numSeeds; i++) {
			max = Math.max(times[i]+numSeeds-i, max);
		}
		out.println(max+1);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new PlantingTrees().go();
	}
}
