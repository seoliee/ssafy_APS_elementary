package SWEA.Flatten1208;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/Flatten1208/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테스트 케이스 10개이므로 10번 반복
		for (int i = 0; i < 10; i++) {
			// 주어진 덤프 횟수
			int dumpCnt = sc.nextInt();

			// 크기 100인 배열 초기화
			int arr[] = new int[100];
			// 배열 요소 받음
			for (int j = 0; j < 100; j++) {
				arr[j] = sc.nextInt();
			}
			

			// 덤프 반복 시작
			for (int j = 0; j < dumpCnt; j++) {
				
				// max - min 초기화
				int result = 99;
				
				// 최대값, 최소값 초기화
				int maxInt = arr[0];
				int minInt = arr[0];
					
				// 최대값, 최소값의 위치 인덱스
				int maxIdx = 0;
				int minIdx = 0;
					
				// 최대값, 최소값 찾기 시작
				for (int k = 0; k < 99; k++) {
					// 최대값 찾는 조건
					if (maxInt < arr[k + 1]) {
						maxInt = arr[k + 1];
						maxIdx = k + 1;
					}
					// 최소값 찾는 조건
					if (minInt > arr[k + 1]) {
						minInt = arr[k + 1];
						minIdx = k + 1;
					}
				}  // 최대값, 최소값 찾기 끝
					
				// 덤프 해
				arr[maxIdx]--;
				arr[minIdx]++;
				// 덤프 한번 끝났어
					
				// result 구해봐
				result = arr[maxIdx] - arr[minIdx];

				// result가 0이나 1이면 덤프 끝내
				if (result == 0 || result == 1) break;	
				
			}  // 덤프 끝
			
			System.out.printf("#%d %d\n", (i + 1), result);
		}
	}
}
