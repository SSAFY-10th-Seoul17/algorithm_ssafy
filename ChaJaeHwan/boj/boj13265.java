import java.io.*;
import java.util.*;

public class boj13265 {


    static int T, n, m, x, y;
    static ArrayList<ArrayList<Integer>> list;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(new ArrayList<>());
            }
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken()) - 1;
                y = Integer.parseInt(st.nextToken()) - 1;
                list.get(x).add(y);
                list.get(y).add(x);
            }

            boolean flag = false;
            visited = new int[n];
            if (bfs(n)) {
                flag = true;
            }
            if (flag) {
                System.out.println("possible");
            } else {
                System.out.println("impossible");
            }
        }

    }

    static boolean bfs(int n) {
        for (int j = 0; j < n; j++) {
            if (visited[j] == 0) {
                Queue<Integer> que = new ArrayDeque<>();
                que.add(j);
                visited[j] = 1;

                while (!que.isEmpty()) {
                    int poll = que.poll();
                    boolean flag1 = false, flag2 = false;
                    for (int i : list.get(poll)) {
                        if (visited[i] == 0) {
                            if (visited[poll] == 1) {
                                visited[i] = 2;
                                que.add(i);
                            } else if (visited[poll] == 2) {
                                visited[i] = 1;
                                que.add(i);
                            }
                        } else if (visited[i] == 1) {
                            flag1 = true;
                        } else if (visited[i] == 2) {
                            flag2 = true;
                        }

                        if (flag1 && flag2) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;

    }

}
