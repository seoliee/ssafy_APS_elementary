package SWEA.의석이의세로로말해요5356;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/의석이의세로로말해요5356/sample_input.txt");
		Scanner sc = new Scanner(file);
		
		// 테스트케이스 입력받기
		int T = sc.nextInt();
		
		// 테케만큼 반복
		for(int tc = 1; tc <= T; tc++) {
			
			String[] strArr = new String[5];
			// 한 줄 입력받기
			for(int i = 0; i < 5; i++) {
				strArr[i] = sc.next(); 
			}
			
			// 입력값의 최대 길이
			int maxLen = strArr[0].length();
			for(int i = 0; i < 4; i++) {
				if(maxLen < strArr[i+1].length()) {
					maxLen = strArr[i+1].length();
				}
			}
			
			// 세로로 출력
			String result = "";
			for(int j = 0; j < maxLen; j++) {
				for(int i = 0; i < 5; i++) {
					if(j < strArr[i].length()) {
						result += strArr[i].charAt(j);
					} else {
						continue;
					}
				}
			
			}

			
			System.out.println("#" + tc + " " + result);
		}  // 테케만큼 반복 끝
		

	}

}
