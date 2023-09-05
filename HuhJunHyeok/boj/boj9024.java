import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 9024. 두 수의 합
 */
public class boj9024 {
    /**
     * t: 테스트 케이스의 수, n: S에 속하는 서로 다른 정수의 수, k: S에 속하는 서로 다른 두 개의 정수의 합이 가까워져야 하는 대상 값
     * count: k에 가까운 두 정수의 합의 조합의 수, minGap: k와 두 정수의 합의 최솟값
     */
    static int t, n, k, count, minGap;
    /**
     * numArr: 문제에서 주어지는 서로 다른 정수가 속한 S.
     */
    static int[] numArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            numArr = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < n; i++) {
                numArr[i] = Integer.parseInt(st.nextToken());
            }

            binarySearch();

            sb.append(count).append("\n");
        }
        System.out.print(sb.toString());
    }

    /**
     * binary search + two pointer 이용
     */
    public static void binarySearch() {
        Arrays.sort(numArr);
        minGap = Integer.MAX_VALUE;
        count = 0;

        int low = 0;
        int high = n - 1;

        while(low < high) {
            int sum = numArr[low] + numArr[high];
            int gap = Math.abs(sum - k);

            if(minGap == gap) {
                count++;
            } else if(minGap > gap) {
                minGap = gap;
                count = 1;
            }

            if(sum >= k) {
                high--;
            } else {
                low++;
            }
        }
    }
}
