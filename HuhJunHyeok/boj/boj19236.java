import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * [boj] 19236. 청소년 상어
 */
public class boj19236 {
    /**
     * 물고기 클래스
     */
    static class Fish implements Comparable<Fish> {
        /**
         * x,y: 물고기가 위치한 곳의 좌표
         */
        int x, y;
        /**
         * id: 물고기의 번호, dir: 물고기의 방향
         */
        int id, dir;
        /**
         * isAlive: 물고기의 생사 여부
         */
        boolean isAlive;

        public Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.id, o.id);
        }
    }

    /**
     * 상어 클래스
     */
    static class Shark {
        /**
         * x, y: 상어가 위치한 곳의 좌표
         */
        int x, y;
        /**
         * dir: 상어의 방향, totalEatId: 먹은 물고기 번호의 합
         */
        int dir, totalEatId;

        public Shark(int x, int y, int dir, int totalEatId) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.totalEatId = totalEatId;
        }
    }

    /**
     * map: 4x4 크기의 공간 정보
     */
    static int[][] map = new int[4][4];
    /**
     * fishes: 물고기들의 정보를 담은 리스트
     */
    static ArrayList<Fish> fishes = new ArrayList<>();
    /**
     * shark: 상어 객체
     */
    static Shark shark;
    /**
     * LOCATION_SHARK: 상어가 있는 위치를 표현하는 상수.
     */
    static final int LOCATION_SHARK = -1;
    /**
     * maxTotalEatId: 먹은 물고기 번호의 합의 최댓값
     */
    static int maxTotalEatId;
    /**
     * dx, dy: ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 순서의 delta값.
     */
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 4; j++) {
                int id = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = id;
                fishes.add(new Fish(i, j, id, dir, true));
            }
        }

        Collections.sort(fishes); // 물고기는 번호(id) 값이 작은 물고기부터 이동해야 함.

        // 처음에 상어가 (0, 0) 위치로 들어가면서 그 위치에 있는 물고기를 먹음.
        Fish fish = fishes.get(map[0][0] - 1); // 물고기의 번호 - 1 = fishes 리스트에서의 위치
        shark = new Shark(0, 0, fish.dir, fish.id);
        fish.isAlive = false;
        map[0][0] = LOCATION_SHARK;

        simulate(map, fishes, shark);

        System.out.println(maxTotalEatId);
    }

    /**
     * 재귀를 통해 상어가 이동할 수 없을 때까지 반복.
     * 1. 모든 물고기를 순서대로 이동
     * 2. 상어가 이동해서 물고기를 먹음.
     */
    public static void simulate(int[][] usingMap, ArrayList<Fish> usingFishes, Shark usingShark) {
        if(usingShark.totalEatId > maxTotalEatId) {
            maxTotalEatId = usingShark.totalEatId;
//            System.out.println(maxTotalEatId);
        }

        usingFishes.forEach(fish -> moveFish(fish, usingMap, usingFishes));

        for(int i = 1; i < 4; i++) { // 4x4 이므로 상어는 1~3칸 이동 가능.
            int nextX = usingShark.x + dx[usingShark.dir] * i;
            int nextY = usingShark.y + dy[usingShark.dir] * i;

            if(!isInBoundary(nextX, nextY) || usingMap[nextX][nextY] == 0) {
                continue;
            }

            int[][] cloneMap = cloneMap(usingMap);
            ArrayList<Fish> cloneFishes = cloneFishes(usingFishes);

            cloneMap[usingShark.x][usingShark.y] = 0;

            Fish fish = cloneFishes.get(cloneMap[nextX][nextY] - 1);
            fish.isAlive = false;
            Shark newShark = new Shark(fish.x, fish.y, fish.dir, usingShark.totalEatId + fish.id);
            cloneMap[fish.x][fish.y] = LOCATION_SHARK;

            simulate(cloneMap, cloneFishes, newShark);
        }
    }

    /**
     * 물고기를 이동.
     * 빈칸과 다른 물고기가 있는 칸으로 이동 가능.(다른 물고기가 있는 칸이면 위치를 서로 바꿈.)
     * 이동이 불가능하면 반시계 방향으로 45도씩 회전하면서 이동 시도.
     * @param targetFish
     * @param usingMap
     * @param usingFishes
     */
    public static void moveFish(Fish targetFish, int[][] usingMap, ArrayList<Fish> usingFishes) {
        if(!targetFish.isAlive) { // 죽은 물고기인 경우
            return;
        }

        for(int i = 0; i < 8; i++) {
            int tempDir = targetFish.dir + i;
            int nextDir = tempDir < 8 ? tempDir : tempDir - 8;
            int nextX = targetFish.x + dx[nextDir];
            int nextY = targetFish.y + dy[nextDir];

            if(!isInBoundary(nextX, nextY) || usingMap[nextX][nextY] == LOCATION_SHARK) { // 경계를 벗어나거나 상어가 있는 칸인 경우
                continue;
            }

            if(usingMap[nextX][nextY] == 0) { // 빈 칸으로 이동하는 경우
                usingMap[targetFish.x][targetFish.y] = 0;

                targetFish.x = nextX;
                targetFish.y = nextY;
            } else {
                Fish switchFish = usingFishes.get(usingMap[nextX][nextY] - 1);

                switchFish.x = targetFish.x;
                switchFish.y = targetFish.y;
                usingMap[targetFish.x][targetFish.y] = switchFish.id;

                targetFish.x = nextX;
                targetFish.y = nextY;
            }

            usingMap[nextX][nextY] = targetFish.id;
            targetFish.dir = nextDir;

            return;
        }
    }

    /**
     * 2차원 배열 깊은 복사
     * @param src
     * @return
     */
    public static int[][] cloneMap(int[][] src) {
        int[][] dest = new int[4][4];

        for(int i = 0; i < 4; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, 4);
        }

        return dest;
    }

    /**
     * 리스트 깊은 복사.
     * @param src
     * @return
     */
    public static ArrayList<Fish> cloneFishes(ArrayList<Fish> src) {
        ArrayList<Fish> dest = new ArrayList<>();

        src.forEach(fish -> dest.add(new Fish(fish.x, fish.y, fish.id, fish.dir, fish.isAlive))); // dest.add(fish) 하면 얕은 복사.

        return dest;
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}
