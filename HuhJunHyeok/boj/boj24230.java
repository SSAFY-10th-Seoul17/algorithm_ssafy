import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 24230. 트리 색칠하기
 */
public class boj24230 {
    static int n, count;
    static int[] color;
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static boolean[] isVisited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        color = new int[n + 1];
        isVisited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        node.add(null);
        for(int i = 1; i <= n; i++) {
            color[i] = Integer.parseInt(st.nextToken());
            node.add(new ArrayList<>());
        }

//        for(int i = 0; i <= n; i++) {
//            node.add(new ArrayList<>());
////            node.get(i).add(0);
//        }

        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            node.get(left).add(right);
            node.get(right).add(left);
        }

        paintingDfs(1, 0);

        System.out.println(count);
    }

    public static void paintingDfs(int idx, int clr) {
        isVisited[idx] = true;
        ArrayList<Integer> infoList = node.get(idx);
        if(clr != color[idx]) { // 칠하는 색이 변경되어야 하는 경우
            count++;
//            infoList.set(0, color[idx]);
            clr = color[idx];
        }

//        for(int childIdx: infoList.subList(1, infoList.size())) {
        for(int childIdx: infoList) {
            if(!isVisited[childIdx]) {
                paintingDfs(childIdx, clr);
            }
        }
    }
}
