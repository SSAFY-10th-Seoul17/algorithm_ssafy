package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj27977 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] charge = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        charge[0] = 0;
        for (int i = 1; i <= N; i++) {
            charge[i] = Integer.parseInt(st.nextToken());
        }
        charge[N + 1] = L;

        int left = 0;
        int right = L + 1;

        int mid = 0;

        while (left + 1 < right) {
            mid = (left + right) >> 1;

            if (check(mid, charge, K)) {// mid값 늘려야하면 false
                right = mid;
            } else {
                left = mid;
            }

        }
        System.out.println(right);

    }

    private static boolean check(int mid, int[] charge, int k) {
        int start = 0;
        int chargeCount = 0;
        int curDist = 0;
        for (int i = 1; i < charge.length; i++) {
            if (charge[i] - charge[i - 1] > mid) {// 간격이 미드보다 크면 false => mid값 늘려야함
                return false;
            }
            curDist = charge[i] - start;
            if (curDist <= mid) {
                continue;
            }

            start = charge[i - 1];
            i--;
            curDist = 0;
            chargeCount++;
        }
        if (chargeCount <= k) {// 방문하는 충전소가 적음 => mid값 줄여야함
            return true;
        }

        return false;// 방문하는 충전소가 더 많음 => mid값 늘려야함
    }

}
