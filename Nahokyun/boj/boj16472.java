package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj16472 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String s=br.readLine();
        int[] arr=new int[s.length()];
        for(int i=0;i<s.length();i++) {
            arr[i]=s.charAt(i)-'a';
        }

        int[] check=new int[26];
        int maxSize=0;

        int start=0;
        int end=0;
        check[arr[0]]++;

        int count=1;
        for(int i=1;i<s.length();i++) {
            if(count==n+1) {
                end=i-1;
                check[arr[end]]--;

                break;
            }
            if(check[arr[i]]==0) {//처음 방문하는 종류일때
                check[arr[i]]++;
                count++;
                end=i;
            }else {//이미 방문했던 종류일때
                check[arr[i]]++;
                end=i;
            }
        }
        maxSize=end-start;
        //초기설정 완료

        for(int i=end;i<s.length();i++) {
            if(check[arr[i]]==0) {//처음 들어오는 경우 start좌표의 문자가 없을때까지 start 당김
                check[arr[i]]++;

                while(!Kind(check,n)) {
                    check[arr[start]]--;
                    start++;
                }

            }else {//이미 들어왔던 경우 해당 문자개수++
                check[arr[i]]++;
            }
            maxSize=maxSize<i-start+1?i-start+1:maxSize;

        }

        System.out.println(maxSize);
    }

    private static boolean Kind(int[] check, int n) {//종류 맞을때 true 리턴
        int count=0;
        for(int i=0;i<26;i++) {
            if(check[i]!=0) {
                count++;
            }
        }
        return count==n?true:false;
    }


}

