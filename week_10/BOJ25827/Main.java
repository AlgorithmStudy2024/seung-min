package week_10.BOJ25827;

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static long[] time = new long[24 * 60 * 60 + 1];
    // time[d] : [d-1,d]에 가지고 있는 값
    private static long[] sum = new long[24 * 60 * 60 + 1];
    // sum[d] : [0,1]...[d-1,d]까지의 합
    // sum[1] : time[1]
    // sum[2] : sum[1] + time[2]
    // sum[3] : sum[2] + time[3]
    // [a,a+1]....[b-1,b] : sum[b] - sum[a]
    

    private static int n;

    private static int k = 1; // 유형 2가 처음 나왔을때를 확인하기 위한 용도

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int query = 0; query < n; query++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            if (num.equals("1")) {
                String startTime = st.nextToken();
                String endTime = st.nextToken();
                int startSec = changeTimeSec(startTime);
                int endSec = changeTimeSec(endTime);
                time[startSec+1]++;
                time[endSec+1]--;
            }
            if (num.equals("2")) {
                String startTime = st.nextToken();
                String endTime = st.nextToken();
                int startSec = changeTimeSec(startTime);
                int endSec = changeTimeSec(endTime);
                if (k == 1) {
                    k--;
                    makeArray();
                }
                sb.append(sum[endSec] - sum[startSec]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int changeTimeSec(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = 3600 * Integer.parseInt(st.nextToken());
        int min = 60 * Integer.parseInt(st.nextToken());
        int sec = Integer.parseInt(st.nextToken());

        return hour + min + sec;
    }

    private static void makeArray() {
        for (int i = 1; i <= 24 * 60 * 60; i++) {
            time[i] += time[i - 1];
        }
        for (int i = 1; i <= 24 * 60 * 60; i++) {
            sum[i] = sum[i - 1] + time[i];
        }
    }
}
