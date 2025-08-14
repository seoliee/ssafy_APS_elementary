package SWEA.달팽이숫자1954;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/달팽이숫자1954/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 입력받기
		int T = sc.nextInt();
		
		//테케만큼 반복 시작
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();  // 배열 크기 입력받기
			int[][] snail = new int[N][N];  // 배열 선언하기
			
			int num = 1;  // 배열에 들어갈 숫자 선언 및 초기화
			
			// 배열의 첫 줄 채우기
			for(int c = 0; c < N; c++) {
				snail[0][c] = num++;
			}
			
			// 배열의 상하좌우 범위 설정
			int top = 1;
			int bottom = N - 1;
			int left = 0;
			int right = N - 1;
			
			while(left <= right && top <= right) {
				for(int r = top; r <= bottom; r++) {
					snail[r][right] = num++;
				}
				right--;
				if(left > right || top > bottom) {
					break;
				}
				
				for(int c = right; c >= left; c--) {
					snail[bottom][c] = num++;
				}
				bottom--;
				if(left > right || top > bottom) {
					break;
				}
				
				for(int r = bottom; r >= top; r--) {
					snail[r][left] = num++;
				}
				left++;
				if(left > right || top > bottom) {
					break;
				}
				
				for(int c = left; c <= right; c++) {
					snail[top][c] = num++;
				}
				top++;
			}
			
			System.out.println("#" + tc);
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					System.out.print(snail[r][c] + " ");
				}
				System.out.println();
			}
			

		}  // 테케만큼 반복 끝
	}

}
