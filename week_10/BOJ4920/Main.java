package week_10.BOJ4920;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int[][] map;
    private static List<int[][]> firstBlock;
    private static List<int[][]> secondBlock;
    private static List<int[][]> thirdBlock;
    private static List<int[][]> fourthBlock;
    private static List<int[][]> fifthBlock;
    private static int ans;

    public static void main(String[] args) throws IOException {
        int cnt = 0;
        while (true) {
            cnt++;
            ans = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0)
                break;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            init();
            process();

            sb.append(cnt).append(". ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        // 1번 블럭
        firstBlock = new ArrayList<>();
        int[][] block_1 = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } };
        for (int i = 0; i < 2; i++) {
            firstBlock.add(block_1);
            block_1 = rotate90(block_1);
        }

        // 2번 블럭
        secondBlock = new ArrayList<>();
        int[][] block_2 = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } };
        for (int i = 0; i < 2; i++) {
            secondBlock.add(block_2);
            block_2 = rotate90(block_2);
        }

        // 3번 블럭
        thirdBlock = new ArrayList<>();
        int[][] block_3 = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } };
        for (int i = 0; i < 4; i++) {
            thirdBlock.add(block_3);
            block_3 = rotate90(block_3);
        }

        // 4번 블럭
        fourthBlock = new ArrayList<>();
        int[][] block_4 = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } };
        for (int i = 0; i < 4; i++) {
            fourthBlock.add(block_4);
            block_4 = rotate90(block_4);
        }

        // 5번 블럭
        fifthBlock = new ArrayList<>();
        int[][] block_5 = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        fifthBlock.add(block_5);
    }

    private static int[][] rotate90(int[][] arr) {
        int[][] newBlock = new int[arr.length][arr[0].length];
        for (int j = 0; j < arr.length; j++) {
            newBlock[j][0] = arr[j][1];
            newBlock[j][1] = -arr[j][0];
        }
        return newBlock;
    }

    private static void process() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int[][] block : firstBlock) {
                    ans = Math.max(ans, find(i, j, block));
                }
                for (int[][] block : secondBlock) {
                    ans = Math.max(ans, find(i, j, block));
                }
                for (int[][] block : thirdBlock) {
                    ans = Math.max(ans, find(i, j, block));
                }
                for (int[][] block : fourthBlock) {
                    ans = Math.max(ans, find(i, j, block));
                }
                for (int[][] block : fifthBlock) {
                    ans = Math.max(ans, find(i, j, block));
                }
            }
        }
    }

    private static int find(int x, int y, int[][] blocks) {
        int answer = 0;
        for (int[] block : blocks) {
            int nextX = x + block[0];
            int nextY = y + block[1];
            if (nextX >= N || nextY >= N || nextX < 0 || nextY < 0) {
                return Integer.MIN_VALUE;
            }
            answer += map[nextX][nextY];
        }
        return answer;
    }
}


