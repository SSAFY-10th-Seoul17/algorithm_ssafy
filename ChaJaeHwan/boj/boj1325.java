import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1325 {
    static int N, M;
    static ArrayList <Integer>[] arr;
    static boolean isVisited[];
    static int max;
    static int cntArr[];

    static void bfs(int start) {
        Queue <Integer> que = new ArrayDeque<>();

        que.add(start);
        isVisited[start] = true;

        while(!que.isEmpty()) {
            int now = que.poll();
            for (int i : arr[now]) {
                if (isVisited[i]) continue;
                cntArr[i]++;
                isVisited[i] = true;
                que.add(i);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N+1];
        cntArr = new int[N+1];

        arr = new ArrayList[N+1];
        for (int i=0; i<N+1; i++) arr[i] = new ArrayList <>();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }


        for (int i=1; i<N+1; i++) {
            isVisited = new boolean[N+1];
            bfs(i);

        }

        for (int i=1; i<N+1; i++) {
            if (max<cntArr[i]) max = cntArr[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<N+1; i++) {
            if (max == cntArr[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

}