import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 13265. 색칠하기
 */
public class boj13265 {
    /**
     * T: 테스트 케이스의 수, n: 동그라미의 수, m: 직선의 수
     */
    static int T, n, m;
    /**
     * adjList: 인접 리스트
     */
    static ArrayList<Integer>[] adjList;
    /**
     * color: index에 해당하는 동그라미별 색상. 1, -1은 색칠 완료. 0은 아직 색칠 전.
     */
    static int[] color;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        testCase:
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from].add(to);
                adjList[to].add(from);
            }

            color = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                if(color[i] == 0 && !bfs(i)) {
                    sb.append("impossible").append("\n");
                    continue testCase;
                }
            }
            sb.append("possible").append("\n");
        }
        System.out.print(sb.toString());
    }

    /**
     * 사용하는 색깔은 -1, 1의 2가지 색상.
     * 시작하는 동그라미를 -1로 칠하고, 연결된 동그라미들을 1로 칠한다.
     * bfs를 통해 인접한 동그라미들을 색칠하는데, 만약 이미 색칠되어 있는 동그라미이고 현재 동그라미의 색깔 + 인접 동그라미의 색깔 != 0 이면
     * cycle이 발생한 것이다. cycle이 발생하면 문제에서 원하는 2가지 색상으로 연결된 두 동그라미의 색상이 다르게 칠하는 작업은 불가능하다.
     * @param startCircle
     */
    public static boolean bfs(int startCircle) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startCircle);
        color[startCircle] = -1;

        while(!queue.isEmpty()) {
            int nowCircle = queue.poll();

            for(int nextCircle: adjList[nowCircle]) {
                if(color[nextCircle] == 0) {
                    queue.offer(nextCircle);
                    color[nextCircle] = -color[nowCircle];
                } else if(color[nowCircle] + color[nextCircle] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
