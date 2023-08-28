import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj5014 {

    static int F, S, G, U, D;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        visited = new int[F + 1];
        S = Integer.parseInt(st.nextToken());
        visited[S] = 1;
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        bfs(S);
        if (visited[G] == 0) {
            System.out.println("use the stairs");
        } else{
            System.out.println(visited[G]-1);
        }
    }

    static void bfs(int S) {
        Queue<Integer> que = new LinkedList<>();
        que.add(S);
        while (!que.isEmpty()) {
            int p = que.remove();
            if (inGraph(p + U) && visited[p + U] == 0) {
                que.add(p + U);
                visited[p + U] = visited[p] + 1;
            }
            if (inGraph(p - D) && visited[p - D] == 0) {
                que.add(p - D);
                visited[p - D] = visited[p] + 1;
            }
        }

    }

    static boolean inGraph(int cur) {
        return 1 <= cur && cur <= F;
    }


}
