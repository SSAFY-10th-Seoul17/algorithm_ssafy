import java.io.*;
import java.util.*;

public class boj16472 {
    static int n;
    static String s;

    static int[] cnt = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());
        s = br.readLine();

        int l = 0; // 시작점
        int r = 0; // 끝점
        int size = s.length();
        int ans = 1;
        int kind = 1;
        cnt[s.charAt(0) - 'a'] = 1;

        if (n == 1){
            System.out.println(1);
            System.exit(0);
        }

        while (true){
            r++;
            if (r == size){
                break;
            }
            // 종류가 제한을 넘기면 l을 종류가 줄 때 까지 축소
            cnt[s.charAt(r) - 'a']++;
            if (cnt[s.charAt(r) - 'a'] == 1){
                kind++;
                if (kind > n){
                    while(true){
                        cnt[s.charAt(l) - 'a']--;
                        if(cnt[s.charAt(l) - 'a'] == 0){
                            l++;
                            break;
                        }
                        l++;
                    }
                }
            }
            ans = Math.max(ans, r-l+1);
        }
        System.out.println(ans);
    }
}
