package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
먹을 것인가 먹힐 것인가
 */
public class boj7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int aSize = Integer.parseInt(st.nextToken());
            int bSize = Integer.parseInt(st.nextToken());
            int[] arrA = new int[aSize];
            int[] arrB = new int[bSize];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < aSize; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }//A그룹 입력 종료
            Arrays.sort(arrA);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < bSize; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }//B그룹 입력 종료
            Arrays.sort(arrB);

            int count = 0;
            for (int i = 0; i < aSize; i++) {
                for (int j = bSize - 1; j >= 0; j--) {
                    if (arrA[i] > arrB[j]) {//arrB의 뒤에서부터 검색하는데 arrA[i]가 더 크면 뒤는 당연히 arr[i]가 더큼
                        count += j + 1;
                        break;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
