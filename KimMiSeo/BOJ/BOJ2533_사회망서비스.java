import java.io.*;
import java.util.*;

/**
 * 얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다.
 * 라는 구문에서 dp를 떠올릴 수 있는 훈련이 필요하다고 생각됩니다!
 */

public class BOJ2533_사회망서비스 {
    static int N;
    static int[][] dp;
    static boolean[] visited;
    static List<ArrayList> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i=0; i<N+1; i++){
            list.add(new ArrayList<>());
        }

        for (int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list.get(from).add(to);
            list.get(to).add(from);
        }

        dp= new int[N+1][2];
        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur){
        dp[cur][0] = 0; // 얼리어답터가 아님
        dp[cur][1] = 1; // 얼리어답터가 맞음

        ArrayList friends = list.get(cur);
        for (int i=0; i<friends.size(); i++){
            int friend = (Integer) friends.get(i);
            if (!visited[friend]){
                visited[friend] = true;
                dfs(friend);
                dp[cur][0] += dp[friend][1];
                dp[cur][1] += Math.min(dp[friend][0] , dp[friend][1]);
            }
        }
    }
}
