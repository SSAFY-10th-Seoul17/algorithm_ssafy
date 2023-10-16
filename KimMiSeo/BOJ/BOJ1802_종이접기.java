import java.util.*;
import java.io.*;

/**
 * 처음에는 중간을 중심으로 대칭이 되면(0,1이 짝) 된다고 생각했었지만, 틀린 생각이었습니다.
 * 여러번 접을 수 있기 때문에 계속해서 반을 접고, 반을 접었을 때 대칭이 되어야합니다.
 * 따라서 재귀를 이용하여 접고 접어서 1개가 되기 직전까지 중앙을 기준으로 왼쪽, 오른쪽인 대칭이 되는지 확인해주었습니다.
 */
public class BOJ1802_종이접기 {
    public static void main(String[] args) throws Exception{
        // 한번 접을 때마다 두께는 2배, 길이는 절반
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++){
            String in = br.readLine();
            int[] paper = new int[in.length()];
            for (int j=0; j<in.length(); j++){
                paper[j] = in.charAt(j) - '0';
            }
            flag = true;
            solve(0,paper.length-1,paper);
            System.out.println(flag ? "YES" : "NO");
        }
    }
    static boolean flag;
    private static void solve(int s, int e, int[] p){
        if (s >= e){
            return;
        }
        int start = s;
        int end = e;
        int mid = (start + end) / 2;
        while ( start < mid ){
            if (p[start] == p[end]){ // 대칭이 아닐 때
                flag = false;
                return;
            }
            start++;
            end--;
        }
        solve(s,mid-1,p);
        solve(mid+1,e,p);
    }
}
