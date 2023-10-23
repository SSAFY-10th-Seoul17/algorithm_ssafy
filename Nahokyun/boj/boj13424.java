package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
비밀 모임
 */
public class boj13424 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                Arrays.fill(arr[i], 100_000_000);
                arr[i][i]=0;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                arr[a][b] = c;
                arr[b][a] = c;
            }

            int friend = Integer.parseInt(br.readLine());//친구 수 입력(k)
            int[] friends=new int[friend+1];

            st=new StringTokenizer(br.readLine());
            for(int i=1;i<=friend;i++){
                friends[i]=Integer.parseInt(st.nextToken());
            }
            //입력 종료

            //최소거리 계산 (플로이드 워셜)
            Floyd(n, arr);

            int[] distSum=new int[n+1];//친구들의 각 지점에서 해당 i번째 방에 대한 거리합을 저장할 배열
            for(int i=1;i<friends.length;i++){
                for(int j=1;j<=n;j++) {
                    distSum[j] += arr[j][friends[i]];
                }
            }

            int min=Integer.MAX_VALUE;
            int minRoom=-1;
            for(int i=1;i<=n;i++){
                if(min>distSum[i]) {
                    min=distSum[i];
                    minRoom=i;
                }
            }

            sb.append(minRoom).append('\n');
        }//테케 종료

        System.out.printf(sb.toString());
    }

    private static void Floyd(int n, int[][] arr) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i==j)
                    continue;
                for (int k = 1; k <= n; k++) {
                    if(i==k||j==k)
                        continue;
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }
    }

}
