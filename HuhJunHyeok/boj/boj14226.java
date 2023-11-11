import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * [boj] 14226. 이모티콘
 */
public class boj14226 {
    static class Status {
        int screenEmoticonCnt;
        int clipboardEmoticonCnt;
        int time;

        public Status(int screenEmoticonCnt, int clipboardEmoticonCnt, int time) {
            this.screenEmoticonCnt = screenEmoticonCnt;
            this.clipboardEmoticonCnt = clipboardEmoticonCnt;
            this.time = time;
        }
    }
    /**
     * S: 최종 이모티콘의 수
     */
    static int S, maxSize;
    static boolean[][] visited;
    static Queue<Status> statusQueue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        maxSize = S << 1;

        System.out.println(bfs());
    }

    public static int bfs() {
        visited = new boolean[maxSize + 1][maxSize + 1];
        visited[1][0] = true;

        statusQueue.offer(new Status(1, 0, 0));

        while(!statusQueue.isEmpty()) {
            Status now = statusQueue.poll();

            if(now.screenEmoticonCnt == S) {
                return now.time;
            }

            if(now.screenEmoticonCnt > 0 && now.screenEmoticonCnt < maxSize) {
                // 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
                if(!visited[now.screenEmoticonCnt][now.screenEmoticonCnt]) {
                    visited[now.screenEmoticonCnt][now.screenEmoticonCnt] = true;

                    statusQueue.offer(new Status(now.screenEmoticonCnt, now.screenEmoticonCnt, now.time + 1));
                }

                // 화면에 있는 이모티콘 중 하나를 삭제
                if(!visited[now.screenEmoticonCnt - 1][now.clipboardEmoticonCnt]) {
                    visited[now.screenEmoticonCnt - 1][now.clipboardEmoticonCnt] = true;

                    statusQueue.offer(new Status(now.screenEmoticonCnt - 1, now.clipboardEmoticonCnt, now.time + 1));
                }
            }

            // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
            if(now.clipboardEmoticonCnt > 0 && now.screenEmoticonCnt + now.clipboardEmoticonCnt < maxSize) {
                visited[now.screenEmoticonCnt + now.clipboardEmoticonCnt][now.clipboardEmoticonCnt] = true;

                statusQueue.offer(new Status(now.screenEmoticonCnt + now.clipboardEmoticonCnt, now.clipboardEmoticonCnt, now.time + 1));
            }
        }
        return -1;
    }
}
