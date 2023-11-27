package study11월2주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj25795_예쁜초콜릿과숫자놀이 {
    static int n, a, b, c;
    static int[] groups;
    static boolean[] isWhite;
    static final long MOD = 100000;
    static long tmp = 0;
    static int ans = 0;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        groups = new int[n * 2];
        isWhite = new boolean[n * 2];
        makeGroups(0, 0, 0);
        System.out.println(ans);
    }

    private static void makeGroups(int whiteused, int blackused, int idx) {
        if (whiteused == n) {
            tmp = a;
            for (int i = 0; i < idx; i++) {
                if (isWhite[i]) {
                    tmp += b;
                    tmp %= MOD;
                }
                else {
                    tmp *= c;
                    tmp %= MOD;
                }
//                System.out.print((isWhite[i])? 'w' : 'b');
//                System.out.print(' ');
            }
            for (int i = idx; i < groups.length; i++) {
                tmp *= c;
                tmp %= MOD;
//                System.out.print('b');
//                System.out.print(' ');
            }
//            System.out.println();
//            System.out.println(tmp);
//            System.out.println();
            if (tmp > ans) {
                ans = (int)tmp;
            }
            return;
        }

        isWhite[idx] = true;
        makeGroups(whiteused + 1, blackused, idx + 1);
        if (whiteused > blackused) {
            isWhite[idx] = false;
            makeGroups(whiteused, blackused + 1, idx + 1);
        }
    }
}