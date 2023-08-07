package boj;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj7490 {
    public static int test;
    public static int n;
    public static char[] oper=new char[] {' ','+','-'};
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        test=sc.nextInt();
        for(int i=0;i<test;i++) {
            StringBuilder sb=new StringBuilder();
            n=sc.nextInt();
            track(1,n,sb);
            System.out.println();
        }
    }

    public static void track(int cur,int n,StringBuilder s) {
        s.append(cur);
        if(cur==n) {
            if(calc(s.toString())) {
                System.out.println(s.toString());
            }
            return;
        }
        for(int i=0;i<3;i++) {
            s.append(oper[i]);
            track(cur+1,n,s);
            s.deleteCharAt(s.length()-1);
            s.deleteCharAt(s.length()-1);
        }
    }
    public static boolean calc(String s) {
        s=s.replaceAll(" ","");
        //공백제거
        ArrayList<Character> operArr=new ArrayList<>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='+'||s.charAt(i)=='-') {
                operArr.add(s.charAt(i));
            }
        }
        //연산자 순서 입력
        StringTokenizer st=new StringTokenizer(s,"+-");
        //숫자분리
        int sum=Integer.parseInt(st.nextToken());
        int idx=0;
        while(st.hasMoreTokens()) {
            char op=operArr.get(idx);
            if(op=='+') {
                sum+=Integer.parseInt(st.nextToken());
            }else {
                sum-=Integer.parseInt(st.nextToken());
            }
            idx++;
        }
        //연산

        if(sum==0) {
            return true;
        }
        return false;
    }
}
