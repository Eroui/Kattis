import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Bela {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numCards = io.nextInt()*4;
        int sum = 0;
        char trump = io.next().charAt(0);
        for (int i = 0; i < numCards; i++) {
            char[] card = io.next().toCharArray();
            if (card[1] == trump) {
                switch (card[0]) {
                    case 'A':
                        sum += 11;
                        break;
                    case 'K':
                        sum += 4;
                        break;
                    case 'Q':
                        sum += 3;
                        break;
                    case 'J':
                        sum += 20;
                        break;
                    case 'T':
                        sum += 10;
                        break;
                    case '9':
                        sum += 14;
                        break;
                }
            } else {
                switch (card[0]) {
                    case 'A':
                        sum += 11;
                        break;
                    case 'K':
                        sum += 4;
                        break;
                    case 'Q':
                        sum += 3;
                        break;
                    case 'J':
                        sum += 2;
                        break;
                    case 'T':
                        sum += 10;
                        break;
                }
            }
        }
        io.println(sum);

        io.flush();
        io.close();
    }

    public static void main(String[] args) {
        new Bela().go();
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
