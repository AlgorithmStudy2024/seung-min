package week_8.BOJ1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static int N;
    private static char[][] map;
    public static void main(String[] args) throws IOException{
        init();
        find(0, 0, N);
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    private static void find(int x, int y, int size) {
        char nowNum = map[x][y];
        int check = 1;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (nowNum != map[i][j]) {
                    check = 0;
                    sb.append("(");
                    find(x, y, size / 2);
                    find(x, y + size / 2, size / 2);
                    find(x + size / 2, y, size / 2);
                    find(x + size / 2, y + size / 2, size / 2);
                    sb.append(")");
                    return;
                }
            }
        }
        if (check == 1) {
            sb.append(nowNum);
            return;
        }
    }
}
