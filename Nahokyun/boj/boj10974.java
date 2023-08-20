package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj10974 {
    static int n;
    static boolean[] isSelected;
    static ArrayList<Integer> arr=new ArrayList<>();
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
        isSelected=new boolean[n+1];
        permutation(0);
        System.out.println(sb);
    }
    private static void permutation(int cur){
        if(cur==n){
            for(int i:arr){
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=1;i<=n;i++){
            if(!isSelected[i]){
                isSelected[i]=true;
                arr.add(i);
                permutation(cur+1);
                arr.remove(cur);
                isSelected[i]=false;
            }
        }

    }
}
