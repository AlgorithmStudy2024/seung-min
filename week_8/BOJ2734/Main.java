package week_8.BOJ2734;

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static class Point {
        double x, y;

        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    private static List<Point> arr;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            process();
            double resultx = arr.get(0).x;
            double resulty = arr.get(0).y;
            sb.append(String.format("%.4f", resultx)).append(" ").append(String.format("%.4f", resulty)).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(new Point(Double.parseDouble(st.nextToken()), 1.0));
        }
    }

    private static void process() {
        while (arr.size() != 1) {
            int N = arr.size();
            for (int i = 0; i < N - 1; i++) {
                Point p1 = arr.get(i);
                Point p2 = arr.get(i + 1);
                Double r = calculateDistance(p1, p2);
                Double t = Math.sqrt(4 - Math.pow(r / 2.0, 2.0));

                Double nx = (p1.x + p2.x) / 2.0 - (t * (p2.y - p1.y) / r);
                Double ny = (p1.y + p2.y) / 2.0 + (t * (p2.x - p1.x) / r);

                arr.add(new Point(nx, ny));
            }
            for (int i = 0; i < N; i++) {
                arr.remove(0);
            }
        }
        
    }
    
    private static double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2.0) + Math.pow(b.y - a.y, 2.0));
    }
}