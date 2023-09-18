import java.io.*;
import java.util.*;

public class boj1174 {

    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Queue<String> que = new ArrayDeque();
        for(int i = 0;i <10; i++) {
            que.add(String.valueOf(i));
        }
        if(n <= 10){
            System.out.println(n-1);
        } else {
            String answer = null;
            int cnt = 0;
            while(!que.isEmpty()) {
                String poll = que.poll();
//                System.out.println(poll);
                cnt += 1;
                if(cnt == n) {
                    answer = poll;
                    break;
                }
                int last = poll.charAt(poll.length()-1)-'0';
                for(int i = 0; i < last; i++) {
                    que.add(poll + String.valueOf(i));
                }
            }

            if(answer == null) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        }
    }
}
