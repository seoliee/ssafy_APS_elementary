package 일타싸피;

import java.io.*;
import java.net.*;

public class 일타싸피_12기 {




/*
 * 5문장 요약:
	- 흰 공. 목표 공, 상대 공의 위치를 실시간으로 분석하여 가장 가까운 목표 공을 선정합니다.
	- 상대 공과 충돌할 가능성이 있는지 계산하여, 위험을 피할 수 있는 경우 쿠션을 사용합니다.
	- 단 쿠션 사용 시 쿠션 포인트가 포켓 근처에 있다면 일반 전략으로 돌아갑니다.
	- 각 공의 거리와 각도를 바탕으로 최적의 각도와 파워를 계산하여 정확하고 안전한 샷을 시도합니다.
	- 목표공 앞에 계속 멈추면 실격이기 때문에 이를 방지하고자 안전한 파워의 최솟값이 있습니다.
 */

    // 닉네임을 사용자에 맞게 변경해 주세요.
    static final String NICKNAME = "12기";

    // 일타싸피 프로그램을 로컬에서 실행할 경우 변경하지 않습니다.
    static final String HOST = "127.0.0.1";

    // 일타싸피 프로그램과 통신할 때 사용하는 코드값으로 변경하지 않습니다.
    static final int PORT = 1447;
    static final int CODE_SEND = 9901;
    static final int CODE_REQUEST = 9902;
    static final int SIGNAL_ORDER = 9908;
    static final int SIGNAL_CLOSE = 9909;

    // 게임 테이블의 크기와 포켓의 좌표 정의
    static final int TABLE_WIDTH = 254;
    static final int TABLE_HEIGHT = 127;
    static final int NUMBER_OF_BALLS = 6;
    static final int[][] HOLES = { { 0, 0 }, { 127, 0 }, { 254, 0 }, { 0, 127 }, { 127, 127 }, { 254, 127 } };

    public static void main(String[] args) {

        Socket socket = null;
        String recv_data = null;
        byte[] bytes = new byte[1024];
        float[][] balls = new float[NUMBER_OF_BALLS][2];
        int order = 0;

        try {
            socket = new Socket();
            System.out.println("Trying Connect: " + HOST + ":" + PORT);
            socket.connect(new InetSocketAddress(HOST, PORT));
            System.out.println("Connected: " + HOST + ":" + PORT);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // 서버에 닉네임 전송
            String send_data = CODE_SEND + "/" + NICKNAME + "/";
            bytes = send_data.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
            System.out.println("Ready to play!\n--------------------");

            while (socket != null) {

                // 데이터 수신
                bytes = new byte[1024];
                int count_byte = is.read(bytes);
                recv_data = new String(bytes, 0, count_byte, "UTF-8");
                System.out.println("Data Received: " + recv_data);

                // 수신된 데이터 파싱 및 공 좌표 저장
                String[] split_data = recv_data.split("/");
                int idx = 0;
                try {
                    for (int i = 0; i < NUMBER_OF_BALLS; i++) {
                        for (int j = 0; j < 2; j++) {
                            balls[i][j] = Float.parseFloat(split_data[idx++]);
                        }
                    }
                } catch (Exception e) {
                    // 데이터 손상 시 재요청
                    bytes = (CODE_REQUEST + "/" + CODE_REQUEST).getBytes("UTF-8");
                    os.write(bytes);
                    os.flush();
                    System.out.println("Received Data has been corrupted, Resend Requested.");
                    continue;
                }

                // 플레이어의 순서 확인 또는 연결 종료 신호 처리
                if (balls[0][0] == SIGNAL_ORDER) {
                    order = (int) balls[0][1];
                    System.out.println("\n* You will be the " + (order == 1 ? "first" : "second") + " player. *\n");
                    continue;
                } else if (balls[0][0] == SIGNAL_CLOSE) {
                    break;
                }

                // 각 공의 위치 출력
                for (int i = 0; i < NUMBER_OF_BALLS; i++) {
                    System.out.println("Ball " + i + ": " + balls[i][0] + ", " + balls[i][1]);
                }

                float angle = 0.0f;
                float power = 0.0f;

                // 흰 공(첫 번째 공)의 x, y 좌표를 변수에 저장
                float whiteBall_x = balls[0][0];
                float whiteBall_y = balls[0][1];

                // 목표 공의 초기 좌표를 설정 (아직 목표 공이 결정되지 않은 상태를 의미)
                float targetBall_x = -1;
                float targetBall_y = -1;

                // 후보 공 리스트 설정 (첫 번째 플레이어는 1번, 3번 공, 두 번째 플레이어는 2번, 4번 공)
                int[] candidateBalls;
                if (order == 1) {
                    candidateBalls = new int[] { 1, 3 };
                } else {
                    candidateBalls = new int[] { 2, 4 };
                }

                // 최소 거리를 저장하기 위한 변수 초기화 (현재 가능한 최대값으로 설정)
                // 이후 실제 공과의 거리 계산 시 이 값을 갱신하여 가장 가까운 공을 찾기 위함
                float minDistance = Float.MAX_VALUE;

                // 각 후보 공과의 거리 계산 및 가장 가까운 공 선택
                for (int i : candidateBalls) {
                    // 공이 포켓되지 않은 경우만 계산 (포켓된 공은 좌표가 음수)
                    if (balls[i][0] >= 0) {
                        // 흰 공과 후보 공 간의 거리 계산
                        float distance = calculateDistance(whiteBall_x, whiteBall_y, balls[i][0], balls[i][1]);
                        
                        // 현재 계산된 거리가 이전에 기록된 최소 거리보다 작으면
                        // 이 공을 목표 공으로 설정하고 최소 거리 갱신
                        if (distance < minDistance) {
                            minDistance = distance;
                            targetBall_x = balls[i][0];
                            targetBall_y = balls[i][1];
                        }
                    }
                }

                // 만약 모든 후보 공이 포켓된 상태라면 검은 공(5번 공)을 목표로 설정
                if (targetBall_x < 0) {
                    targetBall_x = balls[5][0];
                    targetBall_y = balls[5][1];
                }

                // 상대 공 리스트 설정 (첫 번째 플레이어는 2번, 4번 공, 두 번째 플레이어는 1번, 3번 공)
                int[] opponentBalls;
                if (order == 1) {
                    opponentBalls = new int[] { 2, 4 };
                } else {
                    opponentBalls = new int[] { 1, 3 };
                }

                int riskCount = 0;

                // 상대 공과의 위험 각도 계산
                for (int i : opponentBalls) {
                    // 상대 공이 포켓되지 않은 경우만 고려 (포켓된 공은 좌표가 음수)
                    if (balls[i][0] > 0 && balls[i][1] > 0) {
                        // 목표 공과 흰 공, 그리고 상대 공 사이의 각도를 계산
                        float angleBetween = calculateRiskAngle(targetBall_x, targetBall_y, whiteBall_x, whiteBall_y, balls[i][0], balls[i][1]);
                        
                        // 만약 각도가 90도 미만이라면, 두 공 사이의 충돌 위험이 높다고 판단하여 리스크 카운트를 증가시킴
                        if (angleBetween < 90) {
                            riskCount++;
                            System.out.printf("riskCount = " + riskCount + "\n");
                        }
                    }
                }

                // 위험 각도가 하나만 감지된 경우 쿠션 사용 전략 선택
                // riskCount가 1일 때만 쿠션을 사용할지 여부를 결정
                boolean useCushion = (riskCount == 1);


                if (useCushion) {
                    // 위험도가 높을 경우(상대 공과의 충돌 가능성) 쿠션 사용 전략 채택
                    float[] cushionPoint = calculateCushionPoint(whiteBall_x, whiteBall_y, targetBall_x, targetBall_y);
                    
                    // 계산된 쿠션 포인트가 포켓 근처에 있는지 확인
                    if (isNearPocket(cushionPoint)) {
                        // 쿠션 포인트가 포켓 근처에 있으면 직접 공략(Direct) 전략 사용
                        angle = calculateDirectAngle(whiteBall_x, whiteBall_y, targetBall_x, targetBall_y);
                        power = calculateDirectPower(whiteBall_x, whiteBall_y, targetBall_x, targetBall_y);
                        // 파워가 너무 낮으면 최소 파워를 7로 설정
                        if (power < 7) {
                            power = 7;
                        }
                    } else {
                        // 쿠션 포인트가 포켓 근처가 아니면 쿠션을 이용한 전략 사용
                        angle = calculateDirectAngle(whiteBall_x, whiteBall_y, cushionPoint[0], cushionPoint[1]);
                        power = calculateCushionPower(whiteBall_x, whiteBall_y, cushionPoint[0], cushionPoint[1]);
                        // 파워가 너무 낮으면 최소 파워를 7로 설정
                        if (power < 7) {
                            power = 7;
                        }
                    }
                } else {
                    // 쿠션을 사용할 필요가 없으면 기본 Direct 전략 사용
                    angle = calculateDirectAngle(whiteBall_x, whiteBall_y, targetBall_x, targetBall_y);
                    power = calculateDirectPower(whiteBall_x, whiteBall_y, targetBall_x, targetBall_y);
                    // 파워가 너무 낮으면 최소 파워를 7로 설정
                    if (power < 7) {
                        power = 7;
                    }
                }
                
                // 각도와 파워 데이터를 서버에 전송
                String merged_data = angle + "/" + power + "/";
                System.out.println("Sending Data: angle = " + angle + ", power = " + power);
                System.out.println("Merged Data: " + merged_data);

                bytes = merged_data.getBytes("UTF-8");
                os.write(bytes);
                os.flush();
                System.out.println("Data Sent: " + merged_data);

                // 데이터가 전송되었는지 확인하기 위한 대기 시간 추가 (선택 사항)
                Thread.sleep(100);  // 0.1초 대기
            }

            os.close();
            is.close();
            socket.close();
            System.out.println("Connection Closed.\n--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 흰 공에서 목적구로의 각도 계산
    static float calculateDirectAngle(float whiteBall_x, float whiteBall_y, float targetBall_x, float targetBall_y) {
        // 목표 공과 흰 공 사이의 가로 및 세로 거리 계산
        float width = Math.abs(targetBall_x - whiteBall_x);
        float height = Math.abs(targetBall_y - whiteBall_y);
        
        // 세로 거리가 0이 아닌 경우 아크탄젠트를 사용해 각도(라디안)를 계산
        // 라디안을 각도로 변환 (0도에서 90도 사이의 값이 도출됨)
        double radian = height > 0 ? Math.atan(width / height) : 0;
        float angle = (float) ((180.0 / Math.PI) * radian);

        // 흰 공과 목표 공이 세로선상에 위치한 경우 (x 좌표가 같을 때) 각도 조정
        if (whiteBall_x == targetBall_x) {
            if (whiteBall_y < targetBall_y) {
                angle = 0;
            } else {
                angle = 180;
            }
        // 흰 공과 목표 공이 가로선상에 위치한 경우 (y 좌표가 같을 때) 각도 조정
        } else if (whiteBall_y == targetBall_y) {
            if (whiteBall_x < targetBall_x) {
                angle = 90;
            } else {
                angle = 270;
            }
        }

        // 목적구가 흰 공을 중심으로 어느 사분면에 위치하는지에 따라 각도 조정
        if (whiteBall_x < targetBall_x && whiteBall_y < targetBall_y) {
            // 목적구가 흰 공의 오른쪽 아래 (제1사분면)에 위치한 경우
            angle = (float) (((180.0 / Math.PI) * radian) + 0);
        } else if (whiteBall_x > targetBall_x && whiteBall_y < targetBall_y) {
            // 목적구가 흰 공의 왼쪽 아래 (제2사분면)에 위치한 경우
            radian = Math.atan(height / width);  // 가로, 세로 값을 바꿔 라디안 재계산
            angle = (float) (((180.0 / Math.PI) * radian) + 270);
        } else if (whiteBall_x > targetBall_x && whiteBall_y > targetBall_y) {
            // 목적구가 흰 공의 왼쪽 위 (제3사분면)에 위치한 경우
            angle = (float) (((180.0 / Math.PI) * radian) + 180);
        } else {
            // 목적구가 흰 공의 오른쪽 위 (제4사분면)에 위치한 경우
            radian = Math.atan(height / width);  // 가로, 세로 값을 바꿔 라디안 재계산
            angle = (float) (((180.0 / Math.PI) * radian) + 90);
        }

        return angle;
    }

    // 흰 공에서 목적구로의 거리 기반 파워 계산
    static float calculateDirectPower(float whiteBall_x, float whiteBall_y, float targetBall_x, float targetBall_y) {
        float distance = (float) Math.sqrt(Math.pow(targetBall_x - whiteBall_x, 2) + Math.pow(targetBall_y - whiteBall_y, 2));
        // 거리와 상관없이 멈추게 되는 마법의 상수는 6
        // 공에 붙으면 파울이기 때문에 그보다 작은 값으로 설정
        return (float) distance / (float) 5; 
    }
    
    // 쿠션 사용 시 파워 계산
    static float calculateCushionPower(float whiteBall_x, float whiteBall_y, float cushion_x, float cushion_y) {
        float distance = (float) Math.sqrt(Math.pow(cushion_x - whiteBall_x, 2) + Math.pow(cushion_y - whiteBall_y, 2));
        // 쿠션 사용 시 파워를 조금 더 높게 설정 (상수 값을 조정)
        // 거리와 상관없이 멈추게 되는 마법의 상수는 5.5
        // 공에 붙으면 파울이기 때문에 그보다 작은 값으로 설정
        return (float) distance / (float) 5.3; 
    }

    // 목표구의 대칭점을 계산하여 쿠션 포인트 설정
    static float[] calculateCushionPoint(float whiteBall_x, float whiteBall_y, float targetBall_x, float targetBall_y) {
        float mirror_x = targetBall_x;
        float mirror_y = targetBall_y;

        // X축 방향에서 대칭 계산 (쿠션 포인트의 x 좌표)
        if (targetBall_x > TABLE_WIDTH / 2) {
            // 목표구가 테이블 오른쪽에 위치하면 오른쪽 벽을 기준으로 대칭 계산
            mirror_x = TABLE_WIDTH + (TABLE_WIDTH - targetBall_x);  
        } else {
            // 목표구가 테이블 왼쪽에 위치하면 왼쪽 벽을 기준으로 대칭 계산
            mirror_x = -targetBall_x;  
        }

        // Y축 방향에서 대칭 계산 (쿠션 포인트의 y 좌표)
        if (targetBall_y > TABLE_HEIGHT / 2) {
            // 목표구가 테이블 아래쪽에 위치하면 아래쪽 벽을 기준으로 대칭 계산
            mirror_y = TABLE_HEIGHT + (TABLE_HEIGHT - targetBall_y);  
        } else {
            // 목표구가 테이블 위쪽에 위치하면 위쪽 벽을 기준으로 대칭 계산
            mirror_y = -targetBall_y;  
        }

        // 계산된 대칭 좌표(쿠션 포인트) 반환
        return new float[]{mirror_x, mirror_y};
    }

    // 포인트가 포켓 근처인지 확인
    // 주어진 포인트가 어느 포켓에 매우 가까운지 (3 단위 이내) 확인
    static boolean isNearPocket(float[] point) {
        for (int[] hole : HOLES) {
            // 포인트와 각 포켓의 좌표 차이가 3 이하인 경우 근처에 있다고 판단
            if (Math.abs(point[0] - hole[0]) <= 3 && Math.abs(point[1] - hole[1]) <= 3) {
                return true;
            }
        }
        return false;
    }

    // 위험 각도 계산 (상대 공과의 각도)
    // 두 점(point1, point2)과 중심점(center) 사이의 각도를 계산하여 위험도를 평가
    static float calculateRiskAngle(float center_x, float center_y, float point1_x, float point1_y, float point2_x, float point2_y) {
        // 벡터 1 계산 (중심점에서 첫 번째 점으로 향하는 벡터)
        float vector1_x = point1_x - center_x;
        float vector1_y = point1_y - center_y;
        // 벡터 2 계산 (중심점에서 두 번째 점으로 향하는 벡터)
        float vector2_x = point2_x - center_x;
        float vector2_y = point2_y - center_y;
        
        // 각 벡터의 크기 계산
        float vector1_size = (float) Math.sqrt(Math.pow(vector1_x, 2) + Math.pow(vector1_y, 2));
        float vector2_size = (float) Math.sqrt(Math.pow(vector2_x, 2) + Math.pow(vector2_y, 2));
        
        // 두 벡터 사이의 코사인 값 계산 (내적을 이용)
        float cosTheta = (vector1_x * vector2_x + vector1_y * vector2_y) / (vector1_size * vector2_size);
        
        // 아크 코사인을 이용하여 각도를 계산하고, 이를 도(degree) 단위로 변환
        return (float) Math.toDegrees(Math.acos(cosTheta));
    }

    // 두 점 간의 거리 계산
    // 주어진 두 점 (x1, y1)과 (x2, y2) 사이의 유클리드 거리 계산
    static float calculateDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}