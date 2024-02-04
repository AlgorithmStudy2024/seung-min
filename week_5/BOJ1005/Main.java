package week_5.BOJ1005;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int T;
    private static int N;
    private static int K;
    private static List<Node> graph;
    private static int W;
    
    static class Node {
        int num;
        int time;
        int inputNum;
        List<Node> nodes;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
            this.inputNum = 0;
            this.nodes = new ArrayList<>();
        }
    }

    static class Building implements Comparable<Building>{
        int num;
        int time;

        public Building(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Building o) {
            if (this.time < o.time) {
                return -1;
            }
            if (this.time > o.time) {
                return 1;
            }
            return 0;
        }

        
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            init();
            process();
        }
        System.out.println(sb);
    }
    
    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        graph.add(new Node(0, 0));

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int time = Integer.parseInt(st.nextToken());
            graph.add(new Node(i, time));
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int sourceNodeNum = Integer.parseInt(st.nextToken());
            int destinationNodeNum = Integer.parseInt(st.nextToken());
            Node sourceNode = graph.get(sourceNodeNum);
            Node destinationNode = graph.get(destinationNodeNum);
            sourceNode.nodes.add(destinationNode);
            destinationNode.inputNum++;
        }

        W = Integer.parseInt(br.readLine());

    }

    private static void process() {
        Queue<Building> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            Node n = graph.get(i);
            if (n.inputNum == 0) {
                q.add(new Building(n.num, n.time));
            }
        }

        while (!q.isEmpty()) {
            Building now = q.poll();
            int nodeNum = now.num;
            int time = now.time;
            Node node = graph.get(nodeNum);
            if (nodeNum == W) {
                sb.append(time).append("\n");
                return;
            }
            for (Node n : node.nodes) {
                n.inputNum--;
                if (n.inputNum == 0) {
                    q.add(new Building(n.num, n.time + time));
                }
            }
            node.nodes.clear();
        }
    }
}