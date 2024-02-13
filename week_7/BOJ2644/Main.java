package week_7.BOJ2644;

import java.io.*;
import java.util.*;


public class Main {

    private static int n;
    private static int u, v;
    private static int m;

    private static int[][] graph;
    private static boolean[] visited;

    private static int answer;

    public static void main(String[] args) throws IOException{
        init();
        bfs();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;
        }
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>(); // 노드 번호, 거리
        visited[u] = true;
        q.add(new int[] {u,0});
        while (!q.isEmpty()) {
            int[] nowNode = q.poll();
            if (nowNode[0] == v) {
                answer = nowNode[1];
                return;
            }
            for (int i = 1; i <= n; i++) {
                if (graph[nowNode[0]][i] == 1 && visited[i] == false) {
                    visited[i] = true;
                    q.add(new int[] { i, nowNode[1] + 1 });
                }
            }
        }
        answer = -1;
        return;
    }
}