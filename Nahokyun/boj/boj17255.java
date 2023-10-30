package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj17255 {

    static Map<String, Integer> method=new HashMap<>();

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String n=br.readLine();

        System.out.println(find(n));
    }

    private static int find(String num) {
        if(method.containsKey(num)) {
            return method.get(num);
        }

        if(num.length()==1) {
            method.put(num, 1);
            return 1;
        }

        int n=num.charAt(0);
        for(int i=1;i<num.length();i++) {
            if(n!=num.charAt(i)) {
                return find(num.substring(1))+find(num.substring(0, num.length()-1));
            }
        }

        return find(num.substring(1));
    }

}
