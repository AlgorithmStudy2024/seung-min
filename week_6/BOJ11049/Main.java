package week_6.BOJ11049;

import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] data;
    private static int[][] dp;
    

    public static void main(String[] args) throws IOException {
        init();
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            data[i] = r;
            data[i + 1] = c;
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        System.out.println(solve(0, N - 1));

    }
    
    private static int solve(int pos, int cur) {
        if (pos == cur)
            return 0;
        if (dp[pos][cur] != Integer.MAX_VALUE)
            return dp[pos][cur];
        for (int i = pos; i < cur; i++) {
            int value = solve(pos, i) + solve(i + 1, cur) + (data[pos] * data[i + 1] * data[cur + 1]);
            dp[pos][cur] = Math.min(dp[pos][cur], value);
        }
        return dp[pos][cur];
    }
}