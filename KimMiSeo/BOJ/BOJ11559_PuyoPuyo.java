import java.util.*;
import java.io.*;

/**
 * 12 x 6 이기 때문에 시간의 제한이 많이 없다고 생각했습니다.
 * bfs로 뿌요가 4개 이상인 경우 모두 . 으로 바꿔줍니다. (bomb 함수)
 * 이때, 뿌요의 개수뿐만 아니라, 뿌요가 존재하는 위치도 필요하기 때문에 toChangeQueue를 따로 운영해 뿌요의 위치를 저장해주었습니다.
 * 그 이후 모든 행마다 뿌요를 바닥으로 옮겨주었습니다. -> . 가 아니라면 큐에 담고 . 로 변경 후 , 바닥부터 뿌요로 채워줍니다.
 */
public class BOJ11559_PuyoPuyo {
    static char[][] map;
    static int result;
    static boolean flag,bombflag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i=0; i<12; i++){
            String temp = br.readLine();
            for (int j=0; j<6; j++) {
                map[i][j] = temp.charAt(j);
            }
        }
        // 알파벳일 경우 dfs 하고, 4 이상이면 터뜨리기
        result = 0;
        flag = true;

        while(flag){ // 터뜨릴게 있다면
            visited = new boolean[12][6];
            bombflag = false;
            for (int i=0; i<12; i++){
                for (int j=0; j<6; j++){
                    if (map[i][j] != '.' && !visited[i][j]){ // 뿌요가 있고, 방문하지 않았으면
                        toChangeQueue = new LinkedList<>();
                        toChangeQueue.offer(new int[] {i,j});
                        bfs(i, j, map[i][j]);
                        if (toChangeQueue.size() >= 4 ){ // 4개가 넘으면 터뜨리기
                            bombflag = true;
                            bomb();
                        }else{ // 4개가 넘지 않으면 초기화 - 안해줘도 괜찮을 듯!
                            toChangeQueue.clear();
                        }
                    }
                }
            }

            if (bombflag){
                result++;
            }else{ // 더이상 터뜨릴 게 없음
                break;
            }
            // 바닥으로
            toBottom();
        }
        System.out.println(result);
    }

    static int[] dr = {-1, 1, 0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;
    static Queue<int[]> toChangeQueue;
    private static void bfs(int r, int c, char color){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()){
            int[] polls = queue.poll();
            int rr = polls[0];
            int cc = polls[1];

            for (int i=0; i<4; i++){
                int nr = rr + dr[i];
                int nc = cc + dc[i];
                // 범위 안에 있고, 방문하지 않았고, 색이 같은 경우
                if (nr >=0 && nr <12 && nc>=0 && nc<6 && !visited[nr][nc] && map[nr][nc] == color){
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr,nc});
                    toChangeQueue.offer(new int[] {nr,nc});
                }
            }
        }
    }

    private static void bomb(){ // 터뜨리기
        while(!toChangeQueue.isEmpty()){
            int[] polls = toChangeQueue.poll();
            int r = polls[0];
            int c = polls[1];
            map[r][c] = '.';
        }
    }

    private static void toBottom(){
        Queue<Character> q = new LinkedList<>();
        for (int i=0; i<6; i++){
            for (int j=11; j>=0; j--){
                if (map[j][i] != '.'){ // 알파벳이면
                    q.offer(map[j][i]);
                    map[j][i] = '.';
                }
            }

            int j = 11;
            while(!q.isEmpty()){
                map[j][i] = q.poll();
                j--;
            }
        }
    }
}
