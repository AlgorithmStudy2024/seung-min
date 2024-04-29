package week_12.BOJ14502;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static int[][] newMap;
    private static int ans;

    private static List<Point> empty = new ArrayList<>(); // 빈공간 리스트
    private static List<List<Point>> checkList = new ArrayList<>();
    
    private static List<Point> virus = new ArrayList<>(); // 바이러스 리스트

    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        process();
        System.out.println(ans);
    }
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    empty.add(new Point(i, j));
                }
                if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }
        ans = Integer.MIN_VALUE;
    }

    
    private static void process() {
        // 빈공간 3개를 선택한다.
        selectEmptySpace(0, new ArrayList<Point>());
        
        // 선택한 3개를 가지고 지도에서 bfs를 진행한다.
        for (List<Point> check : checkList) {
            visited = new boolean[N][M];
            newMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    newMap[i][j] = map[i][j];
                }
            }
            // 1. 선택한 공간들에 벽을 세운다.
            for (Point p : check) {
                newMap[p.x][p.y] = 1;
            }
            // 2. 나머지 공간에서 bfs를 수행한다.
            bfs();

            // 3. 남은 공간의 숫자를 센다.
            countEmpty(newMap);

        }
    }


    private static void selectEmptySpace(int index, List<Point> select) {
        if (select.size() == 3) {
            List<Point> arr = new ArrayList<>();
            for (Point p : select) {
                arr.add(new Point(p.x, p.y));
            }
            checkList.add(arr);
            return;
        }
        for (int i = index; i < empty.size(); i++) {
            select.add(empty.get(i));
            selectEmptySpace(i + 1, select);
            select.remove(select.size()-1);
        }
    }
    
    private static void countEmpty(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    cnt++;
            }
        }
        ans = Math.max(cnt, ans);
    }
    
    private static void bfs() {
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        Queue<Point> queue = new LinkedList<>();

        for (Point p : virus) {
            queue.add(p);
            visited[p.x][p.y] = true;
        }

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = p.x + dx[i];
                int nextY = p.y + dy[i];
                if (isOut(nextX, nextY))
                    continue;
                if (newMap[nextX][nextY] == 1 || newMap[nextX][nextY] == 2)
                    continue;
                visited[nextX][nextY] = true;
                newMap[nextX][nextY] = 2;
                queue.add(new Point(nextX, nextY));
            }
        }
    }
    
    private static boolean isOut(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) {
            return true;
        }
        return false;
    }
}