package week_3.BOJ5568;

import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static Node rootNode;

    private static class Node {
        int data;
        Node left;
        Node right;

        private Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException{
        init();
        postOrder(rootNode);
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nodeNum = br.readLine();
        rootNode = new Node(Integer.parseInt(nodeNum));

        while ((nodeNum = br.readLine()) != null) {
            Node node = new Node(Integer.parseInt(nodeNum));

            Node parentNode = rootNode;
            for (Node childNode = parentNode; childNode != null;) {
                parentNode = childNode;
                if (node.data < parentNode.data)
                    childNode = parentNode.left;
                else
                    childNode = parentNode.right;
            }

            if (node.data < parentNode.data)
                parentNode.left = node;
            else
                parentNode.right = node;
        }

    }
    // 왼 오 중
    private static void postOrder(Node node) {
        if (node.left != null)
            postOrder(node.left);
        if (node.right != null)
            postOrder(node.right);
        sb.append(node.data).append("\n");
    }
}