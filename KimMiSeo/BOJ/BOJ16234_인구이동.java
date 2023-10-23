import java.io.*;
import java.util.*;

/**
 * bfs로 사방 탐색을 하면서 연합을 이루고 있는 칸의 좌표를 큐에 담았습니다.
 * 또, 연합칸의 인구수와 칸의 개수를 넘겨주어 새로운 배열에 적어주었습니다.
 * 새로운 배열을 기존 배열에 복사하여 인구이동이 다시 일어나게 했습니다.
 */
public class BOJ16234_인구이동 {
    static int N,L,R,result,visit,cnt,sum,isAble;
    static int[][] map, newMap, visited;
    static Queue<int[]> memoq = new LinkedList<>();
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // nxn 크기 땅
        L = Integer.parseInt(st.nextToken()); // L명 이상
        R = Integer.parseInt(st.nextToken()); // R명 이하

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }// 입력

        visit = 1;
        while(true){
            isAble = 0;
            newMap = new int[N][N];
            visited = new int[N][N];

            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    if (visited[i][j] == 0){
                        isAble++;
                        bfs(i,j);
                        into();
                    }
                }
            }
            copy(map,newMap);
            if (isAble == N*N){
                break;
            }
            result++;
        }
        System.out.println(result);
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    // 4방 확인하면서 국경선 뚫리면 더하기, ++ , 큐에 좌표 담아두기
    private static void bfs(int r, int c){
       // System.out.println(r+" "+c);
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r,c});
        memoq.offer(new int[] {r,c});
        visited[r][c] = visit;
        sum = map[r][c];
        cnt = 1;

        while(!q.isEmpty()){
            int[] polls = q.poll();
            int rr = polls[0];
            int cc = polls[1];

            for (int i=0; i<4; i++){
                int nr = rr + dr[i];
                int nc = cc + dc[i];

                if (nr>=0 && nr<N && nc>=0 && nc<N && visited[nr][nc]==0 ){
                    int diff =  Math.abs(map[rr][cc] - map[nr][nc]);
                    if (diff>=L && diff<=R){
                        visited[nr][nc] = visit;
                        sum+= map[nr][nc];
                        cnt++;
                        memoq.offer(new int[] {nr,nc});
                        q.offer(new int[] {nr,nc});
                    }
                }
            }
        }
    }

    private static void into(){
        int value = sum / cnt;
        // 새로운 맵에 넣기
        while(!memoq.isEmpty()){
            int[] polls = memoq.poll();
            int r = polls[0];
            int c = polls[1];

            newMap[r][c] = value;
        }
    }

    private static void copy(int[][] map, int[][] newMap){
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                map[i][j] = newMap[i][j];
            }
        }
    }

    private static void print(int[][] d){
        for (int i=0; i<N; i++){
            System.out.println(Arrays.toString(d[i]));
        }
    }
}
