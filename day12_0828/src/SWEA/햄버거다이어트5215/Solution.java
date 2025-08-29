package SWEA.햄버거다이어트5215;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/햄버거다이어트5215/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 개수 입력받아
		int T = sc.nextInt();
		
		// 테케 개수만큼 반복해
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();  // 재료 개수
			int L = sc.nextInt();  // 제한 칼로리
			
			// 맛, 칼로리 입력받을 배열 선언
			int[] flavor = new int[N];
			int[] cal = new int[N];
			
			// 재료 개수만큼 입력받기
			for(int i = 0; i < N; i++) {
				flavor[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}
			
			// 변수 설정
			int maxFlavor = 0;
			
			for(int i = 0; i < (1 << N); i++) {
				int totalFlavor = 0;
				int totalCal = 0;
				for(int j = 0; j < N; j++) {
					if((i & (1 << j)) != 0) {
						totalCal += cal[j];
						totalFlavor += flavor[j];
					}
//					System.out.println("총 칼로리: " + totalCal);
//					System.out.println("총 맛: " + totalFlavor);
//					System.out.println("-------------------------------");
					if(totalCal <= L) {
						if(totalFlavor >= maxFlavor) {
							maxFlavor = totalFlavor;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + maxFlavor);

			
		}  // 테케 개수만큼 반복 끝

	}

}
