// Created on iPad.

import java.util.*;
import java.io.*;

public class Boj01543 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        String str = br.readLine();

        int count = 0;
        
        int range = target.length() - str.length() + 1;
        loop:
        for (int i=0;i < range; i++) {
            
            for (int j=0;j<str.length();j++) {
                if(target.charAt(i+j) != str.charAt(j)) {
                    continue loop;       
                }
            }
            count++;
            i += str.length() - 1;
        }

        System.out.println(count);
		
	}
}
