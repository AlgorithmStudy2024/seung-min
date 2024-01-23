package week_4.BOJ1256;

import java.io.*;

public class Main {
    private static int n;
    private static int[] nums;
    private static int[] dp;


    public static void main(String[] args) throws IOException {
        init();
        process();
        System.out.println(dp[n]);
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n+1];
        dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = num;
        }
    }

    private static void process() {
        if (n == 1) {
            dp[1] = nums[1];
            return;
        }
        if (n == 2) {
            dp[1] = nums[1];
            dp[2] = dp[1] + nums[2];
            return;
        }
        dp[1] = nums[1];
        dp[2] = dp[1] + nums[2];
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 3] + nums[i - 1] + nums[i], dp[i - 2] + nums[i]);
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
    }
    
}