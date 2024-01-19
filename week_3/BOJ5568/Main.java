package week_3.BOJ5568;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int K;
    private static List<Integer> cards;
    private static Set<Integer> nums;

    public static void main(String[] args) throws IOException {
        init();
        selectCards(0, 0, new ArrayList<>());
        System.out.println(nums.size());
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        cards = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(br.readLine());
            cards.add(card);
        }

        nums = new HashSet<>();
    }

    private static void selectCards(int start, int selectNum, List<Integer> current) {
        if (selectNum == K) {
            permuteCard(current,0);
            return;
        }
        for (int i = start; i < cards.size(); i++) {
            current.add(cards.get(i));
            selectCards(i + 1, selectNum + 1, current);
            current.remove(current.size() - 1);
        }
    }

    private static void permuteCard(List<Integer> current, int currentIndex) {
        if (currentIndex == current.size() - 1) {
            addCard(current);
            return;
        }
        for (int i = currentIndex; i < current.size(); i++) {
            Collections.swap(current, currentIndex, i);
            permuteCard(current, currentIndex + 1);
            Collections.swap(current, currentIndex, i);
        }
    }
    
    private static void addCard(List<Integer> arr) {
        String cardNum = "";
        for (int num : arr) {
            cardNum += num;
        }
        nums.add(Integer.valueOf(cardNum));
    }
}