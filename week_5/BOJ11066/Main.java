package week_5.BOJ11066;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int T;
    private static int K;
    private static int[] arr;
    private static int[] arrSum;
    private static int[][] dp;
    

    public static void main(String[] args) throws IOException{
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            init();
            process();
            sb.append(dp[1][K]).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        K = Integer.parseInt(br.readLine());
        arr = new int[K + 1];
        arrSum = new int[K + 1];
        dp = new int[K+1][K+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= K; i++) {
            arrSum[i] = arrSum[i - 1] + arr[i];
        }
    }

    private static void process() {
        // dp[2] = arr[1] + arr[2];
        // dp[3] = dp[2] + arr[1] + arr[2] + arr[3];
        // for (int i = 3; i <= K; i++) {
        //     dp[i] = Math.min(dp[i - 1] + arrSum[i], dp[i - 2] + arr[i - 1] + arr[i] + arrSum[i]);
        // }
        for (int gap = 1; gap < K; gap++) {
            for (int start = 1; start + gap <= K; start++) {
                int end = start + gap;
                dp[start][end] = Integer.MAX_VALUE;
                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end],
                            dp[start][mid] + dp[mid + 1][end] + arrSum[end] - arrSum[start - 1]);
                }
            }
        }
    }
}