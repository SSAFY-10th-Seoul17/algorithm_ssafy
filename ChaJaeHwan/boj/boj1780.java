import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1780 {

    static int N;
    static int[][] graph;
    static int[][] nine = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

    static int m1 = 0, z = 0, p1 = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0,N);
        System.out.println(m1);
        System.out.println(z);
        System.out.println(p1);

    }

    static void solve(int r, int c, int w) {
        if(w ==1){
            if(graph[r][c] == -1) {
                m1 +=1;
            } else if(graph[r][c] == 0) {
                z += 1;
            } else {
                p1 += 1;
            }
            return;
        }
        if(allSame(r,c,w)) {
            if(graph[r][c] == -1) {
                m1 +=1;
            } else if(graph[r][c] == 0) {
                z += 1;
            } else {
                p1 += 1;
            }
        } else {
            w /= 3;
            for(int i = 0; i < 9; i++) {
                solve(r +nine[i][0]*w, c + nine[i][1]*w , w );
            }
        }
    }

    static boolean allSame(int r, int c, int w) {
        int p = graph[r][c];
        for(int i = r; i < r +w ; i++) {
            for(int j = c; j < c + w; j++){
                if(graph[i][j] != p) {
                    return false;
                }
            }
        }
        return true;
    }
}
