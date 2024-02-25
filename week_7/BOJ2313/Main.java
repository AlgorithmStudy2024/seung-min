package week_7.BOJ2313;

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int n;
    private static int[] arr;
    private static int[] sum;
    private static List<Pair> answer;
    private static int answerSum;

    private static class Pair {
    
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        answer = new ArrayList<>();
        answerSum = 0;
        for (int i = 0; i < n; i++) {
            init();
            process();
        }
        System.out.println(answerSum);
        for (Pair p : answer) {
            System.out.print(p.a);
            System.out.print(" ");
            System.out.println(p.b);
        }
    }

    private static void init() throws IOException {
        int L = Integer.parseInt(br.readLine());
        arr = new int[L];
        sum = new int[L + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i + 1] = sum[i] + arr[i];
        }

    }
    
    private static void process() {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        for (int length = 1; length < sum.length; length++) { // 탐색범위
            for (int i = 0; i < sum.length - length; i++) {
                if (max < sum[i + length] - sum[i]) {
                    max = sum[i + length] - sum[i];
                    left = i + 1;
                    right = i + length;
                }
            }
        }
        answer.add(new Pair(left, right));
        answerSum += max;
    }
}
