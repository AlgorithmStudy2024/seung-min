package week_6.BOJ17271;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static long[] dp;
    private static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        init();
        process();
        System.out.println(dp[N]);
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new long[N + 1];
    }

    private static void process() {
        dp[0] = 1;
        if (N < M) {
            for (int i = 1; i <= N; i++) {
                dp[i] = dp[i -1] % MOD;
            }
            return;
        }
        for (int i = 1; i < M; i++){
            dp[i] = dp[i-1] % MOD;
        }
        for (int i = M; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - M]) % MOD;
        }
    }

}
