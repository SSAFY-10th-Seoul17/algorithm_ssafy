import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 14567. 선수과목(Prerequisite)
 */
public class boj14567 {
    /**
     * N: 과목의 수, M: 선수 조건의 수, semester: 현재 학기
     */
    static int N, M, semester;
    /**
     * 과목별 선수 관계에 따른 리스트
     */
    static ArrayList<Integer>[] list;
    /**
     * inDegree: 진입 차수 배열, minSemester: 해당 과목을 이수하기 위한 최소 학기 배열
     */
    static int[] inDegree, minSemester;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];
        minSemester = new int[N + 1];
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            list[prev].add(next);
            inDegree[next]++;
        }

        semester = 1; // 학기
        topologySort();
        for(int i = 1; i <= N; i++) {
            sb.append(minSemester[i]).append(" ");
        }
        sb.append("\n");

        System.out.print(sb.toString());
    }

    /**
     * 위상 정렬 알고리즘 사용
     */
    public static void topologySort() {
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int now = queue.poll();

                minSemester[now] = semester;

                ArrayList<Integer> adj = list[now];
                for(int adjVertex: adj) {
                    inDegree[adjVertex]--;
                    if(inDegree[adjVertex] == 0) {
                        queue.offer(adjVertex);
                    }
                }
            }
            semester++;
        }
    }
}
