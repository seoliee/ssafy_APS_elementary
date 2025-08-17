package 일타싸피;

public class 연습 {

	public static void main(String[] args) {
		double thetaDeg = 90;  // 90도
		double thetaRad = Math.toRadians(thetaDeg);  // 90도를 라디안으로 변환
		
		System.out.println("90도 = " + thetaRad + " 라디안");
		System.out.println("sin(90º) = " + Math.sin(thetaRad));
		System.out.println("cos(90º) = " + Math.cos(thetaRad));
	}
}
