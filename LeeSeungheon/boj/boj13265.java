import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj13265 {

    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Circle[] map = new Circle[N + 1];

            for (int i = 1; i <= N; i++) {
                map[i] = new Circle(i, null);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());

                map[first].nextCircle = new Circle(second, map[first].nextCircle);
                map[second].nextCircle = new Circle(first, map[second].nextCircle);
            }
            visited = new int[map.length];
            boolean check = true;
            for (int i = 1; i <= N; i++) {
                if (visited[i] != 0) {
                    continue;
                }
                if(!solve(map, i, 1, 0)){
                    check = false;
                    break;
                }
            }

            sb.append(check ? "possible" : "impossible").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean solve(Circle[] map, int index , int sort, int parent) {

        visited[index] = sort;
        for(Circle nextCircle = map[index].nextCircle; nextCircle != null; nextCircle = nextCircle.nextCircle){
            if (visited[nextCircle.index] != 0) {
                if (visited[nextCircle.index] == sort) {
                    return false;
                }
                continue;
            }
            if(!solve(map, nextCircle.index, sort == 1 ? 2 : 1, index)){
                return false;
            }
        }
        return true;
    }

    private static class Circle {

        int index;
        Circle nextCircle;

        public Circle(int index, Circle nextCircle) {
            this.index = index;
            this.nextCircle = nextCircle;
        }
    }

}
