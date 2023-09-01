import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  private static int n;
  private static int m;
  private static ArrayList<ArrayList<Node>> graph;

  static class Node {

    int num;
    int weight;

    public Node(int num, int weight) {
      this.num = num;
      this.weight = weight;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    graph = new ArrayList<ArrayList<Node>>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<Node>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(n1).add(new Node(n2, w));
      graph.get(n2).add(new Node(n1, w));
    }

    st = new StringTokenizer(br.readLine(), " ");
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    dijkstra(start, end);
  } // end of main

  private static void dijkstra(int start, int end) {
    int[] w = new int[n + 1];
    Arrays.fill(w, Integer.MAX_VALUE);
    w[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2) ->
      n1.weight - n2.weight
    );
    pq.offer(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (w[cur.num] < cur.weight) continue;
      ArrayList<Node> curLi = graph.get(cur.num);
      for (Node nxt : curLi) {
        int nxtW = cur.weight + nxt.weight;
        if (w[nxt.num] > nxtW) {
          w[nxt.num] = nxtW;
          pq.offer(new Node(nxt.num, nxtW));
        }
      }
    }
    System.out.println(w[end]);
  }
} // end of class
