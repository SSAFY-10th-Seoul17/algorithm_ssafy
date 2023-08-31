package toyproject.somedaybucket.myAlgo.boj;

// boj 17265 - 나의 인생에는 숫자와 함께

import java.io.*;
import java.util.*;

public class boj17265 {

    static char[][] map;
    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        bfs(0, 0, map[0][0]-'0', ' ');
        System.out.println(max + " " + min);
    }

    private static void bfs(int i, int j, int preVal, char operator){
        int val = preVal;
        if(i == N-1 && j == N-1){
            int curVal = map[i][j]-'0';
            int ans = operation(preVal, curVal, operator);
            if(ans > max) max = ans;
            if(ans < min) min = ans;

            return;
        }
        if(checkOp(operator)){ // 숫자가 나왔을 때
            val = operation(preVal, map[i][j]-'0', operator);
        }
        for(int k = 0; k < 2; k++){
            int nx = i + dx[k];
            int ny = j + dy[k];

            if(!checkPos(nx, ny)) continue;

            bfs(nx, ny, val, map[i][j]);
        }
    }
    private static boolean checkPos(int i, int j){
        return (i >= N || j >= N) ? false : true;
    }
    private static boolean checkOp(char op){
        return (op == '*' || op == '+' || op == '-') ? true : false;
    }


    private static int operation(int pre, int cur, char op){
        int ret = pre;
        switch (op){
            case '*' :
                ret = pre * cur;
                break;
            case '+' :
                ret = pre + cur;
                break;
            case '-' :
                ret = pre - cur;
                break;
        }

        return ret;
    }
}
