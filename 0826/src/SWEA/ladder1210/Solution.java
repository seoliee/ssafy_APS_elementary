package SWEA.ladder1210;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/ladder1210/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 10개 (10번 반복 시작)
		for(int tc = 1; tc <= 10; tc++) {
			
			// 테케 입력받음
			int t = sc.nextInt();
			
			// 100 * 100 크기의 배열 생성
			int[][] arr = new int[100][100];
			
			// 주어진 배열 입력받음
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			// 주어진 배열의 100번째 줄의 값이 2일때까지 찾아
			int c = 0;
			while(arr[99][c] != 2) {
				c++;
			}
			
			// 처음 시작 위치 설정
			int curr_r = 99;
			int curr_c = c;
			
			while(curr_r > 0) {
				if(curr_c > 0 && arr[curr_r][curr_c - 1]  == 1) {  // 왼쪽에 길 있음
					// c가 1인 경우 계속 왼쪽으로 이동
					while(curr_c > 0 && arr[curr_r][curr_c - 1] == 1) {
						curr_c--;
					}
				}  // 오른쪽으로 이동 가능한지 확인
				else if(curr_c < 99 && arr[curr_r][curr_c + 1] == 1) {
					// c가 1인 경우 계속 오른쪽으로 이동
					while(curr_c < 99 && arr[curr_r][curr_c + 1] == 1) {
						curr_c++;
					}
				}  // 좌우에 다 길 없는 경우 위로 이동
				curr_r--;  
			}
			System.out.println("#" + tc + " " + curr_c);
			
		}  // 10번 반복 끝
		
	}

}
