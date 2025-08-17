package 공굴러가유;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MySol {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/공굴러가유/input.txt");
		Scanner sc = new Scanner(file);
		
		int T = sc.nextInt();
		
		// 테케만큼 반복 시작
		for(int tc = 0; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] tower = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					tower[i][j] = sc.nextInt();
				}
			}
			
			// 최대 이동 거리 선언, 초기화
			int maxRoute = 1;
			
			// 사방탐색 벡터
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			
			
			// 전체 탐색 시작
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					
					// break 나올 때까지 반복
					while(true) {
						
						// 사방탐색 시작
						for(int a = 0; a < 4; a++) {
							int newR = r + dr[a];
							int newC = c + dc[a];
							
							// 만약 탐색 인덱스가 범위 내에 있다면
							if(newR >= 0 && newC >= 0 && newR < N && newC < N) {
								
								// 
								
								
							}
						}
						
					}
					
					
					
					
					
					
					
				}
			}  // 전체 탐색 끝
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}  // 테케만큼 반복 끝
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
