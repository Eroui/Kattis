import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Putovanje {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numFruit = io.nextInt();
        int maxWeight = io.nextInt();
        int[] fruits = new int[numFruit];
        for (int i = 0; i < numFruit; i++) {
            fruits[i] = io.nextInt();
        }
        int best = 0;
        for (int i = 0; i < numFruit-best; i++) {
            int sum = 0;
            int count = 0;
            for (int e = i; e < numFruit; e++) {
                if (sum+fruits[e] <= maxWeight) {
                    sum += fruits[e];
                    count++;
                }
            }
            best = Math.max(best, count);
        }
        io.println(best);

        io.flush();
        io.close();
    }

    public static void main(String[] args) {
        new Putovanje().go();
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
