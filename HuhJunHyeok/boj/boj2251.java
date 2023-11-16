import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * [boj] 2251. 물통
 */
public class boj2251 {
    static int[] maxWaterOfBottles = new int[3];
    static int maxWater;
    static boolean[][] visited;
    static TreeSet<Integer> cWaters = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 3; i++) {
            maxWaterOfBottles[i] = Integer.parseInt(st.nextToken());

            if(maxWaterOfBottles[i] > maxWater) {
                maxWater = maxWaterOfBottles[i];
            }
        }
        visited = new boolean[maxWater + 1][maxWater + 1];

        dfs(0, 0, maxWaterOfBottles[2]);

        for(int water: cWaters) {
            sb.append(water).append(" ");
        }
        sb.append("\n");

        System.out.print(sb.toString());
    }

    public static void dfs(int a, int b, int c) {
        if(visited[a][b]) {
            return;
        }

        if(a == 0) {
            cWaters.add(c);
        }

        visited[a][b] = true;

        int ab = a + b;
        int ac = a + c;
        int bc = b + c;

        // a to b
        if(ab > maxWaterOfBottles[1]) {
            dfs(ab - maxWaterOfBottles[1], maxWaterOfBottles[1], c);
        } else {
            dfs(0, ab, c);
        }

        // a to c
        if(ac > maxWaterOfBottles[2]) {
            dfs(ac - maxWaterOfBottles[2], b, maxWaterOfBottles[2]);
        } else {
            dfs(0, b, ac);
        }

        // b to a
        if(ab > maxWaterOfBottles[0]) {
            dfs(maxWaterOfBottles[0], ab - maxWaterOfBottles[0], c);
        } else {
            dfs(ab, 0, c);
        }

        // b to c
        if(bc > maxWaterOfBottles[2]) {
            dfs(a, bc - maxWaterOfBottles[2], maxWaterOfBottles[2]);
        } else {
            dfs(a, 0, bc);
        }

        // c to a
        if(ac > maxWaterOfBottles[0]) {
            dfs(maxWaterOfBottles[0], b, ac - maxWaterOfBottles[0]);
        } else {
            dfs(ac, b, 0);
        }

        // c to b
        if(bc > maxWaterOfBottles[1]) {
            dfs(a, maxWaterOfBottles[1], bc - maxWaterOfBottles[1]);
        } else {
            dfs(a, bc, 0);
        }
    }
}
