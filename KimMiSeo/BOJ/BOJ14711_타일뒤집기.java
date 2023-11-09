import java.io.*;
import java.util.*;
// 11:04 - 11:55
public class BOJ14711_타일뒤집기 {
    static int N;
    static char[][] tile;
    static int[][] map;
    static Queue<int[]> q;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        // 0으로 초기화된 타일
        // . # 으로 된 타일
        // # 인 부분 (좌표) 큐에 넣기 , 큐에 있으면 꺼내서 +1 퍼뜨리기, (첫줄만)
        // 다음 줄부터 시작 -> 위에 홀수가 있으면 -> 타일에 # 저장, 1퍼뜨리기 ->
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        tile = new char[N][N];
        map = new int[N][N];
        q = new LinkedList<>();

        String first = br.readLine();
        tile[0] = first.toCharArray(); // 첫줄 세팅

        for (int i=0; i<N; i++){
            if (first.charAt(i) == '#'){ // 검은 색이면
                map[0][i] = 1;
                q.add(new int[] {0,i});
            }
        }

        while(!q.isEmpty()){
            int[] polls = q.poll();
            // # 인 부분 (좌표) 큐에 넣기 , 큐에 있으면 꺼내서 +1 퍼뜨리기, (첫줄만)
            int r = polls[0];
            int c = polls[1];
            map[r][c] += 1;

            for (int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >=0 && nr<N && nc>=0 && nc<N){ // 범위 안에 있으면
                    map[nr][nc] += 1;
                }
            }
        }

        // 다음 줄부터 시작 -> 위에 홀수가 있으면 -> 타일에 # 저장, 1퍼뜨리기
        for (int i=1; i<N; i++){
            for (int j=0; j<N; j++){
                if (map[i-1][j] % 2 == 1){ // 홀수가 있으면
                    tile[i][j] = '#';

                    for (int d=0; d<4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr >=0 && nr<N && nc>=0 && nc<N){ // 범위 안에 있으면
                            map[nr][nc] += 1;
                        }
                    }
                } else{
                    tile[i][j] = '.';
                }
            }
        }

        for(int i=0; i<N; i++){
            sb.append(String.valueOf(tile[i])).append("\n");

        }
        System.out.println(sb);
    }
}
