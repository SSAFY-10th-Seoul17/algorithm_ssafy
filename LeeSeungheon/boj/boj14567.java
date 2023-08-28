import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj14567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }
        int[] solve = solve(map);
        for (int i = 1; i < solve.length; i++) {
            sb.append(solve[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static int[] solve(ArrayList<ArrayList<Integer>> map) {
        int[] dp = new int[map.size()];
        Arrays.fill(dp, 1);

        for (int cur = 1; cur < map.size(); cur++) {

            for (int next : map.get(cur)) {
                dp[next] = Math.max(dp[next] , dp[cur] + 1);

            }
        }
        return dp;
    }
}
