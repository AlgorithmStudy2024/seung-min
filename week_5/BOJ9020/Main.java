package week_5.BOJ9020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int T;
    private static int N;
    private static boolean[] nums;
    private static int A;
    private static int B;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        init();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            process();
            sb.append(A).append(" ").append(B).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void init() throws IOException {
        nums = new boolean[100001];
        nums[0] = false;
        nums[1] = false;
        for (int i = 2; i < nums.length; i++) {
            nums[i] = true;
        }
        for (int i = 2; i <= Math.sqrt(10000); i++) {
            if (nums[i]) {
                for (int j = i * i; j < nums.length; j += i) {
                    nums[j] = false;
                }
            }
        }
    }

    private static void process() {
        for (int i = N/2; i <= N; i++) {
            if (nums[i] && nums[N - i]) {
                A = N - i;
                B = i;
                return;
            }
        }
    }
    
}