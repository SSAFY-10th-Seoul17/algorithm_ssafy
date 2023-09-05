package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
간선 이어가기
 */
public class boj14284 {

    static ArrayList<Node>[] al;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        al=new ArrayList[n +1];

        for(int i = 1; i<= n; i++){
            al[i]=new ArrayList<>();
        }


        for(int i = 0; i< m; i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            al[a].add(new Node(b,c));
            al[b].add(new Node(a,c));
        }

        st=new StringTokenizer(br.readLine());

        int s=Integer.parseInt(st.nextToken());
        int t=Integer.parseInt(st.nextToken());
        //입력 종료


        System.out.println(dijkstra(s,t));
    }
    private static int dijkstra(int start,int end){
        Queue<Node> q=new ArrayDeque<>();
        int[] arr=new int[n+1];
        Arrays.fill(arr,500001);

        q.add(new Node(start,0));
        arr[start]=0;

        while(!q.isEmpty()){
            Node cur=q.poll();

            for(int i=0;i<al[cur.node].size();i++){
                Node cmp=al[cur.node].get(i);
                if(arr[cmp.node]>arr[cur.node]+cmp.cost) {
                    arr[cmp.node] = arr[cur.node] + cmp.cost;
                    q.add(new Node(cmp.node,arr[cmp.node]));
                }
            }
        }
        return arr[end];
    }
}
class Node{
    int node;
    int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}
