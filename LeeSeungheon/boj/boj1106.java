import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1106 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        City[] cities = new City[N];

        for (int num = 0; num < N; num++) {
            st = new StringTokenizer(br.readLine());

            cities[num] = new City(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(solve(C, cities));
    }

    private static int solve(int C, City[] cities) {

        int[] dp = new int[C + 1]; // cost 저장
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int city = 0; city < cities.length; city++) {
            for (int people = C; people >= 0; people--) {


                if (people % cities[city].people == 0) {
                    dp[people] = Math.min(dp[people], people / cities[city].people * cities[city].cost);
                }

                int cur = 0;
                while (people - cities[city].people * ++cur >= 0) {
                    if (dp[people - cities[city].people * cur] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[people] = Math.min(dp[people], dp[people - cities[city].people * cur] + cur * cities[city].cost);
                }
            }

            for (int i = 1; i < cities[city].people && i <= C; i++) {
                if ( dp[C - i] ==  Integer.MAX_VALUE) {
                    continue;
                }
                dp[C] = Math.min(dp[C], dp[C - i] + cities[city].cost);
            }
        }
        return dp[C];
    }

    private static class City {

        int cost;
        int people;

        public City(int cost, int people) {
            this.cost = cost;
            this.people = people;
        }
    }


}
