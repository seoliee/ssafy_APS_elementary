package SWEA.달팽이숫자1954;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/달팽이숫자1954/input.txt");
		Scanner sc = new Scanner(file);

		// 테케 입력받기
		int T = sc.nextInt();
		
		// 테케만큼 반복 시작
		for(int tc = 0; tc < T; tc++) {
			
			// N 입력받기
			int N = sc.nextInt();
			
			// N * N 크기의 2차원 배열 생성
			int[][] snail = new int[N][N];
			

		}  // 테케만큼 반복 끝
	}

}
