package toyproject.somedaybucket.myAlgo;

import java.util.Scanner;

class Sol {
    static char[] s;
    static void cantore(int from, int to){

        int val = ((to-from)/3)/2;
        int mid = (from + to)/2;
//        System.out.println("val : " + val + ", mid : " + mid);
//        System.out.println(s);
        for(int i = mid-val; i <= mid+val; i++){
            s[i] = ' ';
        }
        if(val == 0){ // 선의 길이가 1일 때 멈춤
            return;
        }

        cantore(from, mid-val-1);
        cantore(mid+val+1, to); // 해결 가능하지만, 이렇게 하면 시간이 너무 오래 걸림!
    }


}


public class boj4779 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int n = sc.nextInt();

            if (n == 0){
                System.out.println("-");
            } else {
                StringBuilder sb = new StringBuilder();
                int len = (int)Math.pow(3, n);

                for(int i = 0; i < len; i++){
                    sb.append("-");

                }
                Sol.s = sb.toString().toCharArray();
                Sol.cantore(0, Sol.s.length-1);

                System.out.println(Sol.s);
            }
        }
    }
}
