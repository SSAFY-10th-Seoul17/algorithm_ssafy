import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class boj24230 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Queue<Integer> num = new LinkedList<>(); // 현재 위치
		Queue<Integer> length = new LinkedList<>(); // 현재까지 온 거리
		num.add(n);
		length.add(0);
		
		int[] arr = new int[400000];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.MAX_VALUE;
		}
		
		while (!num.isEmpty()) {
			int now = num.poll();
			int len = length.poll();
			if(now < 0) continue;
			if (arr[now] < len)
				continue;
			arr[now] = len;
			
			if (now == k) {
				if (len < arr[k])
					arr[k] = len;
				continue;
			}
			else if (now > k) {
				num.add(k);
				length.add(len + now - k);
			}
			else {
				num.add(now + 1);
				length.add(len + 1);

				num.add(now - 1);
				length.add(len + 1);

				num.add(now * 2);
				length.add(len + 1);
			}
		}
		System.out.println(arr[k]);
	}
}
