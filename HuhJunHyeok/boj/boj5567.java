import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 5567. 결혼식
 */
public class boj5567 {
    /**
     * N: 동기의 수, M: 리스트의 길이, maxInvitationNum: 초대하는 동기의 수
     */
    static int N, M, maxInvitationNum;
    static boolean[] visited;
    static ArrayList<Integer>[] friendship;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        friendship = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            friendship[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            friendship[from].add(to);
            friendship[to].add(from);
        }

        invite();

        System.out.println(maxInvitationNum);
    }

    public static void invite() {
        visited[1] = true;

        if(friendship[1].size() == 0) { // 상근이의 친구가 없는 경우.
            return;
        }

        maxInvitationNum += friendship[1].size();

        Queue<Integer> queue = new LinkedList<>();
        for(int friend: friendship[1]) {
            if(friendship[friend].size() != 0) {
                queue.offer(friend);
            }
            visited[friend] = true;
        }

        while(!queue.isEmpty()) {
            int friend = queue.poll();
            for(int adj: friendship[friend]) {
                if(!visited[adj]) {
                    visited[adj] = true;
                    maxInvitationNum++;
                }
            }
        }
    }
}
