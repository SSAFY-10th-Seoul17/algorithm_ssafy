import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 10973. 이전 순열
 */
public class boj10973 {
    static int n;
    static int[] perm;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        perm = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            perm[i] = Integer.parseInt(st.nextToken());
        }

        prevPermutation();

        System.out.print(sb.toString());
    }

    /**
     * 이전 순열(PrevPermutation) 찾기
     */
    public static void prevPermutation() {
/*
        i와 j는 n - 1
        1. i > 0 이면서 perm[i] < perm[i-1]를 만족하는 i의 최댓값 찾기.
        2. perm[j] < perm[i-1]을 만족하는 j의 최댓값 찾기.
        3. perm[i-1]과 perm[j]를 swap.
        4. perm[i] ~ perm[n-1]까지 순열 reverse.

        즉, 다음 수열(NextPermutation) 찾기와 부등호 방향만 반대.
*/

        int lastIdx = n - 1;

        // 1번 수행. i > 0 이면서 perm[i] < perm[i-1]를 만족하는 i의 최댓값 찾기.
        int i = lastIdx;
        while(i > 0 && perm[i] >= perm[i - 1]) {
            i--;
        }

        if(i <= 0) { // 주어진 순열이 첫번째 순열인 경우
            sb.append(-1).append("\n");
            return;
        }

        // 2번 수행. perm[j] < perm[i-1]을 만족하는 j의 최댓값 찾기.
        int j = lastIdx;
        while(perm[j] >= perm[i - 1]) {
            j--;
        }

        // 3번 수행. perm[i-1]과 perm[j]를 swap.
        swap(i - 1, j);

        // 4번 수행. perm[i] ~ perm[n-1]까지 순열 reverse.
        for(int k = i, l = lastIdx; k < l; k++, l--) {
            swap(k, l);
        }

        for(int x = 0; x < n; x++) {
            sb.append(perm[x]);
            if(x != lastIdx) {
                sb.append(" ");
            }
        }
        sb.append("\n");
    }

    /**
     * 2개의 index 값을 받아 perm 배열에서 받은 index에 해당하는 값들을 swap.
     */
    public static void swap(int idx1, int idx2) {
        int temp = perm[idx1];
        perm[idx1] = perm[idx2];
        perm[idx2] = temp;
    }
}
