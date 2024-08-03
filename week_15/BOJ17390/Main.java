package week_15.BOJ17390;

import java.io.*;
import java.util.*;

// 수열 A (길이 N)
// 수열 B (A를 비내림차순으로 정렬)
// Query(L R : B의 L번째 원소부터 R번째 원소까지의 합) Q개
public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int N, Q; // N : 수열 A의 길이, Q : 쿼리의 개수
    private static Integer[] arr; // 수열
    private static Integer[] sum; // 누적합 sum[i] : i번째까지의 합

    public static void main(String[] args) throws IOException {
        init();
        Arrays.sort(arr);
        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }
        for (int i = 0; i < Q; i++) {
            query();
        }
        System.out.println(sb);
    }
    
    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new Integer[N];
        sum = new Integer[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void query() throws IOException {
        int L, R;
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        sb.append(sum[R] - sum[L - 1]).append("\n");
    }
}