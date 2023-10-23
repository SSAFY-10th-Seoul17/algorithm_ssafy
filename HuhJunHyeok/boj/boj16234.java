import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 16234. 인구 이동
 */
public class boj16234 {
    /**
     * Country: 나라를 의미하는 class
     */
    static class Country {
        int r, c;

        public Country(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    /**
     * N: 땅의 한 라인의 크기, L: 국경선 여는 인구 차이 하한값, R: 국경선 여는 인구 차이 상한값
     * days: 인구 이동이 며칠 동안 발생하는지, openBoarderCountryCnt: 국경이 열린 나라의 인구 수 합
     */
    static int N, L, R, days, openBoarderCountryPopulation;
    /**
     * map: 땅을 나타내는 지도
     */
    static int[][] map;
    static boolean possibleToMove;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static ArrayList<Country> countryList = new ArrayList<>();
    static Queue<Country> countryQueue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        movePopulation();

        System.out.println(days);
    }

    /**
     * bfs를 통해 인구 이동이 가능한지 파악하여 인구 이동이 불가능 할 때까지 인구 이동 진행.
     */
    public static void movePopulation() {
        while(true) { // 인구 이동이 불가능할 때까지 반복
            possibleToMove = false;
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
//                        countryQueue = new ArrayDeque<>();
//                        countryList = new ArrayList<>();
                        countryList.clear();
                        openBoarderCountryPopulation = 0;
                        bfs(i, j);

                        if(countryList.size() > 1) { // 국경이 열린 나라가 두 나라 이상인 경우 인구 이동이 일어남.
                            possibleToMove = true;

                            int avgPopulation = openBoarderCountryPopulation / countryList.size();
                            for(Country country: countryList) {
                                map[country.r][country.c] = avgPopulation;
                            }
                        }
                    }
                }
            }

            if(!possibleToMove) {
                return;
            }
            days++;
        }
    }

    public static void bfs(int r, int c) {
        countryQueue.offer(new Country(r, c));
        countryList.add(new Country(r, c));

        visited[r][c] = true;
        openBoarderCountryPopulation = map[r][c];

        while(!countryQueue.isEmpty()) {
            Country nowCountry = countryQueue.poll();

            for(int i = 0; i < 4; i ++) {
                int nextR = nowCountry.r + dr[i];
                int nextC = nowCountry.c + dc[i];

                if(!isInMap(nextR, nextC) || visited[nextR][nextC]) {
                    continue;
                }

                int diff = Math.abs(map[nowCountry.r][nowCountry.c] - map[nextR][nextC]);
                if(L <= diff && diff <= R) {
                    countryQueue.offer(new Country(nextR, nextC));
                    countryList.add(new Country(nextR, nextC));

                    visited[nextR][nextC] = true;
                    openBoarderCountryPopulation += map[nextR][nextC];
                }
            }
        }
    }

    public static boolean isInMap(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
