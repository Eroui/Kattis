import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Pop {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numBalloons = in.nextInt();
			if (numBalloons == 0) {
				break;
			}
			Balloon[] balloons = new Balloon[numBalloons];
			for (int i = 0; i < numBalloons; i++) {
				balloons[i] = new Balloon(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
			}
			int numShots = in.nextInt();
			for (int zz = 0; zz < numShots; zz++) {
				double x = in.nextDouble();
				double y = in.nextDouble();
				double z = in.nextDouble();
				double vx = in.nextDouble();
				double vy = in.nextDouble();
				double vz = in.nextDouble();
				int score = 0;
				for (int i = 0; i < balloons.length; i++) {
					Balloon curr = balloons[i];
					if (curr.popped) {
						continue;
					}
					double bx = curr.x - x;
					double by = curr.y - y;
					double bz = curr.z - z;
					double angle = Math.acos((bx*vx+by*vy+bz*vz)/magnitude(vx,vy,vz)/magnitude(bx,by,bz));

//					out.println(angle + " " + opposite(vx,vy,vz,bx,by,bz));
					if (angle >= Math.PI/2 || opposite(vx,vy,vz,bx,by,bz)) {
						continue;
					}
					double mag = (bx*vx+by*vy+bz*vz)/(vx*vx + vy*vy + vz*vz);
					double w1x = mag*vx;
					double w1y = mag*vy;
					double w1z = mag*vz;
					double w2x = bx-w1x;
					double w2y = by-w1y;
					double w2z = bz-w1z;
					double dist2 = Math.abs(w2x*w2x + w2y*w2y + w2z*w2z);
					if (dist2 <= curr.r*curr.r) {
						score++;
						curr.popped = true;
					}
//					out.printf("%f %f %f%n", w2x, w2y, dist);
				}
				out.println(score);
			}
		}
		
		in.close();
	}
	
	public double cross(double x1, double y1, double z1, double x2, double y2, double z2) {
		return magnitude(y1*z2-y2*z1, x2*z1-x1*z2, x1*y2-x2*y1);
	}
	
	public double magnitude(double x, double y, double z) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	public boolean opposite(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Math.abs(x1-x2) == Math.abs(x1) + Math.abs(x2) &&
				Math.abs(y1-y2) == Math.abs(y1) + Math.abs(y2) &&
				Math.abs(z1-z2) == Math.abs(z1) + Math.abs(z2);
	}
	
	public static void main(String[] args) {
		new Pop().go();
	}
	
	private class Balloon {
		double r, x, y, z;
		boolean popped;
		
		public Balloon(double r, double l, double x, double y) {
			this.r = r;
			this.x = x;
			this.y = y;
			z = l+r;
		}
	}
}
