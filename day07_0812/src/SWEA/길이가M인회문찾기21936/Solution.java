package SWEA.길이가M인회문찾기21936;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/길이가M인회문찾기21936/sample_in.txt");
		Scanner sc = new Scanner(file);

		// 테스트케이스 개수 입력 받기
		int T = sc.nextInt();
		
		// 입력받은 테스트케이스 횟수만큼 반복 시작
		for(int tc = 0; tc < T; tc++) {
			
			// 문자열의 길이
			int N = sc.nextInt();
			
			// 회문의 길이
			int M = sc.nextInt();
			
			// 문자열
			String str = sc.next();
			
			
			// str을 맨 앞부터 M글자씩 잘라서 순회
			for(int i = 0; i < (N-M+1); i++) {
				
				// 회문 여부 선언 및 초기화			
				boolean isPal = true;
				
				// M글자의 회문
				String result = str.substring(i, i + M);
//				System.out.println(result);
				
				// 해당 문자열이 회문인지 확인
				for(int j = 0; j < M/2; j++) {
					
					if(!(result.charAt(j) == result.charAt(M-j-1))) {
						isPal = false;
					} else {
						isPal = true;
						break;
					}
				}  // 주어진 M글자의 문자열이 회문인지 확인 끝

				if(isPal == true) {
					System.out.println("#" + (tc + 1) + " " + result);		
					break;
				} else {
					System.out.println("#" + (tc + 1) + " NONE");	
					continue;
				}
			}  // str 순회 끝
			

		}  // 테스트케이스 횟수만큼 반복 끝
	}

}
