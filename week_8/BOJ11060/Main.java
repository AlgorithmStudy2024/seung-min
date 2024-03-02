package week_8.BOJ11060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] A;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        process();
        if (dp[N - 1] == 10000) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N - 1]);
        }
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N];
        Arrays.fill(dp, 10000);
    }

    private static void process() {
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < i + 1 + A[i]; j++) {
                if (j > N-1)
                    break;
                dp[j] = Math.min(dp[j],dp[i] + 1);
            }
        }
    }
}
