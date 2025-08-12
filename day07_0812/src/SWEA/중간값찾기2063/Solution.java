package SWEA.중간값찾기2063;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
//		File file = new File("./src/SWEA/중간값찾기2063/input.txt");
		Scanner sc = new Scanner(System.in);
		
		// 주어진 정수 개수
		int N = sc.nextInt();
		
		// 주어진 정수 저장하는 배열
		int[] arr = new int[N];
		
		// 정수 배열에 저장
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 버블 정렬 사용할거야
		// 정렬 시작
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < (N - 1) - i; j++) {
				if(arr[j] < arr[j + 1]) {
					continue;
				} else {
					int tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}  // 정렬 끝
		
		
		int idx = (N - 1) / 2;
		System.out.println(arr[idx]);


	}

}
