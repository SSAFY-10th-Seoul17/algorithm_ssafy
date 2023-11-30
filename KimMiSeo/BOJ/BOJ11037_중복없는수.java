import java.util.*;
import java.io.*;
public class BOJ11037_중복없는수 {
    static int N;
    static int[] numbers = {1,2,3,4,5,6,7,8,9};
    static String nums = "123456789";
    static List<Integer> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        list = new ArrayList<>();
        solve();
        String read;

        while((read = br.readLine()) != null){
            N = Integer.parseInt(read);
            // 이분탐색으로 n 보다 크지만 가장 가까운 수를 리턴하기
            System.out.println(find(N));
        }
    }

    private static int find(int num){
        if (num > 987654321){
            return 0;
        }
        int start = 0;
        int end = list.size()-1;
        int mid = (start + end)/2;
;
        while ( start < end ){
            // System.out.println(list.get(mid)+" " + start+" "+end+" "+mid);
            if ( list.get(mid) <= num){ // 더 작거나, 같으면 -> 오른쪽 보기
                start = mid+1;
            } else {
                end = mid;
            }
            mid =  (start + end)/2;
        }
        // System.out.println(list.get(mid)+" "+mid);
        return (list.get(mid));

    }

    // 중복 없는 수를 모두 만든다.
    private static void solve(){
        for (int i=1; i<=9; i++){ // 숫자 개수
            isSelected = new boolean[9];
            nns = new char[i];
            perm(0,i);
        }
    }
    static boolean[] isSelected;
    static char[] nns;
    private static void perm(int cnt, int goal){
        if (cnt == goal){ // 다 골랐으면
//            String num = "";
//            for (int i=0; i<9; i++){
//                if (isSelected[i]){ // true 인 것만
//                    num+= Integer.toString(i+1 );
//                }
//            }
//            System.out.println(Integer.parseInt(num));
//            list.add(Integer.parseInt(num));
            //System.out.println(Arrays.toString(nns));
            String num = "";
            for (int i=0; i<cnt; i++){
                num += nns[i];
            }
            //System.out.println(Integer.parseInt(num));
            list.add(Integer.parseInt(num));
            return;
        }

        for (int i=0; i<9; i++){
            if (!isSelected[i]){
                isSelected[i] = true;
                nns[cnt] = nums.charAt(i);
                perm(cnt+1, goal);
                isSelected[i] = false;
            }
        }

    }
}
