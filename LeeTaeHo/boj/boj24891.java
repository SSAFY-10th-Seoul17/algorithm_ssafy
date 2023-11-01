import java.io.*;
import java.util.*;

public class boj24891 {
    static int l, n;
    static String[] words, result;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        words = new String[n];
        result = new String[l];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words);

        StringBuilder sb = new StringBuilder();

        if(dfs(0)){
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    sb.append(result[i].charAt(j));
                }
                sb.append("\n");
            }
        }else{
            sb.append("NONE");
        }

        System.out.println(sb);

    }

    public static boolean dfs(int depth){
        if(depth == l){
            if(isPossible(result)){
                return true;
            }
            return false;
        }

        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                visited[i] = true;
                result[depth] = words[i];
                if(dfs(depth + 1)){
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    public static boolean isPossible(String[] result){
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (result[i].charAt(j) != result[j].charAt(i)) return false;
            }
        }
        return true;
    }
}