import java.io.*;
import java.util.*;
public class boj17265 {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int n;
    static int[][] dir = {{1, 0}, {0, 1}};
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }

        }
        sb.append(map[0][0]);
        recursive(0, 0);
        System.out.println(max + " " + min);
    }

    public static void recursive(int x, int y){
        if(x == n - 1 && y == n - 1){
            max = Math.max(max, calculate());
            min = Math.min(min, calculate());
            return;
        }
        for (int[] dr: dir) {
            int dx = x + dr[0];
            int dy = y + dr[1];

            if(dx < n && dy < n){
                sb.append(map[dx][dy]);
                recursive(dx, dy);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static int calculate(){
        int reuslt = sb.charAt(0) - '0';
        for (int i = 1; i < (n * 2) - 1; i+=2) {
            if(sb.charAt(i) == '+'){
                reuslt = reuslt + (sb.charAt(i + 1) - '0');
            } else if (sb.charAt(i) == '-') {
                reuslt = reuslt - (sb.charAt(i + 1) - '0');
            } else if(sb.charAt(i) == '*'){
                reuslt = reuslt * (sb.charAt(i + 1) - '0');
            }
        }
        return reuslt;
    }
}
