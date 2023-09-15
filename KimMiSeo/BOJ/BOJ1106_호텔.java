import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 홍보할 수 있는 도시 n개의 투자금액, 그에 따른 얻을 수 있는 고객의 수가 주어졌으므로 고객이 i명일 때의 투자한 최소 금액을 계속 구해서 최종적으로 C명일 때를 출력하면 되는 dp문제라고 생각했습니다.
 * 각각의 도시를 선택해보는 모든 경우의 수를 다 따져준다고 생각하면 될 것 같습니다.
 * 1. 첫번째 도시를 통해 초기값을 설정합니다.
 * 이때, 해당 도시의 1배수 투자금액에 따른 고객수의 배수에 해당하는 부분에만 업데이트를 해주면 안됩니다.
 * 예를 들어 2원을 들여서 고객 2명을 늘어나게 할 수 있다고 가정하면, 고객을 1명만 얻고 싶더라도 2원을 사용해야 합니다.
 *
 * 2. 두 번째 도시 ~ N번째 도시까지 반복문을 돌면서 j명의 고객을 얻기 위하여 사용해야 하는 최소 금액을 갱신해줍니다.
 *
 */
public class BOJ1106_호텔 {
    static int C, N; // 충족해야하는 고객수, 총 도시수
    static int[][] cities; // 도시 홍보비용, 얻을 수 있는 고객의 수
    static int[][] memo; // i도시에서 고객이 j명일 때 투자해야하는 최소 금액

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cities = new int[N][2];
        memo = new int[N][C + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        } // cities

        // city 0 - 0번째 도시로 memo배열 초기화
        int customer0 = cities[0][1];
        int money0 = cities[0][0];

        for (int i = 1, index = 1; i <= C; i++) {
            memo[0][i] = money0 * index;
            if (i % customer0 == 0) {
                index++;
            }
        }

        for (int i = 1; i < N; i++) { // 1번째 도시 ~ N번째 도시
            int customer = cities[i][1];
            int money = cities[i][0];

            for (int j = 1; j <= C; j++) {
                if (j - customer >= 0) {
                    memo[i][j] = Math.min(memo[i - 1][j], memo[i][j - customer] + money);
                } else {
                    memo[i][j] = Math.min(memo[i - 1][j], money);
                }
            }
        }

        System.out.println(memo[N - 1][C]);
    }
}
