package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj11729 {
    static long count=0L;
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        hanoi(n,1,2,3);
        System.out.println(count);
        System.out.println(sb);
        //bw.flush();
        //bw.close();
    }

    private static void hanoi(int n,int st,int tmp,int des) throws IOException{
        if(n==1) {
            count++;
            //bw.write(""+st+' '+des+'\n');
            sb.append(""+st+' '+des+'\n');
            return;
        }
        hanoi(n-1,st,des,tmp);
        hanoi(1,st,tmp,des);
        hanoi(n-1,tmp,st,des);
    }
}
