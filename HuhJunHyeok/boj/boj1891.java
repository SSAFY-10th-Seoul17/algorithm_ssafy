import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 1891. 사분면
 */
public class boj1891 {
    /**
     * digit 입력으로 들어온 사분면 조각 번호의 자릿수
     */
    static int digit;
    /**
     * sideLength digit을 통해 도출된 한 변의 길이.
     * moveCol 입력된 x. 좌우 방향 이동 횟수. 음수면 좌, 양수면 우.
     * moveRow 입력된 y. 상하 방향 이동 횟수. 음수면 하, 양수면 상.
     * colLocation 사분면에서 column 위치
     * rowLocation 사분면에서 row 위치
     */
    static long sideLength, moveCol, moveRow, colLocation = 0, rowLocation = 0;
    /**
     * quadrantInfo 입력으로 들어온 사분면의 조각 번호.
     */
    static String quadrantInfo;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        digit = Integer.parseInt(st.nextToken());
        sideLength = (long) 1 << digit;
        quadrantInfo = st.nextToken();

        st = new StringTokenizer(br.readLine(), " ");
        moveCol = Long.parseLong(st.nextToken());
        moveRow = Long.parseLong(st.nextToken());

        findQuadrantLocation(sideLength);
        moveQuadrantLocation();
        if(!isInBoundary(rowLocation, colLocation)) { // 존재하지 않는 사분면
            System.out.println(-1);
        } else {
            findQuadrantInfo(rowLocation, colLocation, sideLength);
            sb.append("\n");
            System.out.print(sb.toString());
        }
    }

    /**
     * String Type인 quadrantInfo(사분면의 정보)를 통해 사분면의 위치 찾기
     * -> colLocation, rowLocation의 정보 찾기.
     */
    public static void findQuadrantLocation(long quadrantSideLength) {
        for(int i = 0; i < digit; i++) {
            quadrantSideLength /= 2;
            int quadrantNum = quadrantInfo.charAt(i) - '0';
            // 2 1
            // 3 4
            // 사분면은 위와 같이 나뉘므로 제2사분면의 경우 colLocation, rowLocation 변화 X.
            switch (quadrantNum) {
                case 1 :
                    colLocation += quadrantSideLength;
                    break;
                case 3 :
                    rowLocation += quadrantSideLength;
                    break;
                case 4 :
                    colLocation += quadrantSideLength;
                    rowLocation += quadrantSideLength;
                    break;
            }
        }
    }

    /**
     * moveCol, moveRow에 따른 사분면 위치 이동.
     * row 이동의 경우 문제에서는 +가 위쪽 이동이지만, 좌표상은 -가 위쪽 이동.
     */
    public static void moveQuadrantLocation() {
        colLocation += moveCol;
        rowLocation -= moveRow;
    }

    /**
     * colLocation, rowLocation, quadrantLength를 통해 사분면 정보 찾기.
     */
    public static void findQuadrantInfo(long rowLocation, long colLocation, long quadrantLength) {
        if(quadrantLength == 1) {
            return;
        }

        long nextQuadrantLength = quadrantLength / 2;
        if(rowLocation < nextQuadrantLength && colLocation >= nextQuadrantLength) { // 제1사분면
            sb.append(1);
            findQuadrantInfo(rowLocation, colLocation - nextQuadrantLength, nextQuadrantLength);
        } else if(rowLocation < nextQuadrantLength) { // 제2사분면
            sb.append(2);
            findQuadrantInfo(rowLocation, colLocation, nextQuadrantLength);
        } else if(colLocation < nextQuadrantLength) { // 제3사분면
            sb.append(3);
            findQuadrantInfo(rowLocation - nextQuadrantLength, colLocation, nextQuadrantLength);
        } else { // 제4사분면
            sb.append(4);
            findQuadrantInfo(rowLocation - nextQuadrantLength, colLocation - nextQuadrantLength, nextQuadrantLength);
        }
    }

    public static boolean isInBoundary(long rowLocation, long colLocation) {
        return 0 <= rowLocation && rowLocation < sideLength && 0 <= colLocation && colLocation < sideLength;
    }
}
