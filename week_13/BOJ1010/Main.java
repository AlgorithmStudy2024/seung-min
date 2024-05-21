package week_13.BOJ1010;

import java.io.*;
import java.util.*;

public class Main {

    private static int T; // 테스트케이스의 수
    private static int N, M; // 강의 서쪽과 동쪽에 있는 사이트의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int res = 1;
            int tmp = 1;
            for (int i = M; i > M - N; i--) {
                res = res * i;
                res = res/tmp;
                tmp++;
                
            }
            System.out.println(res);

        }
    }

}
