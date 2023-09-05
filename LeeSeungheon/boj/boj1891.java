import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1891 {

    static long row = 0;
    static long column = 0;
    static int len;
    static String number;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        len = Integer.parseInt(st.nextToken());
        long length = (long) Math.pow(2, len);
        number = st.nextToken();

        findLocation(0, length / 2);

        st = new StringTokenizer(br.readLine());
        column += Long.parseLong(st.nextToken());
        row -= Long.parseLong(st.nextToken());

        if (row >= length || column >= length || row < 0 || column < 0) {
            System.out.println(-1);
            return;
        }

        makeNumber(0, length / 2);
        System.out.println(sb);
    }

    private static void makeNumber(int count, long length) {

        if (count == len) {
            return;
        }

        if (row < length && column >= length) {
            sb.append(1);
            column -= length;
        } else if (row < length) {
            sb.append(2);
        } else if (column < length) {
            sb.append(3);
            row -= length;
        } else {
            sb.append(4);
            row -= length;
            column -= length;
        }
        makeNumber(count + 1, length / 2);
    }

    private static void findLocation(int count, long length) {
        if (count == len) {
            return;
        }
        char curNum = number.charAt(count);

        if (curNum == '1') {
            column += length;
        } else if (curNum == '2') {
        } else if (curNum == '3') {
            row += length;
        } else if (curNum == '4') {
            row += length;
            column += length;
        }
        findLocation(count + 1, length / 2);
    }


}
