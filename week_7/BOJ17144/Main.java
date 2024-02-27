package week_7.BOJ17144;

import java.io.*;
import java.util.*;

public class Main {
    private static int R, C, T;
    private static int[][] map;
    private static List<AirCleaner> airCleaners;
    private static Queue<Dust> dusts;
    private static int answer;

    private static class AirCleaner {
        public int x, y;

        public AirCleaner(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
    }
    
    private static class Dust {
        public int x, y;
        public int size;

        public Dust(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

    }

    public static void main(String[] args) throws IOException{
        init();
        for (int t = 0; t < T; t++) {
            diffusionDust();
            operateUpAirCleaner();
            operateDownAirCleaner();
            checkDust();
        }
        checkAnswer();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dusts = new LinkedList<>();
        airCleaners = new ArrayList<>();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1)
                    airCleaners.add(new AirCleaner(i, j));
                if (map[i][j] > 0)
                    dusts.add(new Dust(i, j, map[i][j]));
            }
        }
    }

    
    private static void diffusionDust() {
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        while (!dusts.isEmpty()) {
            Dust dust = dusts.poll();
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                int nextX = dust.x + dx[i];
                int nextY = dust.y + dy[i];
                if (canGo(nextX, nextY)) {
                    sum += dust.size / 5;
                    map[nextX][nextY] += dust.size / 5;
                }
            }
            map[dust.x][dust.y] -= sum;
        }
    }
    
    private static void operateUpAirCleaner() {
        // 위쪽 공기청정기
        // 반시계 방향 0,1 -1,0 0,-1 1,0
        int[] upDx = {0,-1,0,1};
        int[] upDy = {1,0,-1,0};
        int nowDir = 0;
        AirCleaner upAirCleaner = airCleaners.get(0);
        int upX = upAirCleaner.x;
        int upY = upAirCleaner.y;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (true) {
            int nextX = upX + upDx[nowDir];
            int nextY = upY + upDy[nowDir];
            if (nextX == upAirCleaner.x && nextY == upAirCleaner.y) {
                break;
            }
            if (isOut(nextX, nextY)) {
                nowDir += 1;
            }
            upX += upDx[nowDir];
            upY += upDy[nowDir];
            int num = map[upX][upY];
            map[upX][upY] = queue.poll();
            queue.add(num);
        }
    }

    private static void operateDownAirCleaner() {
        // 아래쪽 공기청정기
        // 시계 방향 0,1 1,0 0,-1 -1,0
        int[] downDx = {0,1,0,-1};
        int[] downDy = { 1, 0, -1, 0 };
        int nowDir = 0;
        AirCleaner dowAirCleaner = airCleaners.get(1);
        int downX = dowAirCleaner.x;
        int downY = dowAirCleaner.y;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (true) {
            int nextX = downX + downDx[nowDir];
            int nextY = downY + downDy[nowDir];
            if (nextX == dowAirCleaner.x && nextY == dowAirCleaner.y) {
                break;
            }
            if (isOut(nextX, nextY)) {
                nowDir += 1;
            }
            downX += downDx[nowDir];
            downY += downDy[nowDir];
            int num = map[downX][downY];
            map[downX][downY] = queue.poll();
            queue.add(num);
        }

        for (AirCleaner airCleaner : airCleaners) {
            map[airCleaner.x][airCleaner.y] = -1;
        }
        
    }
    
    private static void checkDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    dusts.add(new Dust(i, j, map[i][j]));
            }
        }
    }

    private static void checkAnswer() {
        answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    answer += map[i][j];
                }
            }
        }
        
    }
    
    private static boolean canGo(int x, int y) {
        // 공기청정기 위치 확인
        for (AirCleaner airCleaner : airCleaners) {
            if (airCleaner.x == x && airCleaner.y == y) {
                return false;
            }
        }
        // 밖으로 나가지 않는가 확인
        if (x < 0 || y < 0 || x >= R || y >= C) {
            return false;
        }
        return true;
    }

    private static boolean isOut(int x, int y) {
        if (x < 0 || y < 0 || x >= R || y >= C) {
            return true;
        }
        return false;
    }
}
