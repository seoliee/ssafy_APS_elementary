package SWEA.달팽이숫자1954;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/달팽이숫자1954/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테스트케이스 입력받기
		int T = sc.nextInt();
		
		// 테스트케이스만큼 반복
		for(int tc = 1; tc <= T; tc++) {
			// N 입력받기
			int N = sc.nextInt();
			
			// 배열 크기 선언
			int[][] snail = new int[N][N];
			
			// 배열에 들어갈 숫자 선언, 초기화
			int num = 1;
			
			// 경계값 선언, 초기화
			int top = 1;
			int bottom = N - 1;
			int left = 0;
			int right = N - 1;
			
			// 맨 처음에 한 줄 깔아
			for(int c = 0; c < N; c++) {
				snail[0][c] = num;
				num++;
			}
			
			while(left <= right && top <= bottom) {
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
			
			
			
			
			
			
		}  // 테스트케이스만큼 반복 끝

	}

}
