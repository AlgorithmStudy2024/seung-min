package week_12.BOJ1012;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T;
    private static int M, N, K;

    private static int[][] map;
    private static boolean[][] visited;
    private static List<Point> points;

    private static int ans;
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            init();
            process();
            System.out.println(ans);
        }
    }
    
    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];
        points = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            if (map[x][y] == 1)
                points.add(new Point(x, y));
        }
        ans = 0;
    }
    
    private static void process() {
        for (Point p : points) {
            if (!visited[p.x][p.y]) {
                ans++;
                bfs(p);
            }
        }
    }
    
    private static void bfs(Point p) {
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        Queue<Point> queue = new LinkedList<>();
        visited[p.x][p.y] = true;
        queue.add(p);

        while (!queue.isEmpty()) {
            Point nowP = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = nowP.x + dx[i];
                int nextY = nowP.y + dy[i];
                if (isOut(nextX, nextY))
                    continue;
                if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    queue.add(new Point(nextX, nextY));
                }
            }
        }
    }

    private static boolean isOut(int x, int y) {
        if (x < 0 || y < 0 || x >= M || y >= N) {
            return true;
        }
        return false;
    }
}
