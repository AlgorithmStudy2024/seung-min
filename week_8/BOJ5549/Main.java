package week_8.BOJ5549;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static int M;
    private static int N;
    private static int K;
    private static char[][] map;
    private static int[][] Jmap;
    private static int[][] Omap;
    private static int[][] Imap;

    public static void main(String[] args) throws IOException {
        init();
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            countMap(a, b, c, d);
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        map = new char[M][N];
        Jmap = new int[M + 1][N + 1];
        Omap = new int[M + 1][N + 1];
        Imap = new int[M + 1][N + 1];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'J') {
                    Jmap[i + 1][j + 1] = Jmap[i + 1][j] + Jmap[i][j + 1] - Jmap[i][j] + 1;
                    Omap[i + 1][j + 1] = Omap[i + 1][j] + Omap[i][j + 1] - Omap[i][j];
                    Imap[i + 1][j + 1] = Imap[i + 1][j] + Imap[i][j + 1] - Imap[i][j];
                }
                if (map[i][j] == 'O') {
                    Jmap[i + 1][j + 1] = Jmap[i + 1][j] + Jmap[i][j + 1] - Jmap[i][j];
                    Omap[i + 1][j + 1] = Omap[i + 1][j] + Omap[i][j + 1] - Omap[i][j] + 1;
                    Imap[i + 1][j + 1] = Imap[i + 1][j] + Imap[i][j + 1] - Imap[i][j];
                }
                if (map[i][j] == 'I') {
                    Jmap[i + 1][j + 1] = Jmap[i + 1][j] + Jmap[i][j + 1] - Jmap[i][j];
                    Omap[i + 1][j + 1] = Omap[i + 1][j] + Omap[i][j + 1] - Omap[i][j];
                    Imap[i + 1][j + 1] = Imap[i + 1][j] + Imap[i][j + 1] - Imap[i][j] + 1;
                }
            }
        }
    }

    private static void countMap(int a, int b, int c, int d) {
        int jCount = 0;
        int oCount = 0;
        int iCount = 0;
        jCount = Jmap[c][d] - Jmap[c][b - 1] - Jmap[a - 1][d] + Jmap[a - 1][b - 1];
        oCount = Omap[c][d] - Omap[c][b - 1] - Omap[a - 1][d] + Omap[a - 1][b - 1];
        iCount = Imap[c][d] - Imap[c][b - 1] - Imap[a - 1][d] + Imap[a - 1][b - 1];
        sb.append(jCount).append(" ").append(oCount).append(" ").append(iCount).append("\n");
    }
}

