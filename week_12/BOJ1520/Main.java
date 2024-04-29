package week_12.BOJ1520;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    private static int M, N;
    private static int[][] map;
    private static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        init();
        process();
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void process() {
        Stack<Point> stack = new Stack<>();
        stack.add(new Point(0, 0));
        
    }
}
