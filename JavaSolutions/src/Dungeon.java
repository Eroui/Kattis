import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Dungeon {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numLevels = in.nextInt();
			int numRows = in.nextInt();
			int numCols = in.nextInt();
			if (numLevels == 0 && numRows == 0 && numCols == 0) {
				break;
			}
			in.nextLine();
			char[][][] maze = new char[numLevels][numRows][];
			int[][][] best = new int[numLevels][numRows][numCols];
			int startL = 0, startR = 0, startC = 0;
			int endL = 0, endR = 0, endC = 0;
			for (int l = 0; l < numLevels; l++) {
				for (int r = 0; r < numRows; r++) {
					maze[l][r] = in.nextLine().trim().toCharArray();
					for (int c = 0; c < numCols; c++) {
						best[l][r][c] = Integer.MAX_VALUE;
						if (maze[l][r][c] == 'S') {
							maze[l][r][c] = '.';
							startL = l;
							startR = r;
							startC = c;
						} else if (maze[l][r][c] == 'E') {
							maze[l][r][c] = '.';
							endL = l;
							endR = r;
							endC = c;
						}
					}
				}
				in.nextLine();
			}
			bfs(maze, best, startL, startR, startC, endL, endR, endC);
			if (best[endL][endR][endC] != Integer.MAX_VALUE) {
				out.printf("Escaped in %d minute(s).%n", best[endL][endR][endC]);
			} else {
				out.println("Trapped!");
			}
		}
		
		in.close();
	}
	
	public void bfs(char[][][] maze, int[][][] best, int l, int r, int c, int endL, int endR, int endC) {
		boolean[][][] visited = new boolean[maze.length][maze[l].length][maze[l][r].length];
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(new Point(l,r,c,0));
		while (!queue.isEmpty()) {
			Point p = queue.removeFirst();
			visited[p.l][p.r][p.c] = true;
			best[p.l][p.r][p.c] = p.dist;
			if (p.l == endL && p.r == endR && p.c == endC) {
				break;
			}
			if (inBounds(p.l+1,p.r,p.c,maze) && maze[p.l+1][p.r][p.c] == '.' && !visited[p.l+1][p.r][p.c]) {
				queue.add(new Point(p.l+1, p.r, p.c, p.dist+1));
				visited[p.l+1][p.r][p.c] = true;
			}
			if (inBounds(p.l-1,p.r,p.c,maze) && maze[p.l-1][p.r][p.c] == '.' && !visited[p.l-1][p.r][p.c]) {
				queue.add(new Point(p.l-1, p.r, p.c, p.dist+1));
				visited[p.l-1][p.r][p.c] = true;
			}
			if (inBounds(p.l,p.r+1,p.c,maze) && maze[p.l][p.r+1][p.c] == '.' && !visited[p.l][p.r+1][p.c]) {
				queue.add(new Point(p.l, p.r+1, p.c, p.dist+1));
				visited[p.l][p.r+1][p.c] = true;
			}
			if (inBounds(p.l,p.r-1,p.c,maze) && maze[p.l][p.r-1][p.c] == '.' && !visited[p.l][p.r-1][p.c]) {
				queue.add(new Point(p.l, p.r-1, p.c, p.dist+1));
				visited[p.l][p.r-1][p.c] = true;
			}
			if (inBounds(p.l,p.r,p.c+1,maze) && maze[p.l][p.r][p.c+1] == '.' && !visited[p.l][p.r][p.c+1]) {
				queue.add(new Point(p.l, p.r, p.c+1, p.dist+1));
				visited[p.l][p.r][p.c+1] = true;
			}
			if (inBounds(p.l,p.r,p.c-1,maze) && maze[p.l][p.r][p.c-1] == '.' && !visited[p.l][p.r][p.c-1]) {
				queue.add(new Point(p.l, p.r, p.c-1, p.dist+1));
				visited[p.l][p.r][p.c-1] = true;
			}
//			out.printf("%d %d %d%n", p.l, p.r, p.c);
//			out.println(queue.size());
		}
	}
	
	public boolean inBounds(int l, int r, int c, char[][][] maze) {
		return l >= 0 && l < maze.length && r >= 0 && r < maze[l].length && c >= 0 && c < maze[l][r].length;
	}
	
	public static void main(String[] args) {
		new Dungeon().go();
	}
	
	private class Point {
		int l,r,c;
		int dist;
		
		public Point(int x, int y, int z, int d) {
			l = x;
			r = y;
			c = z;
			dist = d;
		}
	}
}
