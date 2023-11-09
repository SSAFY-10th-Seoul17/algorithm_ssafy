import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/**
 * [boj] 17092. 색칠 공부
 */
public class boj17092 {
    /**
     * H: 모눈종이의 행 수, W: 모눈종이의 열 수, N: 검정색 칸의 수
     */
    static int H, W, N;
    static HashSet<Long> graphPaper = new HashSet<>(), checkPoint = new HashSet<>();
//    static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] drdc;
    static long[] blackCount = new long[10];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graphPaper.add((long) (r - 1) * W + c);
        }

        drdc = new int[] {-W - 1, -W, -W + 1, -1, 0, 1, W - 1, W, W + 1};
        for(long point: graphPaper) {
            for(int i = 0; i < 9; i++) {
                checkBlackCount(point, i);
            }
        }

        blackCount[0] = (long) (H - 2) * (W - 2);
        for(int i = 1; i <= 9; i++) {
            blackCount[0] -= blackCount[i];
        }

        for(long cnt: blackCount) {
            sb.append(cnt).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static void checkBlackCount(long point, int idx) {
        long now = point + drdc[idx];

        if(!isPossible(now)) {
            return;
        }

        if(checkPoint.contains(now)) {
            return;
        }

        int count = 0;
        for(int i = 0; i < 9; i++) {
            long next = now + drdc[i];

            if(graphPaper.contains(next)) {
                count++;
            }
        }

        blackCount[count]++;
        checkPoint.add(now);
    }

    public static boolean isPossible(long val) {
        return W < val && val <= (long) (H - 1) * W && (val % W != 0) && (val % W != 1);
    }
}


//public class boj17092 {
//    static class Coordinate {
//        int r;
//        int c;
//
//        public Coordinate(int r, int c) {
//            this.r = r;
//            this.c = c;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(obj instanceof Coordinate) {
////                return this.r == ((Coordinate) obj).r && this.c == ((Coordinate) obj).c;
//                // hashCode가 r+c인 상태에서 r값이 동일하면 c값도 동일함이 보장.
//                return this.r == ((Coordinate) obj).r;
//            } else {
//                return false;
//            }
////            return this.r == ((Coordinate) obj).r;
//        }
//
//        @Override
//        public int hashCode() {
//            return r + c;
//        }
//    }
//    /**
//     * H: 모눈종이의 행 수, W: 모눈종이의 열 수, N: 검정칸의 수
//     */
//    static int H, W, N;
//    /**
//     * graphPaper: 모눈종이
//     * checkPoint: 확인을 마친 위치
//     */
////    static HashSet<Coordinate> graphPaper = new HashSet<>(), checkPoint = new HashSet<>();
////    static LinkedHashSet<Coordinate> graphPaper = new LinkedHashSet<>(), checkPoint = new LinkedHashSet<>();
//    static LinkedHashSet<Coordinate> graphPaper, checkPoint;
//    static long[] blackCount = new long[10];
//    static int[] dr = {-1, -1, 1, 1, -1, 0, 0, 1, 0}, dc = {-1, 1, -1, 1, 0, -1, 1, 0, 0};
////    static long totalCount = 0;
//    static StringBuilder sb = new StringBuilder();
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//
//        H = Integer.parseInt(st.nextToken());
//        W = Integer.parseInt(st.nextToken());
//        N = Integer.parseInt(st.nextToken());
//
//        graphPaper = new LinkedHashSet<>(N);
//        checkPoint = new LinkedHashSet<>(N);
//        for(int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//
//            int r = Integer.parseInt(st.nextToken());
//            int c = Integer.parseInt(st.nextToken());
//
//            graphPaper.add(new Coordinate(r, c));
//        }
//
//        for(Coordinate refPoint: graphPaper) {
//            for(int i = 0; i < 9; i++) {
//                checkBlackCount(i, refPoint);
//            }
//        }
//
//        blackCount[0] = (long) (H - 2) * (W - 2);
//        for(int i = 1; i <= 9; i++) {
//            blackCount[0] -= blackCount[i];
//        }
//
//        for(long nthCount: blackCount) {
//            sb.append(nthCount).append("\n");
//        }
//
//        System.out.print(sb.toString());
//    }
//
//    public static void checkBlackCount(int i, Coordinate refPoint) {
//        int count = 0;
//
//        Coordinate now = new Coordinate(refPoint.r + dr[i], refPoint.c + dc[i]);
//
//        if(!isExist(now.r, now.c)) {
//            return;
//        }
//
//        if(checkPoint.contains(now)) { // now의 위치가 3*3 보드의 중앙으로 하여 체크한 곳일 때
//            return;
//        }
//
//        for(int j = 0; j < 9; j++) {
//            Coordinate next = new Coordinate(now.r + dr[j], now.c + dc[j]);
//
//            if(graphPaper.contains(next)) {
//                count++;
//            }
//        }
//
////        System.out.println("AAA : " + count);
//
//        checkPoint.add(now);
////        totalCount++;
//        blackCount[count]++;
//    }
//
//    public static boolean isExist(int r, int c) {
//        return 1 < r && r < H && 1 < c && c < W;
//    }
//}
