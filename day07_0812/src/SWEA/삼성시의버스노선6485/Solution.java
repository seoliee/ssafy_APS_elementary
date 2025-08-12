package SWEA.삼성시의버스노선6485;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("./src/SWEA/삼성시의버스노선6485/s_input.txt");
		Scanner sc = new Scanner(file);
		
		// 테스트케이스 입력 받기
		int T = sc.nextInt();
		
		// 주어진 테스트케이스 횟수만큼 반복
		for(int tc = 0; tc < T; tc++) {
			// 버스노선 개수 입력받기
			int N = sc.nextInt();
			
			// 각 버스노선이 다니는 정류장 범위 저장할 배열 선언
			int[][] stationRange = new int[N][2];
			
			// 해당 배열에 값 입력 받기
			for(int i = 0; i < N; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				stationRange[i][0] = a;
				stationRange[i][1] = b;
			}
			
//			System.out.println(Arrays.deepToString(stationRange));

			// 버스 정류장의 개수 P 입력 받기
			int P = sc.nextInt();
			
			// 각 버스 정류장의 번호 저장할 배열 선언
			int[] stationNum = new int[P];
			
			// 배열에 버스 정류장 번호 저장
			for(int i = 0; i < P; i ++)	{
				stationNum[i] = sc.nextInt();
			}
			
			// 출력할 결과 배열 선언
			int[] result = new int[P];
			
			// 각 버스정류장마다 다니는 노선의 횟수 증가
			
			for(int i = 0; i < stationRange.length; i++) {
				int min = stationRange[i][0];
				int max = stationRange[i][1];
				
//				System.out.println(min);
//				System.out.println(max);
				
				for(int j = 0; j < stationNum.length; j++) {
					if(stationNum[j] >= min && stationNum[j] <= max) {
						result[j] += 1;
//						System.out.println(Arrays.toString(result));
					}
				}
			}
			// 결과 출력
			System.out.printf("#%d ", tc+1);
			for(int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
		}

	}

}
