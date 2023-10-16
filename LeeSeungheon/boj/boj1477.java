import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class boj1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 휴게소 개수
        int M = Integer.parseInt(st.nextToken()); // 더 지을 개수
        int L = Integer.parseInt(st.nextToken()); // 고속도로 총
        st = new StringTokenizer(br.readLine());

        int[] map = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        map[0] = 0;
        map[N + 1] = L;
        System.out.println(solve(map, M));

    }

    private static int solve(int[] map, int M) {
        Arrays.sort(map);

        int[] diff = IntStream.range(1, map.length)
                .map(i -> map[i] - map[i - 1])
                .toArray();

        int low = 0;
        int high = map[map.length - 1];

        while (low <= high) {

            int mid = (low + high) / 2;

            if(mid == 0){
                low++;
                continue;
            }

            int sum = Arrays.stream(diff).map(value -> value % mid == 0 ? value / mid : value / mid + 1).sum();

            if (sum > M + diff.length) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}
