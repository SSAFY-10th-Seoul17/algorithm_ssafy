import java.io.*;
import java.util.*;

public class boj11037 {
    static boolean[] visited = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String s = "";
        while((s = br.readLine()) != null){
            int n = Integer.parseInt(s);
            int result = 0;
            while(++n <= 999_999_999){
                if(isDuplicate(n)){
                    result = n;
                    break;
                }

            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isDuplicate(int n){
        Arrays.fill(visited, false);
        while(n > 0){
            int digit = n % 10;
            if(digit == 0) return false;
            if(visited[digit]){
                return false;
            }else{
                visited[digit] = true;
            }
            n /= 10;
        }
        return true;
    }
}
