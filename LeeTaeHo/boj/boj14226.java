import java.io.*;
import java.util.*;

public class boj14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());

        System.out.println(getEmoticons(s));
    }

    static int getEmoticons(int s){
        boolean[][] visited = new boolean[1004][1004];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0, 0));
        visited[1][0] = true;
        while(!q.isEmpty()){
            Node node = q.poll();

            int cnt = node.cnt;
            int buffer = node.buffer;
            int time = node.time;

            if(cnt == s) return time;

            if(cnt - 1 >= 0 && !visited[cnt - 1][buffer]){
                visited[cnt - 1][buffer] = true;
                q.offer(new Node(cnt - 1, buffer, time + 1));
            }

            if(buffer + cnt <= 1000 && !visited[cnt][cnt]){
                visited[cnt][cnt] = true;
                q.offer(new Node(cnt, cnt, time + 1));
            }

            if(buffer != 0 && cnt + buffer <= 1000 && !visited[cnt + buffer][buffer]){
                visited[cnt + buffer][buffer] = true;
                q.offer(new Node(cnt + buffer, buffer, time + 1));
            }
        }
        return 0;
    }

    static class Node{
        int cnt;
        int buffer;
        int time;

        public Node(int cnt, int buffer, int time) {
            this.cnt = cnt;
            this.buffer = buffer;
            this.time = time;
        }
    }
}
