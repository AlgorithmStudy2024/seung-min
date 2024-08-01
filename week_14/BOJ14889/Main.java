package week_14.BOJ14889;

// 알고리즘 구현
// 1. 1~N 중 N/2개를 고르는 경우를 구한다.
// 2. 모든 수에 대하여 Sij를 구한다.
// 3. 최소 값을 갱신한다.

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[][] map;
    private static boolean[] team;
    private static int min;
    
    public static void main(String[] args) throws IOException {
        init();
        team = new boolean[N + 1];
        backtracking(1, N, 0);
        System.out.println(min);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
    }

    private static void backtracking(int now, int end, int size) {
        if (size == N / 2) {
            calculate();
            return;
        }
        if (size > N / 2) {
            return;
        }
        if (now > end) {
            return;
        }
        team[now] = true;
        backtracking(now + 1, end, size + 1);
        team[now] = false;
        backtracking(now + 1, end, size);
    }
    
    private static void calculate() {
        int startPoint = 0;
        int linkPoint = 0;
        List<Integer> startTeam = new ArrayList<>();
        List<Integer> linkTeam = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (team[i]) {
                startTeam.add(i);
            } else {
                linkTeam.add(i);
            }
        }
        for (int i : startTeam) {
            for (int j : startTeam) {
                if (i == j)
                    continue;
                startPoint += map[i][j];
            }
        }
        
        for (int i : linkTeam) {
            for (int j : linkTeam) {
                if (i == j)
                    continue;
                linkPoint += map[i][j];
            }
        }

        int num = Math.abs(startPoint - linkPoint);
        if (num < min) {
            min = num;
        }
    }
}
