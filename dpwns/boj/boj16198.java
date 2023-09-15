import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] w, seq, visited;
    public static int n, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        seq = new int[n-2];
        visited = new int[n];
        dfs(0);
        System.out.println(max);
        br.close();
    }
    public static void dfs(int cnt) {
        if(cnt == n-2) {
            // 에너지 계산
            calEnergy();
        }
        for (int i = 1; i < n-1; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                seq[cnt] = i;
                dfs(cnt + 1);
                visited[i] = 0;
            }
        }
    }
    public static void calEnergy() {
        int[] isUsed = new int[n];
        int res = 0;
        for (int i = 0; i < n-2; i++) {
            int left = seq[i] - 1, right = seq[i] + 1;
            while(isUsed[left] != 0) left--;
            while(isUsed[right] != 0) right++;
            isUsed[seq[i]] = 1;
            res += w[left] * w[right];
        }
        max = Math.max(max, res);
    }
}
