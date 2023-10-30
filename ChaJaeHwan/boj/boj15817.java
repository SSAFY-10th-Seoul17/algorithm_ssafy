import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15817 {

    static class Pipe{
        int length;
        int cnt;

        public Pipe(int length, int cnt) {
            this.length = length;
            this.cnt = cnt;
        }
    }
    static int N, X;
    static Pipe[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        p = new Pipe[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = new Pipe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


    }

}
