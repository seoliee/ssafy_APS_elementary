package SWEA.View1206;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
//		File file = new File("./src/SWEA/View1206/sample_input.txt");
//		Scanner sc = new Scanner(file);
		Scanner sc = new Scanner(System.in);
		
		
		// 테케 10개 받기 시작
		for(int tc = 1; tc <= 10; tc++) {
			
			// 결과 선언 및 초기화
			int result = 0;
			
			// 건물의 개수
			int N = sc.nextInt();
			
			// 각 건물의 높이
			int[] height = new int[N];
			for(int i = 0; i < N; i++) {
				height[i] = sc.nextInt();
			}
			
			// 인접 건물과 차이의 최소값 (양수여야 함)
			for(int i = 2; i < N-2; i++) {
				if(height[i] - height[i - 2] <= 0 || height[i] - height[i - 1] <= 0 || height[i] - height[i + 2] <= 0 || height[i] - height[i + 1] <= 0) {
					continue;
				} else {
					int left = Math.min(height[i] - height[i - 2], height[i] - height[i - 1]);
					int right = Math.min(height[i] - height[i + 2], height[i] - height[i + 1]);
					int neighborHeight = Math.min(left, right);					
//					System.out.println(neighborHeight);
					result += neighborHeight;
				}

			}
			System.out.printf("#%d %d", tc, result);
			System.out.println();
			
			
		}  // 테케 10개 받기 끝
		
	}

}
