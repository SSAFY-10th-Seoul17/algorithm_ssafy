package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj17297 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int m=Integer.parseInt(br.readLine());

        find(m);
    }

    private static void find(int m) {
        String s="Messi Gimossi";
        int first=6;
        int second=14;
        while(true) {
            if(m<16) {
                if(m==0||m==6||m==14) {
                    System.out.println("Messi Messi Gimossi");
                }
                else
                    System.out.println(s.charAt(m-1));
                return;
            }
            if(m<first) {
                int tmp=first;
                first=second-first;
                second=tmp;
                continue;
            }
            if(m<second) {
                m-=first;
                continue;
            }
            int tmp=first;
            first=second;
            second=tmp+first;
        }


    }

}
