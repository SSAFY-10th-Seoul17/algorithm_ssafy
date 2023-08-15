import java.util.*;
import java.io.*;

class Main{
    public static int[] direction = new int[2];
    public static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // F: 총 층, G: 목표 층, S: 현재 위치, U: 한 번에 위로 몇층, D: 한 번에 아래로 몇층
        int f = Integer.parseInt(st.nextToken()),
                s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()),
                u = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
        direction[0] = u;
        direction[1] = -1*d;
        if(s > g && d == 0) System.out.println("use the stairs");
        else if(s < g && u == 0) System.out.println("use the stairs");
        else bfs(s, g, f);

        br.close();
    }

    public static void bfs(int start, int des, int end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        while(!queue.isEmpty()) {
            int[] loc = queue.poll();
            if(loc[0] == des) {
                System.out.println(loc[1]);
                return;
            }
            for(int i=0; i<direction.length; i++) {
                int next = loc[0] + direction[i];
                if(!set.contains(next) && next <= end && next > 0) {
                    set.add(next);
                    queue.offer(new int[]{next, loc[1]+1});
                }
            }
        }
        System.out.println("use the stairs");
    }
}