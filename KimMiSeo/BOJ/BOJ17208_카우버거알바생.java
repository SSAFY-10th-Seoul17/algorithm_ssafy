import java.util.*;
import java.io.*;

/**
 * 저번 주 스터디 문제였던 2666 - 벽장문의 이동과 거의 유사한 문제라고 생각해도 될 것 같습니다.
 * 재귀와 메모이제이션으로 문제를 풀었습니다.
 * i번째 주문을 받는 경우, 받지 않는 경우를 모두 고려했습니다.
 */
public class BOJ17208_카우버거알바생 {
    static int N,M,K,maxresult;
    static int[] hamburgers, fries;
    static int[][][] memo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 주문 수
        M = Integer.parseInt(st.nextToken()); // 버거
        K = Integer.parseInt(st.nextToken()); // 감튀

        hamburgers = new int[N+1];
        fries = new int[N+1];

        for (int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            hamburgers[i] = Integer.parseInt(st.nextToken());
            fries[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[M+1][K+1][N+1];
        for (int i=0; i<=M; i++){
            for (int j=0; j<=K; j++){
                for (int k=0; k<=N; k++){
                    memo[i][j][k] = -1;
                }
            }
        } // 초기화

        maxresult = solve(M,K,0);
        System.out.println(maxresult);
    }

    private static int solve(int leftburger, int leftfries, int index){
//        System.out.println(leftburger+" "+leftfries+" "+index);
        if (index == N){
            return 0;
        }
        if (memo[leftburger][leftfries][index] != -1){
            return memo[leftburger][leftfries][index];
        }

        int choose = 0;
        if (leftburger >= hamburgers[index+1] && leftfries >= fries[index+1]){ // 주문할 수 있으면
            choose = 1+solve(leftburger - hamburgers[index+1], leftfries - fries[index+1], index+1); // 주문
        }

        // 비주문
        int notChoose = solve(leftburger, leftfries, index+1);
        memo[leftburger][leftfries][index] = Math.max(choose, notChoose);
        return memo[leftburger][leftfries][index];
    }
}
