package SWEA.중간평균값구하기1984;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("./src/SWEA/중간평균값구하기1984/input.txt");
		Scanner sc = new Scanner(file);
		
		int T = sc.nextInt();
		
		// 테스트케이스만큼 반복 시작
		for(int tc = 1; tc <= T; tc++) {
			
			// 각 줄의 배열 입력받기
			int[] arr = new int[10];
			for(int i = 0; i < 10; i++) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);
			
			// 최대 최소 제외한 합 구하기
			double sum = 0;
			for(int i = 1; i < 9; i++) {
				sum += arr[i];
			}
			
			double avg = sum / 8.0;
			
			int result = (int)Math.round(avg);
			
			System.out.println("#" + tc + " " + result);
			
		}  // 테케만큼 반복 끝

	}

}
