import java.util.*;
import java.io.*;

class Main{
    public static int[][] map = new int[5][5], direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static Set<String> set = new HashSet<>();
    public static int[] seq = new int[6];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                seq[0] = map[i][j];
                dfs(i, j, 0);
            }
        }
        System.out.println(set.size());
        br.close();
    }

    public static void dfs(int r, int c, int cnt) {
        if(cnt == 6) {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(seq).forEach(s -> sb.append(s));
            set.add(sb.toString());
            return;
        }

        for(int i=0; i<direction.length; i++) {
            int nextR = r + direction[i][0];
            int nextC = c + direction[i][1];
            if(checkPos(nextR, nextC)) {
                seq[cnt] = map[nextR][nextC];
                dfs(nextR, nextC, cnt+1);
            }
        }
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=5 || c<0 || c>=5) ? false : true;
    }
}