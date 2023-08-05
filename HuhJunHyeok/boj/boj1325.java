import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 1325. 효율적인 해킹
 */
public class boj1325 {
    static int n, m, count, max = Integer.MIN_VALUE;
//    static boolean[][] checkTrust; // 인접행렬. O[N^2], O[V^2]. n의 범위가 10000보다 같은 자연수 -> n^2이면 1억.
    static ArrayList<ArrayList<Integer>> checkTrust = new ArrayList<>(); // 인접리스트. O[V+E]
    static boolean[] visited;
    static int[] countArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        countArr = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            checkTrust.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            // A B 형태로 주어지는데, 이는 A가 B를 신뢰한다는 의미. 이 경우 B를 해킹하면 A도 해킹 가능.
            // 리스트에 넣을 때 get(B).add(A) 형태로 넣음.
            st = new StringTokenizer(br.readLine(), " ");
            int trust = Integer.parseInt(st.nextToken());
            int trusted = Integer.parseInt(st.nextToken());
            checkTrust.get(trusted).add(trust);
        }

        for(int i = 1; i <= n; i++) { // 각 컴퓨터를 돌면서 최대 해킹가능 수 체크
            count = 0;
            visited = new boolean[n + 1];
            bfs(i);
            countArr[i] = count;
            max = count > max ? count : max;
        }

        findMaxCom();

        System.out.print(sb.toString());
    }

    public static void bfs(int comNum) {
        Queue<Integer> possibleQueue = new ArrayDeque<>();

        possibleQueue.add(comNum);
        visited[comNum] = true;

        while(!possibleQueue.isEmpty()) {
            int v = possibleQueue.poll();

            for(int com: checkTrust.get(v)) {
                if(!visited[com]) {
                    possibleQueue.add(com);
                    visited[com] = true;
                    count++;
                }
            }
        }
    }

    public static void findMaxCom() {
        for(int i = 1; i <= n; i++) {
            if(countArr[i] == max) {
                sb.append(i).append(" ");
            }
        }
        sb.append("\n");
    }
}
