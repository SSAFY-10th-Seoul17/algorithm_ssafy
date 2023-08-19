package boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj13397 {

    private static int[] arrs;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arrs = new int[n];

        st = new StringTokenizer(br.readLine());

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arrs[i] = Integer.parseInt(st.nextToken());
            max = arrs[i] > max ? arrs[i] : max;
        }

        // 초기화
        int start = -1;
        int end = max;
        int mid = 0;

        if (m == n) {
            System.out.println(0);
            return;
        }

        while (start < end) {
            mid = (start + end) >> 1;
            if (check(mid) <= m) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        // 배열 돌면서 해당 차이 이내로 가능한지 확인 ->해당 차이 벗어나면 그 직전 인덱스까지
        // 가능한집합 개수 체크 후 m과 같으면 리턴
        // m보다 크게 나오면 start값 증가 차이가 크면 적은 집합이 나옴
        // m보다 작게 나오면 end값 감소 차이가 적어야 많은 집합이 나옴

        System.out.println(end);

    }// end of main

    private static int check(int cmp) {
        int count = 0;
        int rMax = Integer.MIN_VALUE;
        int rMin = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int cur = arrs[i];

            rMax = rMax < cur ? cur : rMax;
            rMin = rMin > cur ? cur : rMin;

            if (rMax - rMin > cmp) {// 차이를 벗어낫을때
                count++;
                rMax = cur;
                rMin = cur;
                i--;
            }
            if (count > m) {
                return 20000;
            }
        }
        return count + 1;
    }
}


