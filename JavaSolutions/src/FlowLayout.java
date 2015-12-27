import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class FlowLayout {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int maxWidth = in.nextInt();
			if (maxWidth == 0) {
				break;
			}
			int widthBounds = 0;
			int heightBounds = 0;
			int maxHeight = 0;
			int currWidth = 0;
			while (in.hasNextInt()) {
				int width = in.nextInt();
				int height = in.nextInt();
				if (width == -1 && height == -1) {
					break;
				}
				if (width + currWidth > maxWidth) {
					heightBounds += maxHeight;
					widthBounds = Math.max(currWidth, widthBounds);
					maxHeight = 0;
					currWidth = 0;
				}
				currWidth += width;
				maxHeight = Math.max(maxHeight, height);
			}
			heightBounds += maxHeight;
			widthBounds = Math.max(currWidth, widthBounds);
			maxHeight = 0;
			currWidth = 0;
			out.printf("%d x %d%n", widthBounds, heightBounds);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new FlowLayout().go();
	}
}
