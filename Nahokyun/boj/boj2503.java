package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2503 {
    static Boolean[] flag=new Boolean[988];
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        Arrays.fill(flag,true);
        for(int i=123;i<=987;i++){
            if(sameNumExist(i))
                flag[i]=false;
        }//같은 숫자 존재시 false 처리

        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int input=Integer.parseInt(st.nextToken());
            int strike=Integer.parseInt(st.nextToken());
            int ball=Integer.parseInt(st.nextToken());

            check(input,strike,ball);
        }//입력 끝
        int count=0;
        for(int i=123;i<=987;i++){
            if(flag[i]) {
                count++;
            }
        }
        System.out.println(count);
    }//end of main

    private static void check(int input, int strike,int ball){
        for(int i=123;i<=987;i++){
            if(!flag[i]){//이미 가능성없으면 넘어감
                continue;
            }
            int strikeCount=0;
            int ballCount=0;

            int[] inputArr ={input/100,(input/10)%10,input%10};
            int[] iArr ={i/100,(i/10)%10,i%10};

            for(int j=0;j<3;j++){//strike 탐색
                if(inputArr[j]==iArr[j])
                    strikeCount++;
            }
            
            for(int j=0;j<3;j++){//ball 탐색
                for(int k=0;k<3;k++){
                    if(k==j)
                        continue;
                    if(inputArr[j]==iArr[k])
                        ballCount++;
                }
            }
            if(strikeCount!=strike||ballCount!=ball)
                flag[i]=false;

        }//i탐색 종료

    }

    private static boolean sameNumExist(int num){
        int three=num/100;
        int two=(num/10)%10;
        int one=num%10;
        if(three==0||two==0||one==0)
            return true;
        return three == two || three == one || two == one;
    }
}
