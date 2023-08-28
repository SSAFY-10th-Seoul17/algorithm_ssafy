import java.io.*;
import java.util.Arrays;

class boj4779 {
    public static final int CANTORENUM = 3;
    static char[] chars;
    public static int n;
    public static BufferedReader br;
    public static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        while((n = readNext()) != -1) {
            sb = new StringBuilder();
            int len = (int)Math.pow(CANTORENUM, n);
            chars = new char[len];
            Arrays.fill(chars, '-');
            cantore(0, len);
            sb.append(chars);
            System.out.println(sb);
        }
        br.close();
    }

    public static void cantore(int start, int end) {
        if(end - start <= 1) return;

        int diff = (end - start) / 3;

        Arrays.fill(chars, start+diff, end-diff,' ');

        cantore(start, start+diff);
        cantore(end-diff, end);

    }

    public static int readNext() throws IOException {
        String input = br.readLine();
        if(input == null) return -1;
        return Integer.parseInt(input);
    }
}