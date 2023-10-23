import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj17297 {

    private static final int MESSI_NUM = 5;
    private static final int SPACE_NUM = 1;
    private static final int GIMOSSI_NUM = 7;
    private static final String MG = " Messi Gimossi";
    private static ArrayList<Integer> dp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char solve = solve(N, getLeng(N));
        System.out.println(solve == ' ' ? "Messi Messi Gimossi" : solve);
    }

    private static int getLeng(int n) {
        dp.add(0);
        dp.add(MESSI_NUM);
        dp.add(MESSI_NUM + SPACE_NUM + GIMOSSI_NUM);

        int idx = 1;
        while (n > dp.get(++idx)) {
            dp.add(dp.get(idx - 1) + SPACE_NUM + dp.get(idx));
        }
        return idx - 1;
    }

    private static char solve(int N, int idx) {
        int i = 0;
        for(;dp.get(i) < N; i++);
        idx = i-1;

        if (N <= 13) {
            return MG.charAt(N);
        }

        if (N < dp.get(idx)) { // 왼쪽
            return solve(N, idx - 1);
        } else if (N > dp.get(idx) + SPACE_NUM) { // 오른쪽
            return solve(N - dp.get(idx) - SPACE_NUM, idx - 2);
        } else { // 공백
            return ' ';
        }
    }
}
