package 공굴러가유;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/공굴러가유/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 개수
		int T = sc.nextInt();
		
		// 테케 개수만큼 반복 시작
		for(int tc = 1; tc <= T; tc++) {
			// 배열 크기
			int N = sc.nextInt();
			// 배열 선언
			int[][] tower = new int[N][N];
			// 배열 입력받기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					tower[i][j] = sc.nextInt();
				}
			}
			
			// 사방탐색 벡터
			int[] dr = {-1,1,0,0};  //하상좌우
			int[] dc = {0,0,-1,1};  //하상좌우
			
			// 현재 탐색지점에서의 최대 거리 초기화
			int max = 1;
			
			// 전체 탐색 시작
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int dist = 1;  // 거리는 1로 세팅 (무슨 거리....?)
					int start = tower[i][j];  //현재 탐색하는 지점
					int x = i; // 행 인덱스(?)
					int y = j; // 열 인덱스(?)
					System.out.printf("현재 위치: %d, %d", i, j);
					System.out.println();
					System.out.println("행 인덱스: " + x);
					System.out.println("열 인덱스: " + y);
					
					while(true) {  // break 만날때까지 반복
						int next = start;  // 값과 인덱스 위치를 바꾸기 위해 별도의 변수 지정(?)
						int nx = x;
						int ny = y;
						
						for(int a = 0; a < 4; a++) { // 사방탐색
							int r = x + dr[a];
							int c = y + dc[a];
							
							if(r >= 0 && c >= 0 && r < N && c < N) { // 범위 안이면
								System.out.printf("탐색하는 곳의 좌표: {%d, %d}", r, c);
								System.out.println();
								int temp = tower[r][c]; // temp에 넣고 (임시값)
								if(start > temp) {
									start = temp; //시작점을 갱신
									nx = r; //인덱스 갱신
									ny = c;
									System.out.println("갱신된 시작점: " + start);
									System.out.printf("갱신된 인덱스: {%d, %d}", nx, ny);
									System.out.println();
									
								}
							}
							
							
						} //상하좌우 탐색해서 자기보다 작은게 없으면 갱신x, 자기보다 작은게 있으면 start에 값 nx ny에 인덱스가 있음
						// 사방탐색 끝
						System.out.println("---------------------------");
						
						if(start == next) {	 //next는 시작값을 담아둔 변수, start와 같으면 주변(상하좌우)로 이동x
							System.out.println("다음 좌표부터 탐색");
							System.out.println("---------------------------");
							break;						
						}
							dist++; //거리 1 더하고 x, y에 이동할 인덱스 담고 다시 위에서 바뀐 x,y에서 델타탐색
							System.out.println("거리에 1 더함: " + dist);
							x = nx;
							y = ny;
							System.out.printf("바뀐 좌표: %d, %d", x, y);
							System.out.println();
							
					}
					
					if(max < dist) max = dist;  //모든 배열 인덱스마다 max 최신화
					System.out.println("최신화된 max: " + max);
					System.out.println("---------------------------");
									
				}
			}  // 전체 탐색 끝
			
			System.out.println("#" + tc + " " + max);
			
			
		}  // 테케 개수만큼 반복 끝
	}
}
