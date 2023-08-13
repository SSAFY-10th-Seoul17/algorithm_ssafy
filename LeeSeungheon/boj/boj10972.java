import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj10972 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] map = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(map));
    }

    private static StringBuilder solve(int[] map) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[map.length];
        Arrays.fill(visited, true);
        visited[map[visited.length - 1]] = false;

        int cur = 0;

        for (int i = visited.length - 2; i >= 0; i--) {

            if(i == 0){
                return sb.append(-1);
            }

            visited[map[i]] = false;
            if (map[i] < map[i + 1]) {
                cur = i;
                break;
            }

        }

        for (int i = map[cur] + 1; i < visited.length; i++) {
            if (!visited[i]) {
                map[cur] = i;
                visited[i] = true;
                break;
            }
        }

        for (int i = cur + 1; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if(!visited[j]){
                    visited[j] = true;
                    map[i] = j;
                    break;
                }
            }
        }

        for (int i = 1; i < map.length; i++) {
            sb.append(map[i]).append(" ");
        }
        return sb;
    }
}
