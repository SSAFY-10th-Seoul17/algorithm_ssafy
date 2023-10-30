import java.io.*;
import java.util.*;

// 무게가 서로 다른 n개의 물건 , 1~n
// 각 물건에 대해서 그 물건과의 비교 결과를 알 수 없는 물건의 개수

// 5 <= 물건의 개수 n <= 100
// 0<= 물건 쌍의 개수 m <= 2000
public class BOJ10159_저울 {
    // 큰 애들로 이어진 그래프, 작은 애들로 이어진 그래프 2개로 운영하기
    // visited 배열은 같은 그래프 사용하기
    // 이어진 개수를 구해서 최종 개수에서 빼주기!!
    static ArrayList<ArrayList<Integer>> biggerList, smallerList;
    static boolean[] visited;
    static int N,M;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        biggerList = new ArrayList<>();
        smallerList = new ArrayList<>();

        for (int i=0; i<=N; i++){
            biggerList.add(new ArrayList<>());
            smallerList.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());
            // System.out.println(big+" "+small);
            biggerList.get(big).add(small);
            smallerList.get(small).add(big);
        }

        for (int i=1; i<=N; i++){ // 각각 정점
            visited = new boolean[N+1];
            visited[i] = true;
            cnt = 0;
            dfs(i, biggerList);
            dfs(i, smallerList);
            System.out.println(N - cnt -1);
        }
    }

    private static void dfs(int cur, ArrayList<ArrayList<Integer>> list){
        ArrayList<Integer> links = list.get(cur);
        int len = links.size();

        for (int i=0; i<len; i++){
            int link = links.get(i);
            if (!visited[link]){ // 방문하지 않았으면
                visited[link] = true;
                cnt++;
                dfs(link, list);
            }
        }

    }
}
