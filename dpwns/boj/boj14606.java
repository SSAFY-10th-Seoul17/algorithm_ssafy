import java.io.*;

class Main {
	public static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dividePizza(n);
		System.out.println(sum);
		br.close();
	}
	/*
        가장 큰 행복을 얻으려면 2등분해야 최대 ^2
    */
	public static void dividePizza(int n) {
		if(n == 1) {
			return;
		}
		int n1 = n / 2, n2 = n % 2 == 0 ? n / 2 : (n / 2) + 1;
		sum += n1 * n2;
		dividePizza(n1);
		dividePizza(n2);
	}
}