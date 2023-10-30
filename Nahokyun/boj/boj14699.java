package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj14699 {

    private static Node[] nodes;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());

        ArrayList<Pair> breakPoint=new ArrayList<>();
        dp = new int[n+1];

        for(int i=1;i<=n;i++){
            breakPoint.add(new Pair(Integer.parseInt(st.nextToken()),i));
        }

        nodes = new Node[n+1];
        for(int i=1;i<=n;i++){
            nodes[i]=new Node(null,i);
        }


        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());

            int first=Integer.parseInt(st.nextToken());
            int second=Integer.parseInt(st.nextToken());

            nodes[first].next=new Node(nodes[first].next,second);
            nodes[second].next=new Node(nodes[second].next,first);
        }

        Collections.sort(breakPoint);

        for(Pair cur:breakPoint){
            find(cur.num);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(dp[i]).append('\n');
        }
        System.out.println(sb);

    }
    private static void find(int cur){
        int max=0;
        for(Node next=nodes[cur].next;next!=null;next=next.next){
            if(dp[next.num]>max){
                max=dp[next.num];
            }
        }

        dp[cur]=max+1;

    }

    static class Pair implements Comparable<Pair>{
        int height;
        int num;

        public Pair(int height, int num) {
            this.height = height;
            this.num = num;
        }

        @Override
        public int compareTo(Pair o) {
            return o.height-this.height;
        }
    }


    static class Node{
        Node next;
        int num;

        public Node(Node next, int num) {
            this.next = next;
            this.num = num;
        }
    }
}
