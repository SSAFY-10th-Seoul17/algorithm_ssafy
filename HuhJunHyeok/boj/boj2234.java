import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [boj] 2234. 성곽
 */
public class boj2234 {
    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /**
     * M, N: 지도의 크기. 각각 열과 행.
     * roomCount: 방의 수
     * maxRoomSize: 가장 넓은 방의 넓이
     * removeWallMaxRoomSize: 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
     */
    static int M, N, roomCount, maxRoomSize, removeWallMaxRoomSize;
    /**
     * map: 지도(구현하면서 방의 정보 나타낼 예정, bfs에서 방문 처리의 역할도 겸함.), wall: 벽의 정보
     */
    static int[][] map, wall;
    /**
     * queue: bfs에서 사용할 queue
     */
    static Queue<Coordinate> queue;
    /**
     * dx, dy: 서쪽 북쪽 동쪽 남쪽 순서의 delta
     * dir: 벽에 대한 비트마스킹 값. 서쪽 북쪽 동쪽 남쪽 순서.
     */
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0}, dir = {1, 2, 4, 8};
    /**
     * adjRoom: 방 번호별로 인접한 방들의 번호를 저장하는 HashMap
     * key = 방 번호, value = 인접한 방들의 번호를 저장한 HashSet
     */
    static HashMap<Integer, HashSet<Integer>> adjRoom = new HashMap<>();
    /**
     * roomSizeList: 방의 넓이들을 저장하는 리스트.(방 번호 순서대로 저장)
     */
    static ArrayList<Integer> roomSizeList = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        wall = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        roomSizeList.add(0); // 추후에 사용할 때, adjRoom 등과 index값을 맞춰주기 위하여 0번 index에 대하여 설정.

        int roomNum = 1; // map 배열의 원소 default 값이 0이기 때문에 방의 번호는 1로 시작.
        queue = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) { // 아직 방 번호가 배정되지 않은 칸의 경우
                    bfs(i, j, roomNum++);
                    roomCount++;
                }
            }
        }

        for(int i = 1; i <= roomCount; i++) { // 1번 방부터 마지막 방까지 확인
            if(adjRoom.get(i) != null) { // 현재 방에 인접한 방들이 있는 경우
                for(int j: adjRoom.get(i)) {
                    removeWallMaxRoomSize = removeWallMaxRoomSize > roomSizeList.get(i) + roomSizeList.get(j)
                            ? removeWallMaxRoomSize : roomSizeList.get(i) + roomSizeList.get(j);
                }
            }
        }

        sb.append(roomCount).append("\n").append(maxRoomSize).append("\n").append(removeWallMaxRoomSize).append("\n");

        System.out.print(sb.toString());
    }

    public static void bfs(int x, int y, int roomNum) {
        map[x][y] = roomNum;
        queue.offer(new Coordinate(x, y));
        HashSet<Integer> adjSet = new HashSet<>(); // 인접한 방들의 번호를 저장하는 HashSet

        int roomSize = 0; // 방의 크기
        while(!queue.isEmpty()) {
            Coordinate now = queue.poll();

            roomSize++;

            for(int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(!isInBoundary(nextX, nextY)) {
                    continue;
                }

                if(map[nextX][nextY] != 0 && map[nextX][nextY] != roomNum) { // 번호가 배정된 방이지만 현재 배정 중인 번호와 다를 때
                    adjSet.add(map[nextX][nextY]);
                    continue;
                }

                /**
                 * 비트마스킹을 이용한 비트 연산을 통해 현재 칸에서 다음 칸으로의 이동이 가능한지 확인하고
                 * 이동이 가능한 경우에 다음 칸이 방문하지 않은 칸이면(방 번호 배정하지 않은 칸)
                 */
                if((wall[now.x][now.y] & dir[i]) == 0 && map[nextX][nextY] == 0) {
                    queue.offer(new Coordinate(nextX, nextY));
                    map[nextX][nextY] = roomNum;
                }
            }
        }

        maxRoomSize = maxRoomSize > roomSize ? maxRoomSize : roomSize;
        adjRoom.put(roomNum, adjSet); // 현재 방 번호에 대해서 인접한 방의 번호 설정.
        roomSizeList.add(roomSize);
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
