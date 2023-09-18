package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class boj1174 {
    static ArrayList<Long> al=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());



        find(0,9);
        Collections.sort(al);

        System.out.println(n>al.size()?-1:al.get(n-1));
    }

    private static void find(long cur,int idx) {
        if(!al.contains(cur))
            al.add(cur);
        if(idx==-1)
            return;
        find(cur,idx-1);
        find(cur*10+idx,idx-1);
    }
}
