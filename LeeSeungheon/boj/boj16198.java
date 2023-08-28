import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj16198 {

    static int[] map;
    static int N;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[N];
        int max = 0;
        for (int i = 1; i < N - 1; i++) {
            visited[i] = true;
            solve(i , visited, 0);
            visited[i] = false;
        }
        System.out.println(result);
    }

    private static void solve(int num, boolean[] visited, int energy) {

        int minIndex = num;
        int maxIndex = num;

        while (visited[--minIndex]);
        while (visited[++maxIndex]);
        energy += map[minIndex] * map[maxIndex];
        if (minIndex == 0 && maxIndex == N - 1) {
            result = Math.max(result ,energy );
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            solve(i , visited , energy);
            visited[i] = false;
        }
    }




}
