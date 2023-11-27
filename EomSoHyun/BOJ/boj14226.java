import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main  {

    static int S;
    static boolean[][] visited;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        q = new LinkedList<>();
        q.offer(new int[] {1, 0, 0});
        visited = new boolean[10000][10000];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            // 붙여 넣기
            if (now[1] > 0 && check(now[0]+now[1], now[1], now[2]+1)) break;

            // 삭제
            if (now[0]-1 > 0 && check(now[0]-1, now[1], now[2]+1)) break;

            // 복사
            if (check(now[0], now[0], now[2]+1)) break;

        }

    }

    public static boolean check(int state, int clip, int time) {
        if (state == S) {
            System.out.println(time);
            return true;
        }
        if (!visited[state][clip]) {
            q.offer(new int[] {state, clip, time});
            visited[state][clip] = true;
        }

        return false;
    }

}
