import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//3151번
public class 합이0 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] coding = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			coding[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(coding);
		
		//값을 하나 고정
		//투포인터로 0에 가깝게 움직이게 한다
		long count = 0;
		for(int index = 0; index < n; index++) {
			int start = index+1;
			int end = n-1;
			
			while(start < end) {
				int sum = coding[index] + coding[start] + coding[end];
				if(sum < 0) {
					start++;
				}else if(sum > 0) {
					end--;
				}else{//sum==0
					//중복되는 경우
					if(coding[start] == coding[end]) {
						int len = end-start+1;
						count += len*(len-1)/2;
						break;
					}
					
					//왼쪽, 왼쪽+1 ... 같은 숫자가 나오는 경우
					int left = 1;
					while(coding[start] == coding[start+1] && start+1<end) {
						left++;
						start++;
					}
					
					//오른쪽, 오른쪽-1, ... 같은 숫자가 나오는 경우1
					int right = 1;
					while(coding[end] == coding[end-1] && start<end-1) {
						right++;
						end--;
					}
					count += left*right;
					start++;
					end--;
				}
				
				
			}
		}
		
		System.out.println(count);
		
	}

	
	//10
	//2 -5 2 3 -4 7 -4 0 1 -6
	// 정렬: [-6, -5, -4, -4, 0, 1, 2, 2, 3, 7]
	
	//(2, -5, 3), (2, 2, -4), (2, 2, -4), (-5, 2, 3), (3, -4, 1), (3, -4, 1)
	//중복되는 것들은 어떻게 할까.
	//원하는 값이 중간에 없는 경우 어떻게 할까
}
