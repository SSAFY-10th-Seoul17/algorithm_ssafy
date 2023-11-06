package study10월5주차월;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj17298_오큰수 {
    static int n;
    static Deque<Integer> idxs = new LinkedList<>();
    static int[] arr;
    static int[] ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        ans = new int[n];
        Arrays.fill(ans, -1);

        st = new StringTokenizer(br.readLine());
        int i = 0;
        while (st.hasMoreTokens()) {
            arr[i] = Integer.parseInt(st.nextToken());
            while (!idxs.isEmpty() && arr[idxs.peekLast()] < arr[i]) {
                int idx = idxs.pollLast();
                ans[idx] = arr[i];
            }
            idxs.addLast(i++);
        }

        StringBuilder sb = new StringBuilder();
        for (int a : ans)
            sb.append(a).append(' ');
        System.out.println(sb);
    }
}