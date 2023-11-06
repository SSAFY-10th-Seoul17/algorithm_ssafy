package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //입력종료


        Stack<Integer> s = new Stack<>();
        s.add(-1);
        s.add(arr[n - 1]);
        result[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) {
            Stack<Integer> tmp = new Stack<>();

            while (!s.isEmpty()) {
                int temp = s.pop();
                if (temp > arr[i]) {
                    result[i] = temp;
                    tmp.add(temp);
                    break;
                }
                tmp.add(temp);
            }//자기보다 큰거 나올때까지 pop

            if (s.isEmpty()) {
                result[i] = -1;
                s.add(-1);
                s.add(arr[i]);
                continue;
            }
            while (!tmp.isEmpty()) {
                int cur = tmp.pop();
                if (cur > arr[i]) {
                    s.add(cur);
                }
            }

            s.add(arr[i]);


        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb);
    }
}
