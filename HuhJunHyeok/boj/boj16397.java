import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 16397. 탈출
 */
public class boj16397 {
    static class NumCount {
        int num, count;

        public NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    /**
     * N: 시작 숫자, T: 버튼을 누를 수 있는 최대 횟수, G: 만드려는 숫자
     * N (0 ≤ N ≤ 99,999), T (1 ≤ T ≤ 99,999), G (0 ≤ G ≤ 99,999)
     * minCount: 탈출에 필요한 최소의 버튼 횟수
     */
    static int N, T, G, minCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        System.out.println(bfs() ? minCount : "ANG");
    }

    public static boolean bfs() {
        boolean[] visited = new boolean[100_000]; // 99_999를 넘으면 탈출 실패이기 때문.
        visited[N] = true;

        Queue<NumCount> queue = new ArrayDeque<>();
        queue.offer(new NumCount(N, 0));

        while(!queue.isEmpty()) {
            NumCount now = queue.poll();
            int nowNum = now.num;
            int nowCount = now.count;

            if(nowCount > T) { // 버튼을 누를 수 있는 횟수 초과
                return false;
            }

            if(nowNum == G) { // 만들려는 숫자가 만들어짐.
                minCount = nowCount;
                return true;
            }

            // case 1: 버튼 A를 눌러 N이 +1.
            if(nowNum < 99_999) {
                int plusOne = nowNum + 1;
                if(!visited[plusOne]) {
                    visited[plusOne] = true;
                    queue.offer(new NumCount(plusOne, nowCount + 1));
                }
            }

            // case 2: 버튼 B를 눌러 N * 2. 이후에 0이 아닌 가장 높은 자릿수의 숫자가 1 줄어듦. 단, N이 0이면 불변.
            if(nowNum != 0 && nowNum < 50_000) {
                int twice = nowNum * 2;

                int maxDigitNumIndex = checkMaxDigitNumIndex(twice);
                if(maxDigitNumIndex != -1) {
                    twice -= (int) Math.pow(10, maxDigitNumIndex);

                    if(!visited[twice]) {
                        visited[twice] = true;
                        queue.offer(new NumCount(twice, nowCount + 1));
                    }
                }
            }
        }
        return false;
    }

    /**
     * B 버튼을 누른 경우 2를 곱한 숫자에서 0이 아닌 최대 자릿수의 인덱스를 구함. 인덱스는 일의 자리부터 0으로.
     * @param num
     * @return
     */
    public static int checkMaxDigitNumIndex(int num) {
        for(int i = 1; i < 7; i++) { // num의 최대 자릿수가 5이기 때문.
            if((num % (int) Math.pow(10, i)) == num) {
                return i - 1;
            }
        }
        return -1;
    }
}
