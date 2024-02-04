package week_5.BOJ15666;

import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static int N;
    private static int M;
    private static boolean[] nums;

    public static void main(String[] args) throws IOException {
        init();
        process();
        System.out.println(sb);
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new boolean[10001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[num] = true;
        }
    }

    private static void process() {
        dfs(0, 1, new ArrayList<>());
    }

    private static void dfs(int cnt, int start, List<Integer> arr) {
        if (cnt == M) {
            for (Integer answer : arr) {
                sb.append(answer).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= 10000; i++) {
            if (nums[i] == true) {
                arr.add(i);
                dfs(cnt + 1, i, arr);
                arr.remove(arr.size()-1);
            }
        }
    }
}
