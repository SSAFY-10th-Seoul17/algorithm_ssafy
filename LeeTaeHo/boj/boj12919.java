import java.io.*;

public class boj12919 {
    static String S, T;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        dfs(T, T.length());

        System.out.println(flag ? 1 : 0);
    }

    static void dfs(String str ,int depth){
        if(depth == S.length()){
            if(str.equals(S)){
                flag = true;
            }
            return;
        }

        if(str.charAt(0) == 'B'){
            dfs(reverse(str.substring(1)), depth - 1);
        }
        if (str.charAt(str.length() - 1) == 'A'){
            dfs(str.substring(0, str.length() - 1), depth - 1);
        }
    }

    static String reverse(String str){
        char[] cStr = str.toCharArray();
        char[] reverseStr = new char[cStr.length];
        for (int i = 0; i < cStr.length; i++) {
            reverseStr[cStr.length - 1 - i] = cStr[i];
        }

        return new String(reverseStr);
    }
}