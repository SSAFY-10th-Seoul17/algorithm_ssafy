import java.util.*;
import java.io.*;
public class BOJ1174_줄어드는수 {
    static int[] nums = new int[] {9,8,7,6,5,4,3,2,1,0};
    static ArrayList<Long> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt((br.readLine()));
        solve(0,0);

        Collections.sort(list);
        if (N > list.size()){
            System.out.println(-1);
        }else{
            System.out.println(list.get(N-1));
        }
    }

    private static void solve (int index, long total){
        if (index == 10){
            if(!list.contains(total)){
                list.add(total);
            }
            return;
        }
        // 선택
        solve(index+1, total*10 + nums[index]);
        // 비선택
        solve(index+1, total);
    }
}
