import java.io.*;
import java.util.*;

class Main {
    public static int[] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        table = new int[n];
        for (int i = 0; i < n; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }
        
        long ans = 0, left = 1, right = Arrays.stream(table).min().getAsInt() * (long) m;
        while(left <= right) {
            long mid = (left + right) / 2;
            if(checkTime(mid, m)) {
                right = mid - 1;
                ans = mid;
            }
            else left = mid + 1;
        }
        System.out.println(ans);
        br.close();
    }

    public static boolean checkTime(long time, int dest) {
        long pass = 0;
        for (int i = 0; i < table.length; i++) {
            pass += time / table[i];
            if(pass >= dest) return true;
        }
        return false;
    }
}
