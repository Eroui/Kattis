import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class TenKindsOfPeople {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numRows = io.nextInt();
        int numCols = io.nextInt();
        char[][] mat = new char[numRows][];
        for (int i = 0; i < numRows; i++) {
            mat[i] = io.next().toCharArray();
        }
        int[][] path = new int[numRows][numCols];
        int p = 1;
        for (int i = 0; i < numRows; i++) {
            for (int e = 0; e < numCols; e++) {
                if (path[i][e] == 0) {
                    flood(path, mat, i, e, p++, mat[i][e]);
                }
            }
        }
        int numQueries = io.nextInt();
        for (int i = 0; i < numQueries; i++) {
            int r1 = io.nextInt()-1;
            int c1 = io.nextInt()-1;
            int r2 = io.nextInt()-1;
            int c2 = io.nextInt()-1;
            if (mat[r1][c1] != mat[r2][c2] || path[r1][c1] != path[r2][c2]) {
                io.println("neither");
            } else if (mat[r1][c1] == '1') {
                io.println("decimal");
            } else {
                io.println("binary");
            }
        }

        io.flush();
        io.close();
    }

    public void flood(int[][] path, char[][] mat, int r, int c, int p, char ch) {
        if (inBounds(r,c,mat) && mat[r][c] == ch && path[r][c] == 0) {
            path[r][c] = p;
            flood(path, mat, r+1, c, p, ch);
            flood(path, mat, r-1, c, p, ch);
            flood(path, mat, r, c+1, p, ch);
            flood(path, mat, r, c-1, p, ch);
        }
    }

    public boolean inBounds(int r, int c, char[][] mat) {
        return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
    }

    public static void main(String[] args) {
        new TenKindsOfPeople().go();
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
