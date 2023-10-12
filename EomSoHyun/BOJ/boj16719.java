import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static boolean[] mark;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        str = br.readLine();
        mark = new boolean[str.length()];

        ZOAC(0, str.length()-1);

        System.out.println(sb);


    }

    public static void ZOAC(int start, int end) {
        if (start > end) {
            return;
        }

        int idx = start;
        for (int i = start+1; i <= end; i++) {
            if (str.charAt(idx) > str.charAt(i)) {
                idx = i;
            }
        }
        mark[idx] = true;
        print();
        ZOAC(idx+1, end);
        ZOAC(start, idx-1);


    }

    public static void print() {
        for (int i = 0; i < str.length(); i++) {
            if (mark[i]) {
                sb.append(str.charAt(i));
            }
        }
        sb.append('\n');
    }

}
