package SWEA.Sum1209;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/Sum1209/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 10개 (10회 반복)
		for(int tc = 1; tc <= 10; tc++) {
			// 테케 입력받기
			int t = sc.nextInt();
			// 100 * 100 크기의 배열 선언
			int[][] arr = new int[100][100];
			// 배열 입력받기
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			// 최대 합 변수 설정
			int maxSum = 0;
			// 배열의 합 변수 설정
			int arrSum = 0;
			
			// 각 row의 합 구하기
			for(int r = 0; r < 100; r++) {
				for(int c = 0; c < 100; c++) {
					arrSum += arr[r][c];
				}
				if(maxSum < arrSum) {
					maxSum = arrSum;
//					System.out.println("row의 합: " + maxSum);
				}
				arrSum = 0;
			}
			
			// 각 column의 합
			for(int c = 0; c < 100; c++) {
				for(int r = 0; r < 100; r++) {
					arrSum += arr[r][c];
				}
				if(maxSum < arrSum) {
					maxSum = arrSum;
//					System.out.println("column의 합: " + maxSum);
				}
				arrSum = 0;
			}
			
			// 대각선의 합
			for(int r = 0, c = 0; r < 100; r++, c++) {
				arrSum += arr[r][c];
			}
			if(maxSum < arrSum) {
				maxSum = arrSum;
//				System.out.println("대각선의 합: " + maxSum);
				arrSum = 0;
			}
			
			System.out.println("#" + tc + " " + maxSum);
		}  // 10회 반복 끝
	}

}
