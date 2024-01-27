package week_4.BOJ3257;

import java.io.*;

public class Main {
    private static char[] a = new char[151];
    private static int al;
    private static char[] b = new char[151];
    private static int bl;
    private static char[] c = new char[301];
    private static int[][] cache = new int[151][151];

    public static void main(String[] args) throws IOException {
        init();
        makeMap();
        recur(al, bl);
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputs = br.readLine().toCharArray();
        al = inputs.length;
        for (int i = 0; i < inputs.length; i++) {
            a[i] = inputs[i];
        }
        inputs = br.readLine().toCharArray();
        bl = inputs.length;
        for (int i = 0; i < inputs.length; i++) {
            b[i] = inputs[i];
        }
        inputs = br.readLine().toCharArray();
        for (int i = 0; i < inputs.length; i++) {
            c[i] = inputs[i];
        }
        
        cache[0][0] = 1;
    }
    
    private static void makeMap() {
        for (int i = 0; i <= al; i++) {
            for (int j = 0; j <= bl; j++) {
                if (cache[i][j] > 0) {
                    if (c[i + j] == a[i])
                        cache[i + 1][j] = 1;
                    if (c[i + j] == b[j])
                        cache[i][j + 1] = 2;
                }
            }
        }
    }
    
    private static void recur(int a, int b) {
        if (a == 0 && b == 0)
            return;
        if (cache[a][b] == 1) {
            recur(a - 1, b);
        } else if (cache[a][b] == 2) {
            recur(a, b - 1);
        }
        System.out.print(cache[a][b]);
    }



}
