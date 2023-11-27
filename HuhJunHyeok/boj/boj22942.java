import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [boj] 22942. 데이터 체커
 */
public class boj22942 {
    /**
     * XAxisDot: 원이 x축과 접하는 점을 의미하는 클래스
     */
    static class XAxisDot implements Comparable<XAxisDot> {
        /**
         * circleSequnce: 몇 번째 원인가
         */
        int circleSequnce;
        /**
         * xCoordinate: 접점의 x좌표
         */
        int xCoordinate;
        /**
         * isLeftDot: 왼쪽 접점인가
         */
        boolean isLeftDot;

        public XAxisDot(int circleSequnce, int xCoordinate, boolean isLeftDot) {
            this.circleSequnce = circleSequnce;
            this.xCoordinate = xCoordinate;
            this.isLeftDot = isLeftDot;
        }

        @Override
        public int compareTo(XAxisDot o) {
            return Integer.compare(this.xCoordinate, o.xCoordinate);
        }

        @Override
        public String toString() {
            return "XAxisDot{" +
                    "circleSequnce=" + circleSequnce +
                    ", xCoordinate=" + xCoordinate +
                    ", isLeftDot=" + isLeftDot +
                    '}';
        }
    }
    /**
     * N: 원의 수
     */
    static int N;
    static XAxisDot[] xAxisDots;
    static Stack<XAxisDot> xAxisDotStack = new Stack<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        xAxisDots = new XAxisDot[N * 2];
        for(int i = 0, idx = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int centerX = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            xAxisDots[idx++] = new XAxisDot(i, centerX - r, true);
            xAxisDots[idx++] = new XAxisDot(i, centerX + r, false);
        }

        Arrays.sort(xAxisDots);

        System.out.println(check() ? "YES" : "NO");
    }

    public static boolean check() {
        for(int i = 0; i < N * 2; i++) {
            if(i != 0 && xAxisDots[i - 1].xCoordinate == xAxisDots[i].xCoordinate) {
                return false;
            }
            if(xAxisDotStack.isEmpty()) {
                xAxisDotStack.push(xAxisDots[i]);
            } else {
                if(xAxisDots[i].isLeftDot) {
                    xAxisDotStack.push(xAxisDots[i]);
                } else {
                    if(xAxisDotStack.peek().circleSequnce == xAxisDots[i].circleSequnce) {
                        xAxisDotStack.pop();
                    } else {
                        return false;
                    }
                }
//                XAxisDot nowDot = xAxisDotStack.peek();
//
//                if(nowDot.circleSequnce == xAxisDots[i].circleSequnce) { // 같은 원
//                    xAxisDotStack.pop();
//                } else {
//                    if((nowDot.isLeftDot && !xAxisDots[i].isLeftDot)) { // 두 원의 접점 발생
//                        return false;
//                    }
//                    xAxisDotStack.push(xAxisDots[i]);
//                }
            }
        }
        return true;
    }
}

