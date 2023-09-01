import java.io.*;
import java.util.*;
public class boj12761 {
    static int A, B, N, M , map[], dr[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dr = new int[]{A, B, -A, -B};

        map = new int[100001];

        bfs();

        System.out.println(map[M] - 1);
    }

    private static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        map[N] = 1;
        while(!q.isEmpty()){
            int node = q.poll();

            if(node == M){
                break;
            }

            int dx = node + 1;
            if(canGo(dx)){
                map[dx] = map[node] + 1;
                q.offer(dx);
            }

            dx = node - 1;
            if(canGo(dx)){
                map[dx] = map[node] + 1;
                q.offer(dx);
            }

            for (int dir : dr) {
                dx = node + dir;
                if(canGo(dx)){
                    map[dx] = map[node] + 1;
                    q.offer(dx);
                }
            }

            dx = node * A;
            if(canGo(dx)){
                map[dx] = map[node] + 1;
                q.offer(dx);
            }

            dx = node * B;
            if(canGo(dx)){
               map[dx] = map[node] + 1;
               q.offer(dx);
            }
        }

    }

    private static boolean canGo(int dx){
        return 0 <= dx && dx < 100001 && map[dx] == 0;
    }
}
