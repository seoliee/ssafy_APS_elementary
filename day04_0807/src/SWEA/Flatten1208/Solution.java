package SWEA.Flatten1208;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/Flatten1208/input.txt");
		Scanner sc = new Scanner(file);
		
		// 덤프 횟수 입력 받음
		int T = sc.nextInt();
		
		// 입력받은 상자 개수 담을 배열 선언
		int[] arr = new int[100];
		
		for(int d = 0; d < T; d++) {
			for(int i = 0; i < 100; i++) {
				// 한 줄로 나열된 정수 중 하나만 받아옴
				int box = sc.nextInt();
				// 각 배열 인덱스에 입력받은 정수 담기
				arr[i] = box;
			}  // 상자 개수 한줄을 배열에 입력받음
			
			int maxNum = arr[0];
			for(int i = 0; i < 99; i++) {
				if(maxNum < arr[i + 1]) {
					maxNum = arr[i + 1];
					int newMaxIdx = i + 1;
				} else {
					continue;
				}
			}  // 상자 개수 배열에서 가장 큰 값 찾음
			System.out.println(maxNum);
			
			int minNum = arr[0];
			for(int i = 0; i < 99; i++) {
				if(minNum > arr[i + 1]) {
					minNum = arr[i + 1];
					int newMinIdx = i + 1;
				} else {
					continue;
				}
			}  // 상자 개수 배열에서 가장 작은 값 찾음
			System.out.println(minNum);
			
			int result = maxNum - minNum;
			System.out.println(result);
		}  // maxNum - minNum의 결과 확인
//		if(result >= 0 && result <= 1) {
//			break;

	}

}
