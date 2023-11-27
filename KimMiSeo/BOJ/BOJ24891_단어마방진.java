import java.util.*;
import java.io.*;
public class BOJ24891_단어마방진 {
    static int N,L;
    static String[] words, mabang;
    static boolean[] selected;
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken()); // 단어 길이
        N = Integer.parseInt(st.nextToken()); // 단어 개수
        words = new String[N];
        mabang = new String[L];
        selected = new boolean[N];

        for (int i=0; i<N; i++){
            words[i] = br.readLine();
        }
        Arrays.sort(words);
        solve(0);

        if (flag){
            for (int i=0; i<L; i++){
                sb.append(mabang[i]).append('\n');
            }
            System.out.println(sb);
        }else{
            System.out.println("NONE");
        }
    }

    private static void solve(int cnt){
        if (cnt == L){
            if (check()){ // 마방진이면
                flag = true;
            }
            return;
        }

        for (int i=0; i<N; i++){
            if (selected[i]){
                continue;
            }
            mabang[cnt] = words[i];
            selected[i] = true;
            solve(cnt+1);

            if (flag){
                return;
            }
            selected[i] = false;
        }
    }

    private static boolean check(){
        for (int i=0; i<L; i++){
            for (int j=0; j<L; j++){
                if (mabang[i].charAt(j) != mabang[j].charAt(i)){
                    return false;
                }
            }
        }
        return true;
    }
}