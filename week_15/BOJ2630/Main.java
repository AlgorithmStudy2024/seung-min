package week_15.BOJ2630;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[][] map;
    private static int white_num;
    private static int blue_num;
    public static void main(String[] args) throws IOException {
        init();
        check(0, 0, N);
        System.out.println(white_num);
        System.out.println(blue_num);
    }
    
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        white_num = 0;
        blue_num = 0;
    }

    private static void check(int x, int y, int l) {
        int start = map[x][y];
        for (int i = x; i < x + l; i++) {
            for (int j = y; j < y + l; j++) {
                if (start != map[i][j]) {
                    check(x, y, l / 2);
                    check(x + l / 2, y, l / 2);
                    check(x, y + l / 2, l / 2);
                    check(x + l / 2, y + l / 2, l / 2);
                    return;
                }
            }
        }
        if(start == 1){
            blue_num++;
        }
        if(start == 0){
            white_num++;
        }
        return;
    }
}
