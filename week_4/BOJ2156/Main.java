package week_4.BOJ2156;

import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int[] nums;
    private static int[] dp;


    public static void main(String[] args) throws IOException {
        init();
        process();
        System.out.println(dp[n-1]);
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = num;
        }
    }

    private static void process() {
        if (n == 1) {
            dp[0] = nums[0];
            return;
        }
        if (n == 2) {
            dp[0] = nums[0];
            dp[1] = dp[0] + nums[1];
            return;
        }
        dp[0] = nums[0];
        dp[1] = dp[0] + nums[1];
        for (int i = 2; i < n; i++) {
            if (i < 3) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
                dp[i] = Math.max(dp[i], nums[i - 1] + nums[i]);
                continue;
            }
            dp[i] = Math.max(dp[i - 3] + nums[i - 1] + nums[i], dp[i - 2] + nums[i]);
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
    }
    
}