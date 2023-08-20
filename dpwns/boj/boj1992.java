import java.io.*;
import java.util.*;

class boj1992 {
    public static int[][] map;
    public static int n;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        divideQuad(0, 0, n, n);
        System.out.println(sb);
        br.close();
    }
    public static void divideQuad(int r1, int c1, int r2, int c2) {
        int[] elements = Arrays.stream(map, r1, r2)
                .flatMapToInt(m -> Arrays.stream(m, c1, c2))
                .toArray();
        if(Arrays.stream(elements).allMatch(e -> e == 0) || Arrays.stream(elements).allMatch(e -> e == 1)){
            sb.append(map[r1][c1]);
            return;
        }
        int nextC = (c1 + c2) / 2;
        int nextR = (r1 + r2) / 2;

        sb.append("(");
        divideQuad(r1, c1, nextR, nextC);
        divideQuad(r1, nextC, nextR, c2);
        divideQuad(nextR, c1, r2, nextC);
        divideQuad(nextR, nextC, r2, c2);
        sb.append(")");
    }
}