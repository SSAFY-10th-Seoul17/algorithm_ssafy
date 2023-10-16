import java.util.*;
import java.io.*;

/**
 * 1초이지만 N의 범위가 작아 모든 경우의 수를 고려해주어도 괜찮겠다고 판단했습니다.
 * ArrayList로 구현하였으나, 배열로 해도 상관 없을 것 같습니다.
 * 1. 첫번째, 마지막 구슬을 제외한 구슬들의 순열을 구합니다.
 * 2. 구슬을 고르는 것은 visited 배열을 true로 만드는 것으로 대체하고, 고른 구슬의 왼쪽, 오른쪽을 구해서 곱한 후 더해줍니다.

 * */

public class BOJ16198_에너지모으기 {
    static int N,result,sum;
    static List<Integer> list;
    static int[] picks;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        // n개의 에너지 구슬 , 모을 수 있는 에너지 양의 최댓값
        // 1. 첫번째 , 마지막 제외하고 구슬 고르기
        // 2. 양 옆 에너지 곱해서 모으기
        // 3. 해당 구슬 삭제하고 다시 줄세우기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        picks = new int[N-2];
        visited = new boolean[N];
        solve(0);
        System.out.println(result);

    }

    private static void solve(int cnt){ // 순열 구하기
        if (cnt == N - 2){ // 순열이 다 만들어지면
            sum = 0;
            Arrays.fill(visited, false);
            for (int i = 0; i< N - 2; i++){
                int p = picks[i];
                visited[p] = true;
                int left = 1, right = 1;

                for (int j = p; j >= 0; j--){
                    if (!visited[j]){
                        left = list.get(j);
                        break;
                    }
                }

                for (int j = p; j < N; j++){
                    if (!visited[j]){
                        right = list.get(j);
                        break;
                    }
                }
                sum += left * right;
            }
            result = Math.max(result, sum);
            return;
        }
        int size = list.size();
        for (int i = 1; i < size-1; i++){
            if (visited[i]){
                continue;
            }
            picks[cnt] = i;
            visited[i] = true;
            solve(cnt+1);
            visited[i] = false;
        }
    }
}
