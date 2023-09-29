import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 20917. 사회적 거리 두기
 */
public class boj20917 {
    /**
     * T: 테스트 케이스의 수, n: 콘센트의 수, s: 선택하는 콘센트의 수, maxD: 가장 가까운 두 좌석 사이의 거리의 최댓값
     */
    static int T, n, s, maxD;
    /**
     * sockets: 콘센트 배열
     */
    static int[] sockets;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            sockets = new int[n];
            for(int i = 0; i < n; i++) {
                sockets[i] = Integer.parseInt(st.nextToken());
            }

            parametricSearch();

            sb.append(maxD).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void parametricSearch() {
        Arrays.sort(sockets);

        int low = 1;
        int high = sockets[sockets.length - 1];
        while(low <= high) {
            int mid = (low + high) / 2;

            if(getScoketCount(mid) < s) {
                high = mid - 1;
                maxD = high;
            } else {
                low = mid + 1;
            }
        }
    }

    public static int getScoketCount(int dist) {
        int count = 1;
        int leftSocket = sockets[0];

        for(int i = 1; i < n; i++) {
            int rightSocket = sockets[i];
            if(rightSocket - leftSocket >= dist) {
                count++;
                leftSocket = sockets[i];
            }
        }

        return count;
    }
}
