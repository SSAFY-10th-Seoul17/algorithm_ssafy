import java.util.*;
import java.io.*;

class Main{
    static class Location {
        int pos, time;
        public Location(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
    public static final int MAX_LOC = 100000;
    public static int[] direction = {1, -1, 2};
    public static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        System.out.println(move(n));
        br.close();
    }

    public static int move(int curr) {
        Set<Integer> set = new HashSet<>();
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(curr, 0));
        set.add(curr);
        while (!queue.isEmpty()) {
            Location loc = queue.poll();
            if (loc.pos == k) return loc.time;
            for (int i = 0; i < direction.length; i++) {
                int next = i == 2 ? loc.pos * direction[i] : loc.pos + direction[i];
                if (!set.contains(next) && next >= 0 && next <= MAX_LOC) {
                    set.add(next);
                    queue.offer(new Location(next, loc.time + 1));
                }
            }
        }
        return -1;
    }
}