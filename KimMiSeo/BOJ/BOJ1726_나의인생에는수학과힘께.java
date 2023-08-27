import java.util.*;
import java.io.*;

/**
 * 가중치가 없는 최단 거리이므로 bfs를 사용해아한다고 생각했으나, 숫자를 누적해서 저장하는 형태가 아닌, 선택한 길을 모두 저장해야 하므로
 * dfs를 사용하는 것이 더 적절할 것 같다는 생각을 했습니다. (n의 범위도 작아서 시간상으로도 괜찮을 것 같다는 생각을 했습니다.)
 * dfs를 활용해서 최단 경로를 리스트에 담아주고, (n,n)에 도달하면 저장해둔 최단 경로를 따라가면서 계산해주었습니다.
 * 계산 방식은 연산자가 나오면 stack에 넣어주고, 숫자가 나오면 stack에서 연산자를 꺼내서 이전 sum과 계산해주는 방식으로 구현했습니다.
 */
public class BOJ1726_나의인생에는수학과힘께 {
    static int N;
    static char[][] graph;
    static int maxvalue, minvalue;
    public static void main(String[] args) throws Exception{
        // n x n 바둑판, 숫자와 연산자의 연산결과의 최댓값, 최솟값
        // 항상 최단거리로 이동한다 -> 오른쪽, 아래쪽으로만 이동해야 한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = st.nextToken().charAt(0);
            }
        } // graph

        visited = new boolean[N][N];
        list = new ArrayList<Character>();
        maxvalue = Integer.MIN_VALUE;
        minvalue = Integer.MAX_VALUE;
        bfs(0,0);
        sb.append(maxvalue).append(" ").append(minvalue);
        System.out.println(sb.toString());
    }
    // 오른쪽 아래
    static int[] dr = {0,1};
    static int[] dc = {1,0};
    static boolean[][] visited;
    static ArrayList<Character> list;
    private static void bfs(int r, int c){
        if (r == N-1 && c == N-1){
            // 리스트에서 꺼내서 계산
            Stack<Character> stack = new Stack<>();
            int size = list.size();
            int sum = 0;
            for (int i = 0; i < size; i++){
                int temp = list.get(i) - '0';
                if ( temp >= 0 && temp <= 5){ // 숫자일때
                    if (stack.isEmpty()){
                        sum += temp;
                    }else{ // 스택이 비어있지 않다면
                        char col = stack.pop();
                        switch (col){
                            case '*' :
                                sum = sum * temp;
                                break;
                            case '+' :
                                sum += temp;
                                break;
                            case '-' :
                                sum -= temp;
                        }
                    }
                }
                else{
                    stack.push(list.get(i));
                }
            }

            minvalue = Math.min(minvalue, sum);
            maxvalue = Math.max(maxvalue, sum);
            return;
        }
        visited[r][c] = true;
        if (r == 0 && c == 0){
            list.add(graph[r][c]);
        }

        for(int i = 0; i < 2; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >=0 && nr < N && nc>=0 && nc < N && !visited[nr][nc]){ // 범위안에 있고 방문하지 않았으면
                visited[nr][nc] = true;
                list.add(graph[nr][nc]);
                bfs(nr, nc);
                list.remove(list.size()-1);
                visited[nr][nc] = false;
            }
        }
    }
}
