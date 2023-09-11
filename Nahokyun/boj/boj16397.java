package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16397 {
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        // N은 시작점
        // T는 버튼 누르는 최대횟수
        // G는 도착점

        arr = new int[100000];

        Arrays.fill(arr, 100005);

        find(N, T, G);

        System.out.println(arr[G] != 100005 ? arr[G] : "ANG");
    }

    private static void find(int start, int maxPush, int des) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(start);
        arr[start]=0;
        int pushCount = 0;
        while (!q.isEmpty()) {
            pushCount++;
            Queue<Integer> tmp = new ArrayDeque<>();
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == des)
                    return;

                int cmp = cur + 1;//조건1
                if (cmp < 100000) {//조건 3
                    if (arr[cmp] > pushCount) {
                        arr[cmp] = pushCount;
                        tmp.add(cmp);
                    }
                }

                cmp = cur * 2;
                if(cmp>=100000) {//조건4
                    continue;
                }

                cmp = decreaseTop(cmp);
                if(cur==0) {//2배하고 1 감소할때 cur이 0이였으면 넘어감
                    continue;
                }

                if (arr[cmp] > pushCount) {
                    arr[cmp] = pushCount;
                    tmp.add(cmp);
                }
            }
            q = tmp;
            if (maxPush == pushCount) {
                return;
            }
        }
    }

    private static int decreaseTop(int cmp) {
        int div = 10;
        while (cmp / div !=0) {
            div *= 10;
        }
        return cmp - (div / 10);
    }

}

