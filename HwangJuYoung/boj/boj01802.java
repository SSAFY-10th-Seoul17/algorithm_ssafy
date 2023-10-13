package BOJ;

import java.util.*;
import java.io.*;

public class boj01802 {
    private static boolean flag;
    private static ArrayList<Object> paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            String str = br.readLine();
            paper = new ArrayList<>();

            for (int i = 0; i < str.length(); i++) {
                paper.add(str.charAt(i) - '0');
            }

            flag = true;
            DC(0, paper.size()-1);

            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    } // end of main

    private static void DC(int start, int end) {
        if (start >= end) {
            flag = true;
            return;
        }

        int mid = (start + end) / 2;

        for (int i = 0; i < mid - start; i++) {
            int s = start + i;
            int e = end - i;

            if (paper.get(start + i) == paper.get(end - i)) {
                flag = false;
                break;
            }
        }
        if (!flag) return;

        DC(start, mid-1);
        DC(mid+1, end);
    }
} // end of class