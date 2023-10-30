import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
public class BOJ17297_MessiGimossi {
    static int M, messiLen,index,cnt;
    static String result,temp;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine()) - 1; // 인덱스
        messiLen = 0;
        temp = "Messi Gimossi";
        list.add(5);
        list.add(13);
        
        index = 0;

        if (M <= 13){
            result = String.valueOf(temp.charAt(M));
            if (result.equals(" ")){
                result = "Messi Messi Gimossi";
            }
        } else {
            getMessiLen();
//            System.out.println(list);
            messi(list.size()-1);
        }

        System.out.println(result);
    }

    private static void messi(int index){
        // 0 ~ memo[0]-1
        // memo[0]
        // memo[0]+1 ~ memo[0]+memo[1]
        //System.out.println(Arrays.toString(memo)+"     dddddd"+ messiLen);

        messiLen = list.get(index);
//        System.out.println(messiLen+" messilen "+ index+ " "+M);

        if (messiLen <= 13){
//            System.out.println(M);
            result = String.valueOf(temp.charAt(M));
            if (result.equals(" ")){
                result = "Messi Messi Gimossi";
            }
            return;
        }

        int left = list.get(index - 1);
        int right = list.get(index - 2);
//        System.out.println(left +" left "+ right + " right "+ messiLen);

        if (M == left){ // 가운데
            result = "Messi Messi Gimossi";
            return;
        }

        if ( M <= left - 1 ){ // 왼쪽이면
            messi(index - 1);
        }
//        System.out.println(left+1 +" "+ (left+right)+" " + M);
        if (M>= left + 1 && M <= left+right ){ // 오른쪽이면
            M -= ( left + 1 );
//            System.out.println(M);
            messi(index - 2);
        }
    }

    private static void getMessiLen(){
        while(list.get(list.size()-1) <= M){
            int first = list.get(list.size()-1);
            int second = list.get(list.size()-2);
            list.add(first + second + 1);
        }
    }
}
