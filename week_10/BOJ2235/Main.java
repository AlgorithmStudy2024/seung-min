package week_10.BOJ2235;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static List<String> answer;

    public static void main(String[] args) throws IOException {
        int problemNum = 0;
        while (true) {
            String s = br.readLine();
            if (s.equals("#")) {
                break;
            }
            problemNum += 1;
            sb.append("Problem ").append(problemNum).append("\n");
            char[] c = s.toCharArray();
            process(c);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void process(char[] c) throws IOException {
        while (true) {
            String s = br.readLine();
            if (s.equals("0")) {
                return;
            }
            answer = new ArrayList<>();
            char[] nums = s.toCharArray();
            dfs(c, nums, 0, "");
            int min = Integer.MAX_VALUE;
            for (String ans : answer) {
                min = Math.min(min, ans.length());
            }
            for (int i = answer.size(); i > 0; i--) {
                if (answer.get(i - 1).length() != min) {
                    answer.remove(i - 1);
                }
            }
            Collections.sort(answer, Collections.reverseOrder());
            sb.append(answer.get(0));
            sb.append("\n");
        }

    }
    
    private static void dfs(char[] c, char[] nums, int index, String s) {
        if (index == nums.length - 1) {
            answer.add(s + c[nums[index] - '0']);
            return;
        }
        if (index == nums.length) {
            answer.add(s);
            return;
        }
        dfs(c, nums, index + 1, s + c[nums[index] - '0']);
        if (((nums[index] - '0') * 10) + (nums[index + 1] - '0') <= 25) {
            String newS = s + c[((nums[index] - '0') * 10) + (nums[index + 1] - '0')];
            dfs(c, nums, index + 2, newS);
        }
    }
}
