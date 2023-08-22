import java.util.*;
import java.io.*;
// 09:40 - 
// 📝

// 과자 중에서 가장 긴 과자를 기준으로 반으로 쪼갠 후 반의 길이로 나눠줄 수 있는 사람 수를 구합니다.
// 해당 길이로 나눠줄 수 있는 사람이 m명 이상이면 : 더 큰 길이로 나누어서 줄 수 있다는 의미를 가집니다.
// 해당 길이로 나눠줄 수 있는 사람이 m명 미만이면 : 긴과자 반 길이로 나누어 줄 수 없다는 의미를 가집니다.
// 따라서 더 크게 나누어줄 수 있다면 가장 긴 과자의 중간값 기준으로 오른쪽으로 범위를 이동시켜 mid값을 새로 정해줍니다.
// 더 작게 나누어주어야 한다면 가장 긴 과자의 중간값 기준으로 왼쪽으로 범위를 이동시켜 mid값을 새로 정해 줍니다.

public class BOJ16401_과자나눠주기 {
	static int n,m,result;
	static int[] snacks;
	public static void main(String[] args) throws Exception {
		// 최대한 긴 과자를 나눠주려함, 같은 길이 과자를 줘야함
		// m명의 조카, n개의 과자 , 막대 과자의 최대길이
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		snacks = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
		}
	
		Arrays.sort(snacks);
		// 제일 큰 수를 기준으로
		int start = 1;
		int end = snacks[n-1];
		
		while(start <= end) {
			int mid = (start+end)/2;
			int cnt = 0;
			
			for (int i=0; i<n; i++) {
				cnt += snacks[i]/mid;
			}
			
			if (cnt >= m ) {
				result = mid;
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		System.out.println(result);
	}
}
