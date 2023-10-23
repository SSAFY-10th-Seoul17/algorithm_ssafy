import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [boj] 14699. 관악산 등산
 */
public class boj14699 {
    /**
     * N: 쉼터의 수, M: 두 쉼터 사이를 연결하는 길의 수
     */
    static int N, M;
    /**
     * heights: 각 쉼터가 있는 높이
     * visitCounts: 각 쉼터별 최종적으로 몇 개의 쉼터를 방문할 수 있는지
     */
    static int[] heights, visitCounts;
    /**
     * adjList: 인접 리스트
     */
    static ArrayList<Integer>[] adjList;
    /**
     * isAccessible: 각 쉼터가 다른 쉼터를 통해 진입 가능한 쉼터인지
     * 다른 쉼터를 통해 진입할 수 없으면 시작 쉼터로 가정.
     * 다른 쉼터를 통해 진입할 수 있으면 윗 줄의 시작 쉼터로부터 해당 쉼터의 결과값을 도출 가능.
     */
    static boolean[] isAccessible;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 쉼터는 1~N번 ==> index 0 사용하지 않을 예정.
        isAccessible = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        heights = new int[N + 1];
        visitCounts = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            visitCounts[i] = 1; // 일단 i번 쉼터는 무조건 방문.
            heights[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int endPointOne = Integer.parseInt(st.nextToken());
            int endPointTwo = Integer.parseInt(st.nextToken());

            // 양 끝점이 같은 쉼터는 없다.
            if(heights[endPointOne] < heights[endPointTwo]) {
                isAccessible[endPointTwo] = true;
                adjList[endPointOne].add(endPointTwo);
            } else {
                isAccessible[endPointOne] = true;
                adjList[endPointTwo].add(endPointOne);
            }
        }

        for(int i = 1; i <= N; i++) {
            if(!isAccessible[i]) {
                simulation(i);
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(visitCounts[i]).append("\n");
        }

        System.out.print(sb.toString());
    }

    /**
     * 재귀를 이용한 dfs 방식.(memoization 사용).
     * @param nowIdx
     * @return
     */
    public static int simulation(int nowIdx) {
        for(int nextIdx: adjList[nowIdx]) {
            if(visitCounts[nextIdx] > 1) { // 다음 쉼터가 이미 방문한 쉼터인 경우
                visitCounts[nowIdx] = Math.max(visitCounts[nowIdx], visitCounts[nextIdx] + 1);
            } else {
                int possible = 1; // 현재 쉼터에서 진행할 경우 방문 가능한 쉼터의 수
                possible += simulation(nextIdx);

                if(visitCounts[nowIdx] > 1) { // 현재 쉼터가 이미 방문한 쉼터인 경우
                    visitCounts[nowIdx] = Math.max(visitCounts[nowIdx], possible);
                } else {
                    visitCounts[nowIdx] = possible;
                }
            }
        }

        return visitCounts[nowIdx];
    }
}
