import java.io.*;
import java.util.*;

public class boj16397 {

    static int T, G;
    static String N;
    static int[] num = new int[5];
    static int[] b = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();
        int j = 0;
        for (int i = 5 - N.length(); i < 5; i++) {
            num[i] = N.charAt(j++) - '0';
        }

        T = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        b[toInt(num)] = 1;
        bfs();
//        System.out.println(Arrays.toString(b));
        if (b[G] == 0) {
            System.out.println("ANG");
        } else {
            System.out.println(b[G]-1);
        }
    }

    public static void bfs() {
        ArrayDeque<Integer> que = new ArrayDeque();
        que.add(toInt(num));
        int t = 0;
        while (!que.isEmpty()) {
            t++;
            if (t > T) break;
            int qsize = que.size();

            for (int i = 0; i < qsize; i++) {
                int poll = que.poll();
                //버튼 A
                if (poll + 1 <= 99999) {
                    if (b[poll + 1] == 0) {
                        b[poll + 1] = b[poll] + 1;
                        que.add(poll+1);
                    }
                }
                //버튼 B
                int[] temp = toIntArr(poll);
                int B = m2m1(temp);
                if (B > 0 && B <= 99999) {
                    if (b[B] == 0) {
                        b[B] = b[poll] + 1;
                        que.add(B);
                    }
                }
            }
        }
    }

    public static int toInt(int[] arr) {
        int sum = 0;
        int t = 1;
        for (int i = 4; i >= 0; i--) {
            sum += arr[i] * t;
            t *= 10;
        }
        return sum;
    }

    public static int m2m1(int[] arr) {
        for (int i = 0; i < 5; i++) {
            arr[i] *= 2;
        }
        for (int i = 4; i >= 1; i--) {
            if (arr[i] >= 10) {
                arr[i] -= 10;
                arr[i - 1] += 1;
            }
        }
        if(arr[0] >= 10)
            return 0;
        for (int i = 0; i < 5; i++) {
            if (arr[i] != 0) {
                arr[i] -= 1;
                break;
            }
        }
        return toInt(arr);
    }

    public static int[] toIntArr(int N) {
        int[] temp = new int[5];
        int idx = 4;
        while(N >= 10) {
            temp[idx--] = N %10;
            N /= 10;
        }
        temp[idx] = N;
        return temp;
    }
}


