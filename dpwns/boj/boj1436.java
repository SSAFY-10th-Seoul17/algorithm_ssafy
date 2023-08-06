import java.io.*;

class Main {
    public static int MIN = 666;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0, num = MIN;
        while(cnt < n) {
            if(String.valueOf(num).contains("666")) cnt++;
            num++;
        }
        System.out.println(num-1);
        br.close();
    }
}