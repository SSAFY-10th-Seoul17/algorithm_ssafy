import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] parent;
    static List<List<int[]>> tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());  // 정점 개수
        parent = new int[N-1];  // 부모 정점 번호
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1 ; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());

        long sum = Long.parseLong(st.nextToken());
        sb.append(sum).append('\n');

        for (int i = 0; i < N-1; i++) {
            tree.get(parent[i]-1).add(new int[] {i+1, Integer.parseInt(st.nextToken())});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(o->o[1])));
        for (int[] v: tree.get(0)) {
            pq.offer(v);
        }

        int cnt = 1;
        while (cnt++ < N) {
            int[] v = pq.poll();
            sum += v[1];
            sb.append(sum).append('\n');
            for (int[] w: tree.get(v[0])) {
                pq.offer(w);
            }
        }

        System.out.println(sb);

    }
}
