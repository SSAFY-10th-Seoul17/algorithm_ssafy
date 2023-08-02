import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] enjoy = new int[n+1];
		
		for (int i = 1; i < enjoy.length; i++) {
			if (i == 1) {
				continue;
			}
			else {
				for (int j = 1; j < i/2+1; j++) {
					enjoy[i] = Math.max(enjoy[i], j * (i-j) + enjoy[j] + enjoy[i-j]);
				}
			}
		}
		System.out.println(enjoy[n]);
		
	} // end of main
} // end of class
