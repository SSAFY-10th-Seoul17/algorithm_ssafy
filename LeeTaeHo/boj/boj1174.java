import java.io.*;
import java.util.*;

public class boj1174 {
    static int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dfs(0, 0);
        list.sort(Comparator.comparingLong(o -> o));
        if(list.size() <= n){
            System.out.println(-1);
        }else {
            System.out.println(list.get(n));
        }
    }

    public static void dfs(long num, int cnt){
        if(cnt == 10){
            list.add(num);
            return;
        }

        dfs(num, cnt + 1);
        dfs(num * 10+ nums[cnt], cnt + 1);
    }
}
