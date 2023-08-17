package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16947 {
    static ArrayList<Integer>[] arr;
    static boolean[] flag;
    static int[] moveCount;
    static int findCycle;
    static boolean check=false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] answer=new int[n+1];
        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            arr[first].add(second);
            arr[second].add(first);
        }
        for (int i = 1; i <= n; i++) {

            //init
            flag = new boolean[n + 1];
            moveCount = new int[n + 1];
            Arrays.fill(moveCount, -1);
            findCycle=-1;
            moveCount[i] = 0;
            check=false;
            flag[i]=true;


            dfs(i,i, 0);
            answer[i]=findCycle;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++) {
            sb.append(answer[i]).append(' ');
        }
        System.out.println(sb.toString());

    }

    private static void dfs(int st,int cur, int count) {


        for (int i = 0; i < arr[cur].size(); i++) {
            int tmp = arr[cur].get(i);
            if(!flag[tmp]) {
                moveCount[tmp] = count + 1;
                flag[tmp]=true;
                dfs(st,tmp, count + 1);
            }else {//방문한적이 있을때
                if(moveCount[tmp]!=count-1&&!check) {//방문한적이 있는데  직전에 왔던값이 아닐때
                    findCycle=moveCount[tmp];
                    check=true;
                    return;
                }
            }
        }
    }

}
