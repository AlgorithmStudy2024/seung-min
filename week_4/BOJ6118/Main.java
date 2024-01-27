package week_4.BOJ6118;

import java.io.*;
import java.util.*;


public class Main {

    private static int N;
    private static int M;
    private static List<Node> graph;
    private static boolean[] visited;
    private static int maxDepth;

    private static class Node {
        int index;
        int depth;
        List<Node> nodes;

        public Node(int index) {
            this.index = index;
            this.depth = -1;
            this.nodes = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) throws IOException{
        init();
        bfs();
        ans();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            graph.add(new Node(i));
        }
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node aNode = graph.get(a - 1);
            Node bNode = graph.get(b - 1);
            aNode.nodes.add(bNode);
            bNode.nodes.add(aNode);
        }
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        Node startNode = graph.get(0);
        q.add(startNode);
        visited[startNode.index] = true;
        startNode.depth = 0;
        while (!q.isEmpty()) {
            Node nowNode = q.poll();
            for (Node n : nowNode.nodes) {
                if (visited[n.index])
                    continue;
                visited[n.index] = true;
                n.depth = nowNode.depth + 1;
                maxDepth = Math.max(maxDepth, n.depth);
                q.add(n);
            }
        }

    }
    
    private static void ans() {
        int minNode = 100000;
        int count = 0;
        for (Node n : graph) {
            if (n.depth == maxDepth) {
                minNode = Math.min(minNode, n.index);
                count++;
            }
        }

        System.out.println(minNode + " " + maxDepth + " " + count);
    }
}
