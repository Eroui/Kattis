import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class HittingTargets {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numTargets = in.nextInt();
		Shape[] targets = new Shape[numTargets];
		for (int i = 0; i < numTargets; i++) {
			String type = in.next();
			if (type.equals("rectangle")) {
				targets[i] = new Rectangle(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			} else {
				targets[i] = new Circle(in.nextInt(), in.nextInt(), in.nextInt());
			}
		}
		int numShots = in.nextInt();
		for (int i = 0; i < numShots; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int count = 0;
			for (Shape s : targets) {
				if (s.isInside(x, y)) {
					count++;
				}
			}
			out.println(count);
		}
		in.close();
	}
	
	public static void main(String[] args) {
		new HittingTargets().go();
	}
	
	private interface Shape {
		boolean isInside(int x, int y);
	}
	
	private class Rectangle implements Shape {
		int x1, y1, x2, y2;
		
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
		
		public boolean isInside(int x, int y) {
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		}
	}
	
	private class Circle implements Shape {
		int x,y,r;
		
		public Circle(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
		public boolean isInside(int x, int y) {
			int dist = (this.x-x)*(this.x-x) + (this.y-y)*(this.y-y);
			return dist <= r*r;
		}
	}
}
