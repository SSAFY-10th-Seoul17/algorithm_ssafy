import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1891 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int d = Integer.parseInt(st.nextToken());
        String number = st.nextToken();

        st = new StringTokenizer(br.readLine(), " ");
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        System.out.println(movePoint(number, d, x, -y));
    }

    /** 2 3 -> 0, 1 4 -> 1 */
    static final char[] X_BIT = {0, '1', '0', '0', '1'};
    /** 1 2 -> 0, 3 4 -> 1 */
    static final char[] Y_BIT = {0, '0', '0', '1', '1'};
    /** 00 -> 2, 01 -> 3, 10 -> 1, 11 -> 4 */
    static final char[] PARSE = {'2', '3', '1', '4'};

    private static String movePoint(String number, int d, long x, long y) {
        long maxSize = 1L << d;
        char[] xBit = new char[d];
        long xCount = getCount(xBit, X_BIT, number, x);
        if (xCount < 0 || xCount >= maxSize) {
            return "-1";
        }

        extractBit(xBit, xCount);

        // y
        char[] yBit = new char[d];
        long yCount = getCount(yBit, Y_BIT, number, y);
        if (yCount < 0 || yCount >= maxSize) {
            return "-1";
        }

        extractBit(yBit, yCount);

        return parseToPoint(d, xBit, yBit);
    }

    /** 비트를 사분면 좌표로 */
    private static String parseToPoint(int d, char[] xBit, char[] yBit) {
        char[] result = new char[d];
        for (int i = 0; i < result.length; i++) {
            int bitX = xBit[i] - '0';
            int bitY = yBit[i] - '0';

            result[i] = PARSE[bitX * 2 + bitY];
        }

        return String.valueOf(result);
    }

    private static long getCount(char[] bit, char[] parse, String number, long delta) {
        for (int i = 0; i < bit.length; i++) {
            bit[i] = parse[number.charAt(i) - '0'];
        }

        return Long.parseLong(String.valueOf(bit), 2) + delta;
    }

    private static void extractBit(char[] bit, long count) {
        Arrays.fill(bit, '0');
        String bitString = Long.toBinaryString(count);
        int idx = bit.length - 1;

        for (int i = bitString.length() - 1; i >= 0; i--) {
            bit[idx--] = bitString.charAt(i);
        }
    }
}
