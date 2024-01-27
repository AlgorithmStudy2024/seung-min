package week_4.BOJ8972;

import java.io.*;
import java.util.*;

public class Main {

    static class Robot {
        int x;
        int y;

        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Person {
        int x;
        int y;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int R;
    private static int C;
    private static char[][] board;
    private static char[] command;

    private static Person person;
    private static List<Robot> robots;
    private static int K;
    
    public static void main(String[] args) throws IOException {
        init();
        process();
        answer();
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            board[i] = s.toCharArray();
        }

        command = br.readLine().toCharArray();

        robots = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'I') {
                    person = new Person(i, j);
                }
                if (board[i][j] == 'R') {
                    robots.add(new Robot(i, j));
                }
            }
        }

        K = 0;
    }
    
    private static void process() {
        for (char c : command) {
            personMove(c);
            robotsMove();
        }
    }

    private static void personMove(char command) {
        int[] dx = {  1, 1, 1,  0, 0, 0, -1, -1, -1 };
        int[] dy = { -1, 0, 1, -1, 0, 1, -1,  0,  1 };
        int num = command - '1';
        person.x += dx[num];
        person.y += dy[num];
        K++;
        for (Robot robot : robots) {
            if (person.x == robot.x && person.y == robot.y) {
                System.out.println("kraj " + K);
                System.exit(0);
            }
        }
    }
    
    private static void robotsMove() {
        int[] dx = { 1, 1, 1, 0, 0, -1, -1, -1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int[][] newMap = new int[R][C];
        for (Robot robot : robots) {
            int minDist = 1000000;
            int minDir = -1;
            for (int i = 0; i < 8; i++) {
                int nextX = robot.x + dx[i];
                int nextY = robot.y + dy[i];
                if (isOut(nextX, nextY))
                    continue;
                int dist = cal(person.x, person.y, nextX, nextY);
                if (minDist > dist) {
                    minDist = dist;
                    minDir = i;
                }
            }
            robot.x += dx[minDir];
            robot.y += dy[minDir];
            if (robot.x == person.x && robot.y == person.y) {
                System.out.println("kraj " + K);
                System.exit(0);
            }
            newMap[robot.x][robot.y] += 1;
        }
        
        for (int i = robots.size() - 1; i >= 0; i--) {
            Robot robot = robots.get(i);
            if (newMap[robot.x][robot.y] >= 2)
                robots.remove(robot);
        }
        for(Robot robot : robots){
            if(newMap[robot.x][robot.y]==2) robots.remove(robot);
        }

    }
    
    private static int cal(int personX, int personY, int robotX, int robotY) {
        return Math.abs(personX - robotX) + Math.abs(personY - robotY);
    }

    private static boolean isOut(int x, int y) {
        if (x < 0 || y < 0 || x >= R || y >= C)
            return true;
        return false;
    }

    private static void answer() {
        char[][] ans = new char[R][C];
        ans[person.x][person.y] = 'I';
        for (Robot robot : robots) {
            ans[robot.x][robot.y] = 'R';
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (ans[i][j] != 'I' && ans[i][j] != 'R') {
                    System.out.print('.');
                    continue;
                }
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
}
