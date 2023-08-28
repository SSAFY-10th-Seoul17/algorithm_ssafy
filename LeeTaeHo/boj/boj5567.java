import java.io.*;
import java.util.*;
public class boj5567 {
    static int[] count;
    static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            list.get(to).add(from);
            list.get(from).add(to);
        }

        count = new int[n + 1];
        dfs(1, 0);
        int sum = 0;
        for (int i = 2; i <= n ; i++) {
            if(count[i] != 0) sum++;
        }
        System.out.println(sum);
    }

    public static void dfs(int start, int depth){
        if(depth == 2){
            return;
        }
        for(int next : list.get(start)){
            count[next]++;
            dfs(next, depth + 1);
        }
    }
}
