import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [boj] 14466. 소가 길을 건너간 이유 6
 */
public class boj14466 {
    static class BridgePos {
        int r1;
        int c1;
        int r2;
        int c2;

        public BridgePos(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }
    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    /**
     * N: 정사각형 목초지의 한 변의 길이, K: 소의 수, R: 목초지를 잇는 길의 수,
     * impossibleToMeetPairCount: 길을 건너지 않으면 만날 수 없는 소의 쌍 수
     */
    static int N, K, R, impossibleToMeetPairCount;
    /**
     * farm: 목초지
     */
    static int[][] farm;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static ArrayList<BridgePos> bridgeList = new ArrayList<>();
    static ArrayList<Pos> cowList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        farm = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(farm[i], -1); // 아직 탐색되지 않음.
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            bridgeList.add(new BridgePos(r1, c1, r2, c2));
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            cowList.add(new Pos(r, c));
        }

        for(int i = 0; i < K; i++) {
            Pos nowCowPos = cowList.get(i);
            if(farm[nowCowPos.r][nowCowPos.c] == -1) {
                bfs(nowCowPos, i);
            }
        }

        for(int i = 0, last = K - 1; i < last; i++) {
            for(int j = i + 1; j < K; j++) {
                Pos iCow = cowList.get(i);
                Pos jCow = cowList.get(j);

                if(farm[iCow.r][iCow.c] != farm[jCow.r][jCow.c]) {
                    impossibleToMeetPairCount++;
                }
            }
        }

        System.out.println(impossibleToMeetPairCount);
    }

    public static void bfs(Pos cowPos, int areaNum) {
        visited = new boolean[N][N];
        visited[cowPos.r][cowPos.c] = true;

        farm[cowPos.r][cowPos.c] = areaNum;

        Queue<Pos> posQueue = new ArrayDeque<>();
        posQueue.offer(new Pos(cowPos.r, cowPos.c));

        while(!posQueue.isEmpty()) {
            Pos now = posQueue.poll();

            dirFor:
            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];

                if(!isInFarm(nextR, nextC) || visited[nextR][nextC]) {
                    continue;
                }

                for(BridgePos bridgePos: bridgeList) {
                    if(isLink(bridgePos, now, nextR, nextC)) {
                        continue dirFor;
                    }
                }

                visited[nextR][nextC] = true;
                farm[nextR][nextC] = areaNum;
                posQueue.offer(new Pos(nextR, nextC));
            }
        }
    }

    public static boolean isInFarm(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    public static boolean isLink(BridgePos bridgePos, Pos cowPos, int posR, int posC) {
        return (bridgePos.r1 == cowPos.r && bridgePos.c1 == cowPos.c && bridgePos.r2 == posR && bridgePos.c2 == posC)
                || (bridgePos.r1 == posR && bridgePos.c1 == posC && bridgePos.r2 == cowPos.r && bridgePos.c2 == cowPos.c);
    }
}
