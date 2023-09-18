import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj5904 {
    static int len = 3;
    static int n;
    static int idx;
    static ArrayList<Integer> lengths = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.valueOf(br.readLine());

        lengths.add(3);

        int add = 4;
        while (len < n) {
            len = len * 2 + add++;
            lengths.add(len);
        }
        idx = lengths.size() - 2;

        int l = 1;
        int r = len;

        add--;
        boolean found = false;
        while (idx >= 0) {
//            System.out.println(l + " " + r);

            int m1 = l + lengths.get(idx--) - 1;
            int m2 = m1 + add--;

            if (n <= m1) {
                r = m1;
            } else if (n <= m2) {
                if (n == m1 + 1) {
                    System.out.println("m");
                } else {
                    System.out.println("o");
                }
                found = true;
                break;
            } else {
                l = m2 + 1;
            }
        }

        if (!found) {
            if (l == n)
                System.out.println("m");
            else
                System.out.println("o");
        }
    }
}
