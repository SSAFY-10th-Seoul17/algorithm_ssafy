import java.util.*;
import java.io.*;

class Main{
    public static StringBuilder sb = new StringBuilder();
    public static int n;
    public static int[] seq, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        seq = new int[n];
        visited = new int[n+1];
        permutation( 0);

        System.out.print(sb);

        br.close();
    }

    public static void permutation(int cnt) {
        if(cnt == n) {
            Arrays.stream(seq).forEach(s -> sb.append(s).append(" "));
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++) {
            if(visited[i] == 0) {
                seq[cnt] = i;
                visited[i] = 1;
                permutation(cnt+1);
                visited[i] = 0;
            }
        }
    }
}