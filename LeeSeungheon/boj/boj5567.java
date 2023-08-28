import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj5567 {

    static ArrayList<ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        map = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
        }
        solve(1, N);
    }

    private static void solve(int sang, int n) {

        boolean[] cnt = new boolean[n + 1];

        for (int i : map.get(sang)) {
            cnt[i] = true;
            for (int j : map.get(i)) {
                cnt[j] = true;
            }
        }

        int cntt = 0;
        cnt[sang] = false;
        for (boolean che : cnt) {
            if (che) {
                cntt++;
            }
        }

        System.out.println(cntt);
    }

}
