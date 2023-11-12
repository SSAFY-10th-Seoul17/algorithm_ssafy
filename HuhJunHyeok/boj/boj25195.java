import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * [boj] 25195. Yes or yes
 */
public class boj25195 {
    /**
     * N: 정점의 수, M: 간선의 수, S: 팬클럽 곰곰이가 존재하는 정점의 수
     */
    static int N, M, S;
    static ArrayList<Integer>[] edges;
    static HashSet<Integer> fans = new HashSet<>();
    /**
     * flag: 팬클럽 곰곰이를 만나지 않고 이동하는 방법의 존재 유무
     */
    static boolean flag;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            edges[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        while(S-- > 0) {
            fans.add(Integer.parseInt(st.nextToken()));
        }

        dfs(1);

        System.out.println(flag ? "yes" : "Yes");
    }

    public static void dfs(int nowVertex) {
        if(flag || fans.contains(nowVertex)) {
            return;
        }

        if(edges[nowVertex].isEmpty()) {
            flag = true;
            return;
        }

        for(int nextVertex: edges[nowVertex]) {
            dfs(nextVertex);
        }
    }
}
