import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj]  17616. 등수 찾기
 */
public class boj17616 {
    /**
     * N: 학생의 수, M: 질문 횟수, X: 등수를 알고 싶은 학생
     * highest: 가능한 가장 높은 등수, lowest: 가능한 가장 낮은 등수
     */
    static int N, M, X, highest, lowest;
    /**
     * upperList: 해당 정점보다 높은 등수인 정점의 정보를 기록하는 인접리스트
     * lowerList: 해당 정점보다 낮은 등수인 정점의 정보를 기록하는 인접리스트
     */
    static ArrayList<Integer>[] upperList, lowerList;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        upperList = new ArrayList[N + 1];
        lowerList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            upperList[i] = new ArrayList<>();
            lowerList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int up = Integer.parseInt(st.nextToken());
            int down = Integer.parseInt(st.nextToken());

            upperList[down].add(up);
            lowerList[up].add(down);
        }

        visited = new boolean[N + 1];
        getHighest();

        visited = new boolean[N + 1];
        getLowest();

        System.out.println(highest + " " + lowest);
    }

    /**
     * 학생 X의 가능한 가장 높은 등수 구하기
     */
    public static void getHighest() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(X);
        visited[X] = true;

        int count = 0;
        while(!queue.isEmpty()) {
            int nowVertex = queue.poll();

            ArrayList<Integer> adjList = upperList[nowVertex];
            for(int adjVertex: adjList) {
                if(!visited[adjVertex]) {
                    count++;
                    visited[adjVertex] = true;
                    queue.offer(adjVertex);
                }
            }
        }

        highest = 1 + count;
    }

    /**
     * 학생 X의 가능한 가장 낮은 등수 구하기
     */
    public static void getLowest() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(X);
        visited[X] = true;

        int count = 0;
        while(!queue.isEmpty()) {
            int nowVertex = queue.poll();

            ArrayList<Integer> adjList = lowerList[nowVertex];
            for(int adjVertex: adjList) {
                if(!visited[adjVertex]) {
                    count++;
                    visited[adjVertex] = true;
                    queue.offer(adjVertex);
                }
            }
        }

        lowest = N - count;
    }
}
