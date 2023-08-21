import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 13397. 구간 나누기 2
 */
public class boj13397 {
    static int N, M, maxArrNum, minOfMax;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxArrNum = maxArrNum > arr[i] ? maxArrNum : arr[i]; // maxArrNum의 최초 값은 0.
        }

        parametricSearch();

        System.out.println(minOfMax);
    }

    /**
     * binarySearch를 이용한 parmetricSearch(매개 변수 탐색) 사용.
     * 구간의 수가 M개 이하인 범위에서 최댓값을 낮추면서 최댓값의 최솟값을 찾아나간다.
     */
    public static void parametricSearch() {
        int low = 0;
        int high = maxArrNum;

        while(low < high) {
            int mid = (low + high) / 2;

            if(getMaxMinusMinBiggerThenMid(mid) <= M) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        minOfMax = high;
    }

    public static int getMaxMinusMinBiggerThenMid(int mid) {
        int min = Integer.MAX_VALUE; // 배열 원소 값의 범위가 1~10000 이므로 100001으로 해도 무방.
        int max = Integer.MIN_VALUE; // 배열 원소 값의 범위가 1~10000 이므로 0으로 해도 무방.
        int count = 1; // mid 값보다 큰 최댓값이 포함된 구간의 수. 최초 전체 배열은 무조건 mid 해당하는 구간이므로 1이 시작 값.

        for(int i = 0; i < N; i++) {
            min = min < arr[i] ? min : arr[i];
            max = max > arr[i] ? max : arr[i];

            if(max - min > mid) {
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                count++;
                i--; // 구간은 하나 이상의 연속된 수로 이루어짐. 따라서 해당 구간 재조사 필요.
            }
        }
        return count;
    }
}
