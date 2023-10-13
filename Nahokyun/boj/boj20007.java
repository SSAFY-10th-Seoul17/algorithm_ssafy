package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj20007 {

    private static ArrayList<Node>[] al;
    private static int n;
    private static int x;
    private static int[] distance;
    private static boolean flag;

    /*
    떡돌리기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //집 개수(n-1)까지 있음
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        //하루 한계치
        x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());//시작점
        int[][] arr = new int[n][n];

        al = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            al[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            al[a].add(new Node(b, c));
            al[b].add(new Node(a, c));
        }

        System.out.println(dijk(y));

    }

    private static int dijk(int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(y, 0));
        boolean[] visited = new boolean[n];
        distance = new int[n];

        visited[y] = true;
        Arrays.fill(distance, 100_000_001);
        distance[y] = 0;


        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            visited[cur.destHouseNum] = true;

            for (Node cmp : al[cur.destHouseNum]) {
                if (!visited[cmp.destHouseNum] && distance[cmp.destHouseNum] > distance[cur.destHouseNum] + cmp.dist) {
                    distance[cmp.destHouseNum] = distance[cur.destHouseNum] + cmp.dist;
                    pq.add(new Node(cmp.destHouseNum, distance[cmp.destHouseNum]));
                }
            }

        }
        //원점에서 각  지점별 최소거리 구하기 완료

        Arrays.sort(distance);

        int sum = 0;
        int day = 1;

        if (distance[n - 1] == 100_000_001) {
            return -1;
        }

        for (int i = 1; i < n; i++) {
            if (2 * distance[i] > x) {
                flag = true;
                break;
            }
            if (sum + 2 * distance[i] <= x) {
                sum += 2 * distance[i];
            } else {
                day++;
                sum = 0;
                i--;
            }
        }
        if (flag) {
            return -1;
        }
        return day;

    }

    static class Node implements Comparable<Node> {
        int destHouseNum;
        int dist;

        public Node(int destHouseNum, int dist) {
            this.destHouseNum = destHouseNum;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
