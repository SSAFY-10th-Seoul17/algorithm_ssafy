package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1802 {
    static boolean flag;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String s = br.readLine();

            flag = true;
            if (s.length() == 1) {
                System.out.println("YES");
                continue;
            }


            find(s, 0, s.length() - 1);

            if (!flag) {
                System.out.println("NO");
            } else System.out.println("YES");


        }
    }

    private static void find(String s, int start, int end) {
        if (start > end) {
            return;
        }
        int mid = (start + end) >> 1;

        for (int j = start; j < mid; j++) {
            if (s.charAt(j) == s.charAt(end - j)) {
                flag = false;
                break;
            }
        }
        find(s, start, mid - 1);
        find(s, mid + 1, end);
    }


}


//    private static void find(String s,int size,int mid) {
//        if(size==0)
//            return;
//        if(s.charAt(mid+size)==s.charAt(mid-size)){
//            flag=false;
//            return;
//        }else{
//            find(s,size>>1,mid-size);
//            find(s,size>>1,mid+size);
//        }
//
//    }
