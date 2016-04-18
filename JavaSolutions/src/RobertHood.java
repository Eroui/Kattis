import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class RobertHood {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numArrows = io.nextInt();
        double[][] points = new double[numArrows][2];
        for (int i = 0; i < numArrows; i++) {
            points[i][0] = io.nextInt();
            points[i][1] = io.nextInt();
        }
        Arrays.sort(points, new Comparator<double[]>() {
            @Override
            public int compare(double[] one, double[] two) {
                if (one[0] < two[0]) {
                    return -1;
                } else if (one[0] > two[0]) {
                    return 1;
                } else if (one[1] < two[1]) {
                    return -1;
                } else if (one[1] > two[1]) {
                    return 1;
                }
                return 0;
            }
        });
        double x1 = points[0][0];
        double y1 = points[0][1];
        double x2 = points[1][0];
        double y2 = points[1][1];
        double dist = Math.hypot(x1-x2,y1-y2);
        for (int i = 2; i < numArrows; i++) {
            double x = points[i][0];
            double y = points[i][1];
            double dist1 = Math.hypot(x1-x,y1-y);
            double dist2 = Math.hypot(x2-x,y2-y);
            if (dist1 > dist2 && dist1 > dist) {
                x2 = x;
                y2 = y;
                dist = dist1;
            } else if (dist2 > dist) {
                x1 = x;
                y1 = y;
                dist = dist2;
            }
        }
        io.println(dist);

        io.flush();
        io.close();
    }

    public static void main(String[] args) {
        new RobertHood().go();
    }

    private class Kattio extends PrintWriter {

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasNext() {
            return peekToken() != null;
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }

        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }

        public long nextLong() {
            return Long.parseLong(nextToken());
        }

        public String next() {
            return nextToken();
        }

        public String nextLine() {
            token = null;
            st = null;
            try {
                return r.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
