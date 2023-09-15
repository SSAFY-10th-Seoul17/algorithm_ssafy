import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 12761. 돌다리
 */
public class boj12761 {
    /**
     * A, B: 스카이 콩콩의 힘 (2 <= A, B <= 30)
     * N: 동규의 현재 위치, M: 주미의 현재 위치 (0 <= N, M <= 100_000)
     * count: 이동 횟수
     */
    static int A, B, N, M, count;
    /**
     * dl: 배수를 제외한 delta값.
     */
    static int[] dl;
    /**
     * visitedWithCount: 방문 체크 + 횟수 카운팅
     */
    static int[] visitedWithCount = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(N == M) {
            System.out.println(0);
        } else {
            dl = new int[] {-1, 1, -A, A, -B, B};
            bfs();
            System.out.println(count);
        }

    }

    public static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        visitedWithCount[N] = 1;

        while(!queue.isEmpty()) {
            int nowLocation = queue.poll();

            if(nowLocation == M) {
                count = visitedWithCount[nowLocation] - 1;
                return;
            }

            int nowMultipleA = nowLocation * A;
            if(nowMultipleA <= 100_000 && visitedWithCount[nowMultipleA] == 0) {
                queue.offer(nowMultipleA);
                visitedWithCount[nowMultipleA] = visitedWithCount[nowLocation] + 1;
            }

            int nowMultipleB = nowLocation * B;
            if(nowMultipleB <= 100_000 && visitedWithCount[nowMultipleB] == 0) {
                queue.offer(nowMultipleB);
                visitedWithCount[nowMultipleB] = visitedWithCount[nowLocation] + 1;
            }

            for(int i = 0; i < 6; i++) {
                int nextLocation = nowLocation + dl[i];

                if(nextLocation >= 0 && nextLocation <= 100_000 && visitedWithCount[nextLocation] == 0) {
                    queue.offer(nextLocation);
                    visitedWithCount[nextLocation] = visitedWithCount[nowLocation] + 1;
                }
            }
        }
    }
}
