import java.util.*;

public class boj1543 {
    static int case_num = 0;
    static int[] dp;
    static String comp, keyword;
    public static void main(String[] args) {
        init();
        int key_len = keyword.length();
        int comp_len = comp.length();
        for (int i = key_len; i <= comp_len; i++) {
            dp[i] = Math.max(dp[i - key_len] + (comp.substring(i - key_len, i).contains(keyword) ? 1 : 0), dp[i - 1]);
        }
        System.out.println(dp[comp_len]);
    }
    public static void init(){
        Scanner sc = new Scanner(System.in);
        comp = sc.nextLine();
        keyword = sc.nextLine();
        dp = new int[comp.length()+1];
    }



}

