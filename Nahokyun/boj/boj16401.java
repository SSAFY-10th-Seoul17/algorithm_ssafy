package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
과자 나눠주기
 */
public class boj16401 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());//조카의 수
        int n = Integer.parseInt(st.nextToken());//과자의 수

        st = new StringTokenizer(br.readLine());
        int[] snack = new int[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
            sum += snack[i];
        }
        Arrays.sort(snack);

        long start = 1;//가장 작은 길이의 과자
        long finish = sum / m;//총합을 조카의 수로 나눈 수
        long answer = 0;
        while (start <= finish) {
            long count = 0;
            long mid = (start + finish) >> 1;//주고자 하는 과자길이

            for (int i = 0; i < n; i++) {
                count += snack[i] / mid;//각 과자들을 주고자 하는 과자길이로 나누면 몇개 나올지 count
            }

            if (count >= m) {//조카의 수 이상의 인원에게 나눠줄 수 있을때
                start = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                finish = mid - 1;
            }
        }
        System.out.println(answer);

    }
}
