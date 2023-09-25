import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj28106 {
    static class Node {
        int power;
        List<Integer> child = new ArrayList<>();
    }
    static int[] dp, list;
    static int top;
    static Node[] graph;

    static final int MOD = 998_244_353;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int root = getRootAndSetGraph(n, br);
        dp = new int[n + 1];
        list = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(getCases(root));
    }

    private static int getRootAndSetGraph(int n, BufferedReader br) throws IOException {
        graph = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new Node();
        }
        int root = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int node = 1; node <= n; node++) {
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].child.add(node);
            if (parent == 0) {
                root = node;
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int node = 1; node <= n; node++) {
            graph[node].power = Integer.parseInt(st.nextToken());
        }

        return root;
    }

    private static int getCases(int node) {
        if (graph[node].child.isEmpty()) {
            return 1;
        } else if (graph[node].power == 0) {
            return 0;
        } else if (dp[node] >= 0) {
            return dp[node];
        }

        top = 0;
        setMovableChildList(node, graph[node].power, 0);

        int sum = 0;
        for (int child : Arrays.copyOf(list, top)) {
            sum += getCases(child);
            sum %= MOD;
        }

        return dp[node] = sum;
    }


    private static void setMovableChildList(int node, int power, int distance) {
        if (distance >= power) {
            return;
        }

        for (int child : graph[node].child) {
            list[top++] = child;
            setMovableChildList(child, power, distance + 1);
        }
    }

}

