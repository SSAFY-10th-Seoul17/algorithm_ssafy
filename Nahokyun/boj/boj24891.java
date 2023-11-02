package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj24891 {

    private static int L;
    private static int N;
    private static boolean[] isUsed;
    private static boolean flag;
    private static String[] op;
    private static String reset="     ";
    private static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        op = new String[N];
        isUsed = new boolean[N];

        for(int i=0;i<N;i++) {
            op[i]=br.readLine();
        }
        //입력 종료
        Arrays.sort(op);

        track(0,new char[L][L]);

        if(!flag) {
            System.out.println("NONE");
        }
        else
            System.out.println(sb.toString());
    }

    private static void track(int cur,char[][] map) {
        if(flag) {
            return;
        }

        if(cur==L) {
            if(check(map)) {
                for(int i=0;i<L;i++) {
                    for(int j=0;j<L;j++) {
                        sb.append(map[i][j]);
                    }
                    sb.append('\n');
                }
                flag=true;
            }
            return;
        }

        for(int i=0;i<N;i++) {
            if(!isUsed[i]) {
                map[cur]=op[i].toCharArray();
                isUsed[i]=true;
                track(cur+1,map);
                map[cur]=reset.toCharArray();
                isUsed[i]=false;
            }
        }
    }

    private static boolean check(char[][] map) {
        for(int i=0;i<L;i++) {
            for(int j=0;j<i;j++) {
                if(map[i][j]!=map[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
