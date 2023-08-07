package boj;

import java.util.Scanner;

public class boj1543 {
    public static String first;
    public static String second;
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        first=sc.nextLine();
        second=sc.nextLine();
        boolean flag=true;
        int answer=0;
        while(flag){
            int idx=-1;
            for(int i=0;i<=first.length()-second.length();i++){
                if(first.substring(i,i+second.length()).equals(second)){
                    idx=i;
                    break;
                }
            }

            if(idx!=-1) {
                first = first.substring(idx+second.length());
                answer++;
            }
            else{
                flag=false;
            }
            if(first.length()<second.length())
                flag=false;
        }
        System.out.println(answer);
    }
}
