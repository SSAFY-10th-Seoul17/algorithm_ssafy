import java.util.*;
import java.io.*;
public class BOJ2666_벽장문의이동 {
    static int N,open1,open2,L,result; // 벽장의 개수, 열려있는 위치, 사용할 벽장 순서 길이
    static boolean[] doors; // 문
    static int[] play;
    static int[][][] memo;
    public static void main(String[] args) throws Exception {
        // n개의 벽장 일렬, 문은 n-2개
        // 문이 없으면 앞으로 움직일 수 있음
        // 문은 한칸씩 이동 가능
        // 사용할 벽장의 순서에 따라 벽장문을 이동하는 순서를 찾기
        // 벽장문의 이동횟수 최소
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        open1 = Integer.parseInt(st.nextToken());
        open2 = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(br.readLine());
        play = new int[L];
        result = Integer.MAX_VALUE;
        memo = new int[L][N+1][N+1];

        doors = new boolean[N+1];
        Arrays.fill(doors, true);
        doors[open1] = false; // 비어있음을 나타냄
        doors[open2] = false;

        for (int i=0; i<L; i++){
            play[i] = Integer.parseInt(br.readLine()); // 비어있어야 함
        }
        result = solve(0,open1, open2);
        System.out.println(result);
    }
    private static int solve(int index, int open1, int open2){
        if (index == L){
//            System.out.println(index+" "+open1+" "+open2);
            return 0;
        }else if (memo[index][open1][open2] > 0){
            return memo[index][open1][open2];
        }
        // 첫번째 문
        int c = Math.abs(open1 - play[index]);
        doors[play[index]] = false; // 문 없음
        doors[open1] = true; // 문 있음

        int cc = Math.abs(open2 - play[index]);
        doors[play[index]] = false;
        doors[open2] = true;

        return memo[index][open1][open2] =  Math.min(
                c + solve(index+1, play[index], open2),
                cc + solve(index+1, open1, play[index])
        );

//        return memo[index][open1][open2];
    }
}
