import java.util.*;
import java.io.*;

/**
 * bfs를 이용하여 풀었습니다.
 * 주의해야할 부분은 bfs에서의 if 문 입니다. visited 배열을 활용해서 방문처리와 점수 계산을 해주었는데,
 * bfs를 출발한 처음 점인 index 점을 다시 방문하려할 때를 피해주어야 원활한 점수 계산이 될 수 있습니다.
 */
public class BOJ2660_회장뽑기 {
    static int N,minScore, huboNum;
    static List<Integer> hubos = new ArrayList<>();
    static List<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (from == -1 && to == -1){
                break;
            }

            list.get(from).add(to);
            list.get(to).add(from);
        }

        minScore = Integer.MAX_VALUE;
        huboNum = 0;

        for (int i=1; i<=N; i++){
            visited = new int[N+1];
            bfs(i);
        }

        sb.append(minScore).append(" ").append(huboNum).append("\n");
        for (int i=0; i<hubos.size(); i++){
            sb.append(hubos.get(i)+" ");
        }
        System.out.println(sb.toString());
    }
    static int[] visited;
    private static void bfs(int index){
       Queue<Integer> q = new LinkedList<>();
       q.offer(index);

       while(!q.isEmpty()){
           int cur = q.poll();

           ArrayList li = list.get(cur);
           for (int i=0; i<li.size(); i++){
               int next = (int)li.get(i);
               if (visited[next] == 0 && next != index ){
                   visited[next] = visited[cur]+1;
                   q.offer(next);
               }
           }
       }

       int max = 0;
       for (int i=1; i<=N; i++){
           max = Math.max(max, visited[i]);
       }
        if (minScore > max){
                minScore = max;
                huboNum = 1;
                hubos.clear();
                hubos.add(index);
            } else if (minScore == max){
                huboNum++;
                hubos.add(index);
            }
    }
}
