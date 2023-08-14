import java.util.*;
import java.io.*;

class Main{
    static class Node {
        int num, cost;
        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    public static List<ArrayList<Node>> graph = new ArrayList<>();
    public static Set<Integer> set = new HashSet<>();
    public static int leafNode, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        for(int i=0; i<=v; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e;
            while((e = Integer.parseInt(st.nextToken())) != -1) {
                int cost = Integer.parseInt(st.nextToken());
                graph.get(s).add(new Node(e, cost));
            }
        }
        set.add(1);
        dfs(1, 0); // find Longest Leaf Node

        max = Integer.MIN_VALUE;
        set.clear();

        set.add(leafNode);
        dfs(leafNode, 0);

        System.out.println(max);
        br.close();
    }

    public static void dfs(int node, int len) {
        if (max < len) {
            max = len;
            leafNode = node;
        }
        for(Node next : graph.get(node)) {
            if(set.contains(next.num)) continue;
            set.add(next.num);
            dfs(next.num, len + next.cost);
        }
    }

}