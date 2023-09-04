import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  private static ArrayList<ArrayList<Room>> graph;
  private static int n;
  private static int m;
  private static int k;
  private static int[][] dist;

  static class Room implements Comparable<Room> {

    int d;
    int num;

    public Room(int num, int d) {
      this.d = d;
      this.num = num;
    }

    @Override
    public int compareTo(Room o) {
      return Integer.compare(d, o.d);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      n = Integer.parseInt(st.nextToken()); // 방의 개수
      m = Integer.parseInt(st.nextToken()); // 비밀통로의 개수

      graph = new ArrayList<ArrayList<Room>>();
      for (int i = 0; i <= n; i++) {
        graph.add(new ArrayList<>());
      }

      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        graph.get(a).add(new Room(b, c));
        graph.get(b).add(new Room(a, c));
      }

      k = Integer.parseInt(br.readLine()); // 모임 참여친구 수
      int[] friends = new int[k];
      st = new StringTokenizer(br.readLine(), " ");
      for (int i = 0; i < k; i++) {
        friends[i] = Integer.parseInt(st.nextToken());
      }

      dist = new int[n + 1][n + 1];
      for (int i = 1; i <= n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[i][i] = 0;
      }

      int minIdx = n;
      int minVal = Integer.MAX_VALUE;
      for (int i = 1; i <= n; i++) {
        dijkstra(i);
        int firendDist = 0;
        for (int j = 0; j < k; j++) {
          firendDist += dist[i][friends[j]];
        }
        if (firendDist < minVal) {
          minVal = firendDist;
          minIdx = i;
        }
      }
      sb.append(minIdx).append("\n");
    }
    System.out.println(sb.toString());
  } // end of main

  private static void dijkstra(int start) {
    PriorityQueue<Room> pq = new PriorityQueue<Room>();
    pq.offer(new Room(start, 0));
    while (!pq.isEmpty()) {
      Room cur = pq.poll();
      if (cur.d > dist[start][cur.num]) continue;
      ArrayList<Room> li = graph.get(cur.num);
      for (Room nxt : li) {
        int nxtD = cur.d + nxt.d;
        if (dist[start][nxt.num] > nxtD) {
          dist[start][nxt.num] = nxtD;
          pq.offer(new Room(nxt.num, nxtD));
        }
      }
    }
  }
} // end of class
