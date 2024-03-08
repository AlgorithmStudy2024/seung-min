package week_10.BOJ25827;

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    
    private static int n;

    private static List<Point> arr = new ArrayList<>();

    static class Point {
        int x;
        int y;


        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(String startPoint, String endPoint) {
            StringTokenizer startTime = new StringTokenizer(startPoint, ":");
            StringTokenizer endTime = new StringTokenizer(endPoint, ":");
            int h1 = Integer.parseInt(startTime.nextToken()) * 3600;
            int m1 = Integer.parseInt(startTime.nextToken()) * 60;
            int s1 = Integer.parseInt(startTime.nextToken());

            int h2 = Integer.parseInt(endTime.nextToken()) * 3600;
            int m2 = Integer.parseInt(endTime.nextToken()) * 60;
            int s2 = Integer.parseInt(endTime.nextToken());
            
            this.x = h1 + m1 + s1;
            this.y = h2 + m2 + s2;
        }
    }
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int query = 0; query < n; query++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            if (num.equals("1")) {
                addPoint(st.nextToken(), st.nextToken());
                continue;
            }
            if (num.equals("2")) {
                count(st.nextToken(), st.nextToken());
                continue;
            }
        }
        System.out.println(sb);
    }

    private static void addPoint(String startPoint, String endPoint) {        
        arr.add(new Point(startPoint, endPoint));
    }
    
    private static void count(String startPoint, String endPoint) {
        int ans = 0;
        Point b = new Point(startPoint, endPoint);

        for (Point a : arr) {
            // 1. a가 b보다 앞에 있는 경우, a가 b보다 뒤에 있는 경우
            if (a.y < b.x || b.y < a.x) {
                continue;
            }
            // 2. a가 b보다 앞에 있고 겹치는 경우
            if (a.x <= b.x && a.y <= b.y) {
                ans += a.y - b.x;
                continue;
            }
            // 3. a가 b보다 뒤에 있고 겹치는 경우
            if (b.x <= a.x && b.y <= a.y) {
                ans += b.y - a.x;
                continue;
            }
            // 4. a가 b안에 포함되는 경우
            if (b.x <= a.x && a.y <= b.y) {
                ans += a.y - a.x;
                continue;
            }
            // 5. b가 a안에 포함되는경우
            if (a.x <= b.x && b.y <= a.y) {
                ans += b.y - b.x;
                continue;
            }
        }
        sb.append(ans).append("\n");
    }

}
