import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj20007 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Home>> map = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
        }

        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()); // 거리
        int Y = Integer.parseInt(st.nextToken()); // 집

        for (int line = 0; line < M; line++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            map.get(A).add(new Home(B, dis));
            map.get(B).add(new Home(A, dis));
        }

        solve(map, X, Y);
    }

    private static void solve(ArrayList<ArrayList<Home>> map, int x, int y) {

        PriorityQueue<Home> pq = new PriorityQueue<>();
        pq.offer(new Home(y, 0));

        boolean[] visited = new boolean[map.size()];


        while (!pq.isEmpty()) {

            Home curHome = pq.poll();

            for (Home nextHome : map.get(curHome.index)) {

                if (visited[nextHome.index]) {
                    continue;
                }
                visited[nextHome.index] = true;
                pq.offer(new Home(nextHome.index, curHome.distance + nextHome.distance));
            }
        }


    }

    private static class Home implements Comparable<Home> {

        int index;
        int distance;

        @Override
        public int compareTo(Home o) {
            return Integer.compare(distance, o.distance);
        }

        public Home(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
