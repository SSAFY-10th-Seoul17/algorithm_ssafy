import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  private static int N;
  private static ArrayList<ArrayList<Room>> graph;
  private static int robot1;
  private static int robot2;

  static class Room implements Comparable<Room> {

    int no;
    int d;
    int diff = 0;

    public Room(int no, int d) {
      this.no = no;
      this.d = d;
    }

    public Room(int no, int d, int diff) {
      super();
      this.no = no;
      this.d = d;
      this.diff = diff;
    }

    @Override
    public int compareTo(Room o) {
      return d - o.d;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    robot1 = Integer.parseInt(st.nextToken());
    robot2 = Integer.parseInt(st.nextToken());

    graph = new ArrayList<ArrayList<Room>>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<Room>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int r1 = Integer.parseInt(st.nextToken());
      int r2 = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      graph.get(r1).add(new Room(r2, d));
      graph.get(r2).add(new Room(r1, d));
    }

    djikstra();
  } // end of main

  private static void djikstra() {
    int[] dist = new int[N + 1]; // dist[i][0]: robot1에서i까지의 최단거리
    Arrays.fill(dist, Integer.MAX_VALUE);

    PriorityQueue<Room> pq = new PriorityQueue<Main.Room>();
    dist[robot1] = 0;
    pq.offer(new Room(robot1, 0, 0));
    while (!pq.isEmpty()) {
      Room cur = pq.poll();
      if (cur.no == robot2) {
        System.out.println(dist[robot2] - cur.diff);
        return;
      }
      if (cur.d > dist[cur.no]) continue;
      ArrayList<Room> li = graph.get(cur.no);
      for (Room nxt : li) {
        int nxtD = cur.d + nxt.d;
        if (dist[nxt.no] > nxtD) {
          dist[nxt.no] = nxtD;
          pq.offer(new Room(nxt.no, nxtD, Math.max(cur.diff, nxt.d)));
        }
      }
    }
  }
} // end of class
