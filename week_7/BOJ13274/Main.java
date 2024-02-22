package week_7.BOJ13274;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int K;
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            long X = Long.parseLong(st.nextToken());
            query(L, R, X);
        }
    }

    private static void query(int L, int R, long X) {
        Arrays.sort(arr);
        for (int i = L; i <= R; i++) {
            arr[i-1] += X;
        }
        Arrays.sort(arr);
    }
}