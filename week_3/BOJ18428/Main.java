package week_3.BOJ18428;

import java.io.*;
import java.util.*;

public class Main {
    
    private static int N; // 배열 사이즈
    private static char[][] map;
    private static List<Integer> arr;
    private static List<Integer> teachers;
    private static List<List<Integer>> result;
    private static String answer;

    private static int[] dx = { 0, 0, 1, -1 };
    private static int[] dy = { 1, -1, 0, 0 };

    private static boolean[] visited;
    private static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        init();

        // 3개를 선택하는 조합 구하기
        combination(0, 3, new ArrayList<>());

        answer = "NO";
        // 결과 출력
        for (List<Integer> combi : result) {
            for (int i : combi) {
                int row = i / N;
                int col = i % N;
                map[row][col] = 'O';
            }
            if (bfs()) {
                answer = "YES";
                break;
            }
            for (int i : combi) {
                int row = i / N;
                int col = i % N;
                map[row][col] = 'T';
            }
        }

        System.out.println(answer);
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        arr = new ArrayList<>();
        teachers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'X') {
                    arr.add(i * N + j);
                }
                if (map[i][j] == 'T') {
                    teachers.add(i * N + j);
                }
            }
        }
        
        result = new ArrayList<>();

    }

    private static void combination(int start, int count, ArrayList<Integer> current) {

        if (count == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < arr.size(); i++) {
            current.add(arr.get(i));
            combination(i + 1, count - 1, current);
            current.remove(current.size() - 1);
        }
    }
    
    private static boolean bfs() {
        visited = new boolean[36];
        q = new ArrayDeque<>();

        for (Integer t : teachers) {
            visited[t] = true;
            q.add(t);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            int nowRow = now / N;
            int nowCol = now % N;
            for (int i = 0; i < 4; i++) {
                int nextRow = nowRow + dx[i];
                int nextCol = nowCol + dy[i];
                while (!isOut(nextRow, nextCol)) {
                    if (map[nextRow][nextCol] != 'O') {
                        visited[nextRow * N + nextCol] = true;
                        nextRow += dx[i];
                        nextCol += dy[i];
                    } else {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S' && visited[i * N + j]) {
                    return false;
                }
            }
        }
        return true;
    }


    
    private static boolean isOut(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N)
            return true;
        return false;
    }
}
