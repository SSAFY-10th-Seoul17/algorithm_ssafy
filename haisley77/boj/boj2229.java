import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2229 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stu = new int[N+1];
        int[] dp = new int[N+1];
        int m = 0;
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 1; i <= N; i++){
            stu[i] = Integer.parseInt(st.nextToken());
            for (int j = i-1; j >= 1; j--){
                m = Math.max(Math.abs(stu[i]-stu[j])+dp[j-1],m);
            }
            dp[i] = m;
        }
        System.out.println(dp[N]);
    }
}