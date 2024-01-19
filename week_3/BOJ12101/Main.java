package week_3.BOJ12101;

import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int k;
    private static List<List<Integer>> answer;

    public static void main(String[] args) throws IOException{
        init();
        makePermutation(0, new ArrayList<>());
        if (k <= answer.size()) {
            List<Integer> out = answer.get(k - 1);
            for (int i = 0; i < out.size() - 1; i++) {
                System.out.print(out.get(i));
                System.out.print("+");
            }
            System.out.println(out.get(out.size() - 1));
        } else {
            System.out.println(-1);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = new ArrayList<>();
    }
    
    private static void makePermutation(int num, List<Integer> arr) {
        if (num == n) {
            List<Integer> a = new ArrayList<>();
            a.addAll(arr);
            answer.add(a);
            return;
        }
        if (num > n) {
            return;
        }
        arr.add(1);
        makePermutation(num + 1, arr);
        arr.remove(arr.size() - 1);
        arr.add(2);
        makePermutation(num + 2, arr);
        arr.remove(arr.size() - 1);
        arr.add(3);
        makePermutation(num + 3, arr);
        arr.remove(arr.size() - 1);
    }
}
