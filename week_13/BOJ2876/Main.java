package week_13.BOJ2876;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] arr = new int[6];
    private static int maxNum;
    private static int maxGrade;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maxNum = 0;
        maxGrade = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            check(num1, num2);
            for (int j = 1; j <= 5; j++) {
                if (arr[j] > maxNum) {
                    maxNum = arr[j];
                    maxGrade = j;
                }
            }
        }

        System.out.println(maxNum + " " + maxGrade);
    }
    
    private static void check(int num1, int num2){
        if (num1 == num2) {
            arr[num1]++;
            for (int j = 1; j <= 5; j++) {
                if (j != num1) {
                    arr[j] = 0;
                }
            }
        } else {
            arr[num1]++;
            arr[num2]++;
            for (int j = 1; j <= 5; j++) {
                if (j != num1 && j != num2) {
                    arr[j] = 0;
                }
            }
        }
    }
}
