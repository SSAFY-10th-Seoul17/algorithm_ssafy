import java.io.*;
import java.util.*;

/**
 * 스터디원들의 도움을 받아 bfs를 사용하여 풀 수 있었습니다 ,,
 */
public class BOJ13265_색칠하기 {
    static int N,M;
    static int[] circles;
    static boolean flag;
    static List<ArrayList> graph;
    public static void main(String[] args) throws Exception {
        // 여러 동그라미, 동그라미 2개 연결하는 직선들만으로 그림을 그리고, 연결된 동그라미는 서로 다르게 색을 칠하고자 함
        // 2가지 색상으로 색칠이 가능한지 여부 ,
        // 동그라미 개수 n 직선 개수 m , 직선에 대한 정보
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            circles = new int[N+1];
            graph = new ArrayList<>();
            flag = true;

            // 초기화
            for (int i=0; i<=N; i++){
                graph.add(new ArrayList());
            }

            for( int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            for(int i=1; i<=N; i++){
                if (circles[i] == 0) {
                    bfs(i);
                }
            }

            if (flag){
                sb.append("possible\n");
            }else{
                sb.append("impossible\n");
            }
        } // end of tc
        System.out.println(sb.toString());
    }
    private static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()){
            int cur = q.poll();

            if (circles[cur] == 0){
                circles[cur] = 1;
            }

            ArrayList list = graph.get(cur);
            for (int i=0; i<list.size(); i++){
                int next = (int)list.get(i);

                if (circles[next] == 0){
                    circles[next] = circles[cur]==1 ? 2 : 1;
                    q.offer(next);
                } else{ // 색칠되어있을 때
                    if (circles[cur] == circles[next]){
                        flag = false;
                        return;
                    }
                }
            }
        }
    }
}
