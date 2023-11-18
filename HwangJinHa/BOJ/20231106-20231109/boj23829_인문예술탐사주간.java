package study11월2주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * sort해줘야됨... 상식적으로 i번째 나무면 sort돼있다는 말인데
 *      컴공은 상식을 기대하면 안되는듯
 * 누적합? 합 곱 등이 거듭해서 나오는 문제는 항상 long 조심
 *
 * 0. 범위 벗어나는 것 항상 조심
 * 1. 작은 쪽의 점수 합 고려
 * 2. 큰 쪽의 점수 합 고려
 */
public class boj23829_인문예술탐사주간 {
    static int n, q;
    static long[] p;
    static long[] sums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = new long[n];
        sums = new long[n + 1];
        for (int i = 0; i < n; i++)
            p[i] = Long.parseLong(st.nextToken());
        Arrays.sort(p);
        for (int i = 1; i <= n; i++)
            sums[i] = sums[i-1] + p[i-1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            long target = Long.parseLong(br.readLine());
            int idx = binSearch(target);
            long ans = 0;
            // 0 이상 idx 미만
            if (idx > 0) {
                ans += target * idx - sums[idx];
            }
            // idx 이상 n 이하
            if (idx != n) {
                ans += sums[n] - sums[idx] - target * (n-idx);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int binSearch(long target) {
        int l = 0;
        int r = n-1;
        int m;
        while (l < r) {
            m = (l + r) / 2;
            if (p[m] < target)
                l = m + 1;
            else if (p[m] > target)
                r = m - 1;
            else
                return m;
        }
        return (p[l] >= target)? l : l + 1;
    }
}