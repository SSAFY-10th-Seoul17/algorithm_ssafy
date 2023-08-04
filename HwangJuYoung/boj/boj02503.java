import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] answer = new boolean[988];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = true;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for (int j = 123; j <= 987; j++) {
                if (StrikeCheck(j, q) == s && BallCheck(j, q) == b && EachDiffNum(j) && answer[j]) {
                    answer[j] = true;
                } else {
                    answer[j] = false;
                }
            }
        }
        int cnt = 0;
        for (int j = 123; j <= 987; j++) {
            if (answer[j] == true) {
                cnt++;
            }
        }
        System.out.println(cnt);
    } // end of main

    static int StrikeCheck(int A, int Q) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (A % 10 == Q % 10) {
                cnt++;
            }
            A /= 10;
            Q /= 10;
        }

        return cnt;
    } // end of StrickeCheck

    static int BallCheck(int A, int Q) {
        int cnt = 0;

        String sA = "" + A;
        String sQ = "" + Q;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                if (sA.charAt(i) == sQ.charAt(j)) {
                    cnt++;
                }
            }
        }

        return cnt;
    } // end of BallCheck

    static boolean EachDiffNum(int num) {
        int a = num / 100;
        int b = num % 100 / 10;
        int c = num % 10;

        if (a == b || b == c || c == a) return false;
        if (a == 0 || b == 0 || c == 0) return false;
        return true;
    } // end of EachDiffNum

} // end of class

