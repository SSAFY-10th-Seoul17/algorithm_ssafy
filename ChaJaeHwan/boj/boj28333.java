import java.io.*;
import java.util.*;

public class boj28333 {

    static class Node {
        int idx;
        List<Integer> nodes;

        public Node(int idx, List<Integer> list) {
            this.idx = idx;
            nodes = new ArrayList(list);
        }
    }

    static int T, N, M;
    static ArrayList<Integer>[] cities;
    static Set<Integer> set;
    static boolean[] visited;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            cities = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                cities[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                cities[a].add(b);
            }


            set = new HashSet<>();
            bfs();
            List<Integer> list = new ArrayList(set);
            list.sort(Integer::compareTo);
            for (Integer i : list) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }



    static void bfs() {
        visited = new boolean[N + 1];
        ArrayDeque<Node> que = new ArrayDeque<>();
        List<Integer> initial = new ArrayList();
        initial.add(1);
        que.add(new Node(1, initial));
        visited[1] = true;
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        while (!que.isEmpty()) {
            int qsize = que.size();
            cnt += 1;
            for (int i = 0; i < qsize; i++) {
                Node poll = que.poll();
                int curIdx = poll.idx;
                for (int j = 0; j < cities[curIdx].size(); j++) {
                    int nextIdx = cities[curIdx].get(i);
                    if (nextIdx == N) {
                        min = Math.min(min, cnt);
                        Node next = new Node(nextIdx, poll.nodes);
                        next.nodes.add(nextIdx);
                        set.addAll(next.nodes);
                        break;
                    }
                    if (!visited[nextIdx]) {
                        Node next = new Node(nextIdx, poll.nodes);
                        next.nodes.add(nextIdx);
                        que.add(next);
                        visited[nextIdx] = true;
                    }
                }
            }
        }
    }

}
