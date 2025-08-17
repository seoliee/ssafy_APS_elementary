package SWEA.암호생성기1225;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static void shiftAndAppend(int[] arr, int val) {
		for(int i = 0; i < 7; i++) {
			arr[i] = arr[i+1];
		}
		arr[7] = val;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/암호생성기1225/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 총 10개
		for(int t = 0; t < 10; t++) {
			
			// 테케 번호 입력받기
			int tc = sc.nextInt();
			
			// 데이터 8개 저장할 배열 선언
			int[] arr = new int[8];
			
			
			// 데이터 8개 받기
			for(int i = 0; i < 8; i++) {
				arr[i] = sc.nextInt();
			}
			
			// 감소값 1 -> 5
			// 반복할때마다 d++
			int d = 0;
			while(true) {
				d = (d % 5) + 1;
				int tmp = arr[0] - d;
				
				if(tmp <= 0) {
					shiftAndAppend(arr, 0);
					break;
				} else {
					shiftAndAppend(arr, tmp);
				}
			}
			
			System.out.print("#" + tc + " ");
			for(int i = 0; i < 8; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}  // 10번 반복 끝
		
	}

}
