import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Bing {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numWords = io.nextInt();
        Tree root = new Tree(' ');
        Tree[] roots = new Tree[26];
        for (int i = 0; i < numWords; i++) {
            String word = io.next();
            io.println(root.add(word, 0)-1);
        }

        io.flush();
        io.close();
    }

    public static void main(String[] args) {
        new Bing().go();
    }

    private class Tree {

        char letter;
        int count = 0;
        Tree[] others = new Tree[26];

        public Tree(char c) {
            letter = c;
        }

        public int add(String s, int index) {
            count++;
            if (index == s.length()) {
                return count;
            }
            char c = s.charAt(index);
            if (others[c-'a'] == null) {
                others[c-'a'] = new Tree(c);
            }
            return others[c-'a'].add(s, index+1);
        }
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
