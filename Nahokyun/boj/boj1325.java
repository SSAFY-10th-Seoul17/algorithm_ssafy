package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj1325 {
    static int n;
    static int m;
    //static boolean[] flag;//dfs 호출중에 발견한 원소는 아예 탐색 안함
    static boolean[] flag2;//dfs 호출중에 이미 탐색한적 있는 원소면 탐색 안함
    static int[] countHack;//각 컴퓨터별 해킹가능한 컴퓨터 수 저장
    static ArrayList<Integer>[] al;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n= Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());

        al= new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            al[i]= new ArrayList<>();
        }
        countHack=new int[n+1];
        //flag=new boolean[n+1];

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            al[a].add(b);//a가 b를 신뢰 -> b를 해킹하면 a도 해킹됨
        }//입력 종료
        for(int i=1;i<=n;i++){
            flag2=new boolean[n+1];
            //if(!flag[i]) {
                flag2[i] = true;
                //dfs(i);
            //}
            bfs(i);
        }
        int max=Integer.MIN_VALUE;
        for(int i:countHack){
            max=Math.max(max,i);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            if(countHack[i]==max)
                sb.append(i).append(' ');
        }

        System.out.println(sb);
    }
//    private static void dfs(int st){
//        for(int i:al[st]){
//            if(!flag2[i]){
//                flag2[i]=true;
//                countHack[i]++;
//                //flag[i]=true;
//                dfs(i);
//            }
//        }
//    }
    private static void bfs(int st){
        Queue<Integer> q=new LinkedList<>();
        q.add(st);
        flag2[st]=true;
        countHack[st]++;
        while(!q.isEmpty()){
            int cur=q.poll();
            for(int i:al[cur]){
                if(!flag2[i]){
                    countHack[i]++;
                    flag2[i]=true;
                    q.add(i);
                }
            }
        }
    }
}
