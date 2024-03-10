package week_10.BOJ5175;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int T;
    private static int M;
    private static int N;
    private static List<List<Integer>> set;
    private static List<List<Integer>> combi;
    
    public static void main(String[] args) throws IOException{
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("Data Set ").append(i).append(": ");
            init();
            boolean check = false;
            // Combination 구현
            for (int chooseNum = 1; chooseNum <= N; chooseNum++) { // 선택하는 수
                if (check == true)
                    break;
                combi = new ArrayList<>();
                combination(new boolean[N], 0, chooseNum);
                for (List<Integer> a : combi) {
                    if (checkList(a)) {
                        check = true;
                        break;
                    }

                }
            }
            sb.append("\n");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        set = new ArrayList<>();
        for (int dataSet = 1; dataSet <= N; dataSet++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> data = new ArrayList<>();
            while (st.hasMoreTokens()) {
                data.add(Integer.parseInt(st.nextToken()));
            }
            set.add(data);
        }
    }
    
    private static void combination(boolean[] visited, int index, int chooseNum) {
        if (chooseNum == 0) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visited[i] == true) {
                    ans.add(i);
                }
            }
            combi.add(ans);
            return;
        }
        if (index == N) {
            return;
        }
        visited[index] = true;
        combination(visited, index + 1, chooseNum - 1);

        visited[index] = false;
        combination(visited, index + 1, chooseNum);
    }
    
    private static boolean checkList(List<Integer> arr) {
        boolean[] check = new boolean[M + 1];
        for(int i : arr){
            List<Integer> a = set.get(i);
            for(int j : a){
                check[j] = true;
            }
        }

        for (int i = 1; i <= M; i++) {
            if (check[i] == false)
                return false;
        }
        for (int i : arr) {
            sb.append((char) ('A' + i)).append(" ");
        }
        return true;

    }
}