import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  private static int[] dr = { -1, 0, 1, 0 };
  private static int[] dc = { 0, 1, 0, -1 };
  private static int Totalwolf = 0;
  private static int Totalsheep = 0;
  private static int R;
  private static int C;
  private static char[][] map;
  private static boolean[][] visited;

  static class Point {

    int r;
    int c;

    public Point(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    StringBuilder sb = new StringBuilder();
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    map = new char[R][C];
    for (int i = 0; i < R; i++) { // 맵입력
      String str = br.readLine();
      for (int j = 0; j < C; j++) {
        map[i][j] = str.charAt(j);
      }
    }

    visited = new boolean[R][C];
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (!visited[i][j]) {
          visited[i][j] = true;
          bfs(i, j);
        }
      }
    }
    sb.append(Totalsheep).append(" ").append(Totalwolf);
    System.out.println(sb.toString());
  } // end of main

  private static void bfs(int sr, int sc) {
    int wolf = 0;
    int sheep = 0;
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(sr, sc));
    while (!queue.isEmpty()) {
      Point cur = queue.poll();
      if (map[cur.r][cur.c] == 'v') {
        wolf++;
      } else if (map[cur.r][cur.c] == 'o') {
        sheep++;
      }
      for (int i = 0; i < 4; i++) {
        int nr = cur.r + dr[i];
        int nc = cur.c + dc[i];
        if (
          0 > nr ||
          nr >= R ||
          0 > nc ||
          nc >= C ||
          visited[nr][nc] ||
          map[nr][nc] == '#'
        ) continue;
        visited[nr][nc] = true;
        queue.offer(new Point(nr, nc));
      }
    }

    if (sheep > wolf) Totalsheep += sheep; else Totalwolf += wolf;
  }
} // end of class
