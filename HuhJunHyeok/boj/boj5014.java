import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 5014. 스타트링크
 */
public class boj5014 {
    // f: 총 f층, s: 현재 위치 층, g: 목적지의 층, u: 위로 이동시 이동하는 층의 수, d:아래로 이동시 이동하는 층의 수
    static int f, s, g, u, d;
    static int[] visitedWithCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visitedWithCount = new int[f + 1];

        bfs();
    }

    public static void bfs() {
        Queue<Integer> elevatorQueue = new ArrayDeque<Integer>();
        elevatorQueue.add(s);
        visitedWithCount[s] = 1;

        while(!elevatorQueue.isEmpty()) {
            int nowFloor = elevatorQueue.poll();

            if(nowFloor == g) {
                System.out.println(visitedWithCount[nowFloor] - 1);
                return;
            }

            int upFloor = nowFloor + u;
            int downFloor = nowFloor - d;

            if(upFloor <= f && visitedWithCount[upFloor] == 0) {
                visitedWithCount[upFloor] = visitedWithCount[nowFloor] + 1;
                elevatorQueue.add(upFloor);
            }

            if(downFloor > 0 && visitedWithCount[downFloor] == 0) {
                visitedWithCount[downFloor] = visitedWithCount[nowFloor] + 1;
                elevatorQueue.add(downFloor);
            }
        }

        System.out.println("use the stairs");
    }
}