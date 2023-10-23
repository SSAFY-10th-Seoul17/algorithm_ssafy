import java.io.*;
import java.util.*;

/**
 * dfs로 완탐을 하려고 했으나, 시간초과가 났습니다.
 * 중복되는 계산이 있는 걸 확인한 후 메모이제이션을 통해 시간초과 문제를 해결하였습니다.
 * maxresult 값을 초기화 해주는 작업을 밖에서 해서 계속 틀렸었습니다 ,,
 */
public class BOJ14699_관악산등산 {
    static int N,M;
    static int[] heights;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heights = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (heights[from] > heights[to]){
                map.get(to).add(from);
            } else {
                map.get(from).add(to);
            }
        }

        memo = new int[N+1];
        for (int i=0; i<=N; i++){
            memo[i] = -1;
        }

        for (int i=1; i<=N; i++){
            maxresult = dfs(i);
            sb.append(maxresult).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int maxresult;
    static int[] memo;
    private static int dfs(int shim){
        ArrayList list = map.get(shim);

        if (list.size() == 0){
            return 1;
        }
        if (memo[shim] != -1){
            return memo[shim];
        }

        maxresult = 0; // *
        for (int i=0; i<list.size(); i++){
            maxresult = Math.max(maxresult, dfs((int)list.get(i)));
        }
        return memo[shim] = maxresult+1;
    }
}
