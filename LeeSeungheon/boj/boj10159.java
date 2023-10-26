import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj10159 {

    static boolean[][] map;
    static boolean[] visited;

    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()) + 1;
        int M = Integer.parseInt(br.readLine());

        map = new boolean[N][N];
        visited = new boolean[N];
        for (int relation = 0; relation < M; relation++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        for (int num = 1; num < N; num++) {
            Arrays.fill(visited,false);
            sum = 2;
            topDown(num);
            bottomUp(num);
            sb.append(N - sum).append("\n");
        }
        System.out.println(sb);
    }

    private static void bottomUp(int num) {

        for (int row = 1; row < map.length ; row++) {
            if(map[row][num] && !visited[row]){
                visited[row] = true;
                sum ++;
                bottomUp(row);
            }
        }
    }

    private static void topDown(int num) {

        for (int column = 1; column < map.length ; column++) {
            if(map[num][column] && !visited[column]){
                visited[column] = true;
                sum ++;
                topDown(column);
            }
        }
    }
}
