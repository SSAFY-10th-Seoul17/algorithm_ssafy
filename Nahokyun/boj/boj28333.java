package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj28333 {

    private static Node[] al;
    private static Node[] toTrack;
    static int[] dist;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int test=Integer.parseInt(br.readLine());

        for(int t=0;t<test;t++){
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());

            al = new Node[n+1];
            toTrack=new Node[n+1];
            dist=new int[n+1];
            result=new ArrayList<>();

            for(int i=1;i<=n;i++){
                al[i]=new Node(null,i);
                toTrack[i]=new Node(null,i);
            }

            Arrays.fill(dist,1001);

            for(int i=0;i<m;i++){
                st=new StringTokenizer(br.readLine());

                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());

                al[a].next=new Node(al[a].next,b);
                toTrack[b].next=new Node(toTrack[b].next,a);
            }
            //입력종료, 범인은 N번 도시에 위치
            bfs();
            backtrack(n);
            StringBuilder sb=new StringBuilder();
            result.add(n);
            Collections.sort(result);

            for(int i:result) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        }

    }

    private static void backtrack(int n) {
        int cmpDist=dist[n];
        boolean[] visited=new boolean[n+1];
        ArrayDeque<Integer> q=new ArrayDeque<>();
        q.add(n);
        visited[n]=true;
        while(!q.isEmpty()) {
            ArrayDeque<Integer> tmp=new ArrayDeque<>();
            cmpDist--;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (Node next = toTrack[cur].next; next != null; next = next.next) {
                    if (dist[next.num] ==cmpDist&&!visited[next.num]) {
                        tmp.add(next.num);
                        result.add(next.num);
                    }
                    visited[next.num]=true;
                }
            }
            q=tmp;
        }
    }

    private static void bfs() {
        ArrayDeque<Integer> q=new ArrayDeque<>();
        q.add(1);
        dist[1]=0;
        while(!q.isEmpty()){
            int cur=q.poll();
            for(Node next=al[cur].next;next!=null;next=next.next){
                if(dist[next.num]>dist[cur]+1){
                    q.add(next.num);
                    dist[next.num]=dist[cur]+1;
                }
            }
        }
    }
    static class Node {
        Node next;
        int num;

        public Node(Node next, int num) {
            this.next = next;
            this.num = num;
        }
    }
}
