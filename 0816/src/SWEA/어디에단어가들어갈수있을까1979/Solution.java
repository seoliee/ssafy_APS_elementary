package SWEA.어디에단어가들어갈수있을까1979;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/어디에단어가들어갈수있을까1979/input.txt");
		Scanner sc = new Scanner(file);

		// 테케 입력
		int T = sc.nextInt();
		
		
		// 테케만큼 반복 시작
		for(int tc = 1; tc <= T; tc++) {
			
			// N 입력
			int N = sc.nextInt();
			
			// K 입력
			int k = sc.nextInt();
			
			// 입력받을 배열 선언
			int[][] arr = new int[N][N];
			
			// 배열 입력받기
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					arr[r][c] = sc.nextInt();
				}
			}
			
			// 배열 하나씩 순회
			// 자리의 개수 선언 및 초기화
			int cnt = 0;
			
			// 1. 가로로 한 번 순회 (r -> c)
			for(int r = 0; r < N; r++) {
				int wordLen = 0;
				for(int c = 0; c < N; c++) {
					if(arr[r][c] == 1) {
						wordLen++;
					} else {
						if(wordLen == k) {
							cnt++;
						}
						wordLen = 0;
					}
				}
				if(wordLen == k) {
					cnt++;
				}
			}

			// 2. 세로로 한 번 순회 (c -> r)
			for(int c = 0; c < N; c++) {
				int wordLen = 0;
				for(int r = 0; r < N; r++) {
					if(arr[r][c] == 1) {
						wordLen++;
					} else {
						if(wordLen == k) {
							cnt++;
						}
						wordLen = 0;
					}
				}
				if(wordLen == k) {
					cnt++;
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}  // 테케만큼 반복 끝
	}

}
