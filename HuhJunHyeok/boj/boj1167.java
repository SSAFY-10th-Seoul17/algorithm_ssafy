import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 1167. 트리의 지름
 */
public class boj1167 {
    static class AdjNode { // 트리(그래프) 저장 용도.
        int adjNodeIdx; // 인접 노드.
        int edge; // 인접 노드와 연결된 간선의 길이

        public AdjNode(int adjNodeIdx, int edge) {
            this.adjNodeIdx = adjNodeIdx;
            this.edge = edge;
        }
    }

//    static class Node { // bfs 탐색 용도
//        int nodeIdx;
//        int distance;
//
//        public Node(int nodeIdx, int distance) {
//            this.nodeIdx = nodeIdx;
//            this.distance = distance;
//        }
//    }

    static int V, maxDistance, maxNodeIdx; // V의 범위가 2~100000. => 정점을 모두 체크하는 dfs, bfs는 불가능.
    static ArrayList<ArrayList<AdjNode>> treeInfo;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        treeInfo = new ArrayList<ArrayList<AdjNode>>(V + 1);
        treeInfo.add(null);
        for(int i = 1; i <= V; i++) {
            treeInfo.add(new ArrayList<AdjNode>());
        }

        for(int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int nodeNum = Integer.parseInt(st.nextToken());

            while(true) {
                int adjNode = Integer.parseInt(st.nextToken());
                if(adjNode == -1) {
                    break;
                }
                int edge = Integer.parseInt(st.nextToken());
                treeInfo.get(nodeNum).add(new AdjNode(adjNode, edge));
                treeInfo.get(adjNode).add(new AdjNode(nodeNum, edge));
            }

        }
        // 트리는 사이클이 없이 연결됨. 또한, 트리는 한 정점에서 다른 정점까지의 경로는 유일한 경로를 가진다.
        // 하나의 정점에서 가장 먼 트리의 지름을 이루는 정점에 대해, 그 정점에서 최장 길이의 경로는 하나의 정점이 있는 경로와 겹치는 부분이 존재한다.
        // 따라서, 하나의 정점에서 가장 먼 경로를 이루는 정점을 찾고 그 정점과 가장 먼 경로를 이루는 정점을 찾으면 그 두 정점 사이의 거리가 트리의 지름이다.
        visited = new boolean[V + 1];
//        bfs(1);
        dfs(1, 0);
        visited = new boolean[V + 1];
//        bfs(maxNodeIdx);
        dfs(maxNodeIdx, 0);

        System.out.println(maxDistance);
    }

    public static void dfs(int vIdx, int distance) {
        if(distance > maxDistance) {
            maxDistance = distance;
            maxNodeIdx = vIdx;
        }

        visited[vIdx] = true;

        int size = treeInfo.get(vIdx).size();
        for(AdjNode adjNode: treeInfo.get(vIdx)) {
            if(!visited[adjNode.adjNodeIdx]) {
                dfs(adjNode.adjNodeIdx, distance + adjNode.edge);
            }
        }
    }

//    public static void bfs(int vIdx) {
//        Queue<Node> nodeQueue = new LinkedList<>();
//        nodeQueue.add(new Node(vIdx, 0));
//        visited[vIdx] = true;
//
//        int distance = 0;
//        while(!nodeQueue.isEmpty()) {
//            Node nowNode = nodeQueue.poll();
//
//            if(nowNode.distance > distance) {
//                distance = nowNode.distance;
//                maxNodeIdx = nowNode.nodeIdx;
//            }
//
//            for(AdjNode adjNode: treeInfo.get(nowNode.nodeIdx)) {
//                if(!visited[adjNode.adjNodeIdx]) {
//                    visited[adjNode.adjNodeIdx] = true;
//                    nodeQueue.add(new Node(adjNode.adjNodeIdx, nowNode.distance + adjNode.edge));
//                }
//            }
//        }
//        maxDistance = distance > maxDistance ? distance : maxDistance;
//    }
}


