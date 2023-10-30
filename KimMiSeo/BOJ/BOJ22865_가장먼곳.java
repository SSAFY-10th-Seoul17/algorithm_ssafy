import java.util.*;
import java.io.*;
// n개의 땅 , abc 이 친구들이 살고있는 집으로부터 가장 먼 곳
// 선택할 집 ~ 가장 가까운 친구집까지의 거리 기준
// 가장 먼곳을 구해보자.

class Node{
    int idx;
    int cost;

    Node(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }
}
public class BOJ22865_가장먼곳 {
    static int N,M;
    static int[] abc,a,b,c;
    static ArrayList<ArrayList<Node>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        abc = new int[3];
        graph = new ArrayList<>();
        for (int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<3; i++){
            abc[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to,dis));
            graph.get(to).add(new Node(from,dis));
        }

        a = new int[N+1];
        b = new int[N+1];
        c = new int[N+1];
        solve(a, abc[0]);
        solve(b, abc[1]);
        solve(c, abc[2]);

        int result = -1;
        int resultIdx = -1;

        for (int i=1; i<=N; i++){
            int aa = a[i];
            int bb = b[i];
            int cc = c[i];

            int dis = Math.min(Math.min(aa,bb), cc);
            if (result < dis){
                result = dis;
                resultIdx = i;
            } else if ( result == dis && resultIdx > i){
                resultIdx = i;
            }
        }
        System.out.println(resultIdx);
    }

    private static void solve(int[] distance, int start){
        for (int i=0; i<N+1; i++){
            distance[i] = Integer.MAX_VALUE; // 최소거리 정보 최소화
        }

        PriorityQueue<Node> q = new PriorityQueue<>(
                (o1,o2)-> Integer.compare(o1.cost, o2.cost));
        q.offer(new Node(start, 0));
        distance[start] = 0; // 출발지점 - 0으로 초기화
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(distance[cur.idx] < cur.cost){
                continue;
            }

            for (int i=0; i<graph.get(cur.idx).size(); i++){
                Node next = graph.get(cur.idx).get(i);
                if (distance[next.idx] > cur.cost + next.cost){
                    distance[next.idx] = cur.cost + next.cost;
                    q.offer(new Node(next.idx, distance[next.idx]));
                }
            }

        }

    }
}
