import java.io.*;
import java.util.Arrays;

public class boj4779 {

    public static int n;
    public static int length;
    public static char[] chars;
    public static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while ((input = br.readLine()) != null) {
            n = Integer.parseInt(input);
            length = (int) Math.pow(3, n);
            chars = new char[length];
            Arrays.fill(chars, '-');
            cantor(0, length);
            bw.write(chars);
            bw.write('\n');
            bw.flush();
        }
    }

    public static void cantor(int start, int length) {
        if (length == 1) {
            return;
        }
        length /= 3;
        Arrays.fill(chars, start + length, start + length * 2, ' ');
        cantor(start, length);
        cantor(start + length * 2, length);
    }
}
