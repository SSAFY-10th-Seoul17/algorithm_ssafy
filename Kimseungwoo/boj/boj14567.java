package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

// topology problem!

public class boj14567 {

    static ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();

    static int V, E;
    static int[] inDegree;
    static int[] dp;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        inDegree = new int[V+1];

        for(int i = 0; i < V+1; i++) {
            a.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            a.get(from).add(to);
            inDegree[to]++;
        }

        topologySort();
    }

    private static void topologySort() {
        dp = new int[V+1];
        result = new int[V];

        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= V; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
                dp[i] = 1;
            }
        }
        for(int i = 0; i < V; i++) {
            int x = q.poll();

            for (int j = 0; j < a.get(x).size(); j++) {
                int y = a.get(x).get(j);
                dp[y] = dp[x]+1;
                if (--inDegree[y] == 0) {
                    q.offer(y);
                }
            }
            result[i] = x;
        }

//		for(int i = 0; i < V; i++) {
//			System.out.print(result[i]+" ");
//		}
        for(int i = 1; i <= V; i++) {
            System.out.print(dp[i]+" ");
        }

    }

}

