import java.io.*;
public class BOJ16120_PPAP {
    static String input = "";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        int pcnt=0;
        String result = "PPAP";

        for (int i=0; i<input.length(); i++){
            if (input.charAt(i) == 'P'){ // p면
                pcnt++;
            }
            else{ // a면
                if (i==input.length()-1){
                    result="NP";
                    break;
                }
                if (pcnt>=2 && input.charAt(i+1) == 'P'){ // 앞에 2글자 이상이 p이고, 다음 글자가 p이면
                    pcnt--;
                    i++;
                }else{
                    result = "NP";
                    break;
                }
            }
        }
        if (!(pcnt == 1)){
            result = "NP";
        }
        System.out.println(result);
    }
}
