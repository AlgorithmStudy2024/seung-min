package week_14.BOJ7562;

import java.io.*;
import java.util.*;

// 이동하는 위치의 최소 횟수를 구하는 것
// BFS
// 큐를 활용

// dx dy 테크닉사용

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T; // 테스트 케이스 수
    private static int l; // 한 변의 길이

    private static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
    private static int[] dy = { 2, 1, -1, -2, -2, -1, 1, 2 };

    private static int startX;
    private static int startY;

    private static int endX;
    private static int endY;

    private static StringBuilder sb = new StringBuilder();

    static class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            calculate();
        }
        System.out.println(sb);
    }
    
    private static void calculate() throws IOException {
        l = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        // BFS
        Queue<Point> queue = new LinkedList<>();

        boolean[][] visited = new boolean[l][l];
        
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == endX && p.y == endY) {
                sb.append(p.time).append("\n");
                break;
            }
            for (int i = 0; i < 8; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];
                if (p.x + dx[i] >= 0 && p.x + dx[i] < l && p.y + dy[i] >= 0 && p.y + dy[i] < l) {
                    if (visited[newX][newY]) {
                        continue;
                    }
                    queue.offer(new Point(newX, newY, p.time + 1));
                    visited[newX][newY] = true;
                }
            }
        }
    }
}
