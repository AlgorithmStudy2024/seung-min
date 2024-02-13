package week_6.BOJ15664;

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static Set<ArrayList<Integer>> set;
    private static ArrayList<Integer> ans;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        init();
        process();
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        set = new HashSet<>();
        ans = new ArrayList<>();
    }

    private static void process() {
        Arrays.sort(arr);
        dfs(0, 0);
    }
    
    private static void dfs(int num, int index) {
        if (num == M) {
            if (set.contains(ans)) {
                return;
            }
            set.add((ArrayList<Integer>) ans.clone());
            for (int i : ans) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        if (num > M) {
            return;
        }
        for (int i = index; i < N; i++) {
            ans.add(arr[i]);
            dfs(num + 1, i + 1);
            ans.remove(ans.size() - 1);
            dfs(num, i + 1);
        }
    }

}