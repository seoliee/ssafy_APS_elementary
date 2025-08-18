package SWEA.농작물수확하기2805;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/농작물수확하기2805/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테스트케이스
		int T = sc.nextInt();
		
		// 테스트케이스만큼 반복
		for(int tc = 1; tc <= T; tc++) {
			
			// N 입력받기
			int N = sc.nextInt();
			
			// 농장 배열 선언
			char[][] farm = new char[N][N];
			
			for(int i = 0; i < N; i++) {
				String line = sc.next();
				farm[i] = line.toCharArray();
			}
			
			int totalProfit = 0;
			int middle = N / 2;
			int start = middle;
			int end = middle;
			
			// 상단 마름모 + 중앙행 수확
			for(int i = 0; i < N; i++) {
				// 현재 행의 마름모 범위 내의 값 모두 더함
				for(int j = start; j <= end; j++) {
					// char값을 int 값으로 변환해서 더함
					totalProfit += farm[i][j] - '0';
				}
				
				// 마름모 너비 조정
				if(i < middle) {
					// 중앙행까지 너비 넓어짐
					start--;
					end++;
				} else {
					// 중앙행 이후로는 너비 좁아짐
					start++;
					end--;
				}
			}
			
			System.out.println("#" + tc + " " + totalProfit);
			
			
			
			
		}  // 테스트케이스만큼 반복 끝
		
		
		
	}
	
	
	
}
