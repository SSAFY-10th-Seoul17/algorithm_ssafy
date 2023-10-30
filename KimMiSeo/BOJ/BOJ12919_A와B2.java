import java.io.*;
import java.util.*;
public class BOJ12919_A와B2 {
    static String S,T;
    static boolean result;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        S = br.readLine();
        T = br.readLine();
        result = false;
        solve(T);
        if (result){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    private static void solve(String t){
        // System.out.println(t);
        if (t.length() == S.length()){
            if (t.equals(S)){
                result=true;
            }
            return;
        }

        int len = t.length();
        // A를 붙였다면
        if ( t.charAt(len-1) == 'A'){
            solve(t.substring(0, len-1));
        }

        // B를 붙였다면
        if ( t.charAt(0) == 'B' ){
            sb.append(t);
            String temp = "";
            temp = sb.reverse().toString();
            temp = temp.substring(0,len-1);
            solve(temp);
        }
    }
}
