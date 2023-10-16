package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 8394번
public class 악수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] handshake = new int[n+1];
		handshake[0] = 1;
		handshake[1] = 1;

		for(int i = 2; i <= n; i++) {
			handshake[i] = (handshake[i-1]%10) + (handshake[i-2]%10);
		}

		System.out.println(handshake[n]%10);
	}

}
