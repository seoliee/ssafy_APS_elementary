package SWEA.초심자의회문검사1989;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/SWEA/초심자의회문검사1989/input.txt");
		Scanner sc = new Scanner(file);
		
		// 테케 입력받기
		int T = sc.nextInt();
		
		// 테케만큼 반복 시작
		for(int tc = 1; tc <= T; tc++) {
			
			// 문자열 입력받기
			String pal = sc.next();
			
			// 입력받은 문자열 길이 구하기
			int palLen = pal.length();
			
			// 회문인지 저장하는 boolean 변수
			boolean isPal = true;
			
			// charAt(i)과 charAt(길이-i-1)이 같으면 넘어가
			// 이걸 (문자열길이 + 1) / 2까지 반복해
			for(int i = 0; i <= (palLen + 1) / 2; i++) {
				// 그리고 중간에 isPal = false면 멈추고 반복문 빠져나와
				// 그리고 0 출력
				
				// 끝까지 isPal = true면 1 출력 
				if(pal.charAt(i) != pal.charAt(palLen-i-1)) {
					isPal = false;
					break;
				} else {
					continue;
				}
			}
				
				if(isPal) {
					System.out.println("#" + tc + " 1");
				} else {
					System.out.println("#" + tc + " 0");
				}

		}  // 테케만큼 반복 끝

	}

}
