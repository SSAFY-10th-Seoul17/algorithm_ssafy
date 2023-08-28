import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [boj] 1365. 꼬인 전깃줄
 */
public class boj1365 {
    /**
     * N: 전봇대의 개수 1 <= N <= 100_000. 따라서, 시간복잡도에 따라 O[N^2]인 lis dp는 불가.
     * cutLineCnt: 잘라내야 할 전선의 최소 개수. N - LIS 알고리즘을 통해 도출된 가장 긴 증가하는 부분 수열의 길이
     */
    static int N, cutLineCnt;
    static ArrayList<Integer> lisList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            int lineLength = Integer.parseInt(st.nextToken());
            int idx = lisBinarySearch(lineLength);
            if(idx >= lisList.size()) {
                lisList.add(lineLength);
            } else {
                lisList.set(idx, lineLength);
            }
        }

        cutLineCnt = N - lisList.size();
        System.out.println(cutLineCnt);
    }

    /**
     * lineLength의 값이 lisList에 어느 위치에 add 될 수 있거나 어느 index의 값을 변경하는지를 binary search를 통해 확인.
     */
    public static int lisBinarySearch(int lineLength) {
        int high = lisList.size();
        int low = 0;

        while(low < high) {
            int mid = (low + high) / 2;

            if(lisList.get(mid) < lineLength) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }
}
