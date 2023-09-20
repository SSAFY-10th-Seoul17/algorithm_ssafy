import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1. 아이디어
 * 최소 배터리용량찾기(매개변수탐색)
 * - 배터리 범위 range(출발지~주유소~도착지 중 긴 거리,도착지): 충전소를 한번도 안들리면 배터리 도착지만큼 필요함
 * 	- 최대 K개의 주유소를 들려서 L에 도달할 수 있는가? -> 매개변수탐색 조건
 * 	- 해당 배터리를 가지고 도착지까지 가면서 들린 주유소 수 return
 */

public class Main {
	private static int destination;
	private static int stationNum;
	private static int maxVisit;
	private static int[] stations;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		destination = Integer.parseInt(st.nextToken());
		stationNum= Integer.parseInt(st.nextToken());
		maxVisit = Integer.parseInt(st.nextToken());
		stations = new int[stationNum+1];
		st = new StringTokenizer(br.readLine(), " ");
		int maxDist = 0;
		int before = 0;
		for (int i = 0; i < stationNum; i++) {
			stations[i]= Integer.parseInt(st.nextToken());
			int dist = stations[i]-before;
			maxDist = Math.max(maxDist, dist);
			before = stations[i];
		}
		maxDist = Math.max(maxDist, destination-before);
		stations[stationNum] = destination;
		System.out.println(minBattery(maxDist,destination));
	} // end of main

	private static int minBattery(int left, int right) {
		int ans = 0;
		while(left<=right) {
			int mid = (left+right)>>1;
			if(visitStation(0,mid)<=maxVisit) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		return ans;
	}

	private static int visitStation(int location, int battery) {
		int cnt = 0;
		int Bleft = battery;
		for (int i = 0; i <= stationNum; i++) {
			int need = stations[i]-location;
			if(need > Bleft) {
				cnt++;
				Bleft = battery;
			}
			Bleft -= need;
			location = stations[i];
		}
		return cnt;
	}
} // end of class
