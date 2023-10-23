import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        ArrayList<Integer> myFriends = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            if(a == 1 || b == 1) {
                myFriends.add(a == 1 ? b : a);
                set.add(a == 1 ? b : a);
            }
            else {
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
        }
        for(int friend : myFriends) {
            graph.get(friend).stream().forEach(node -> set.add(node));
        }
        System.out.println(set.size());
        br.close();
    }

}