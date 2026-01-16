/*
 다리를 지나는 트럭
제출 내역
문제 설명
트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며,
다리는 weight 이하까지의 무게를 견딜 수 있습니다.
단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다.
무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

경과 시간	다리를 지난 트럭	다리를 건너는 트럭	대기 트럭
0	[]	[]	[7,4,5,6]
1~2	[]	[7]	[4,5,6]
3	[7]	[4]	[5,6]
4	[7]	[4,5]	[6]
5	[7,4]	[5]	[6]
6~7	[7,4,5]	[6]	[]
8	[7,4,5,6]	[]	[]
따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length,
다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다.
이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

제한 조건
bridge_length는 1 이상 10,000 이하입니다.
weight는 1 이상 10,000 이하입니다.
truck_weights의 길이는 1 이상 10,000 이하입니다.
모든 트럭의 무게는 1 이상 weight 이하입니다.
입출력 예
bridge_length	weight	truck_weights	return
2	10	[7,4,5,6]	8
100	100	[10]	101
100	100	[10,10,10,10,10,10,10,10,10,10]	110
*/

import java.util.*;

class 다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 경과 시간을 저장하는 변수
        int time = 0;

        // 대기 중인 트럭들을 관리하는 큐 (트럭의 무게를 저장)
        Queue<Integer> waitingTrucks = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            waitingTrucks.offer(truckWeight);
        }

        // 다리 위의 트럭들을 관리하는 큐 (다리 길이만큼 0으로 초기화)
        // 0은 빈 공간을 의미, 트럭 무게가 들어가면 해당 위치에 트럭이 있음을 의미
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        // 현재 다리 위에 있는 트럭들의 총 무게
        int currentWeight = 0;

        // 모든 트럭이 다리를 건널 때까지 반복
        while (!bridge.isEmpty()) {
            // 1초 경과
            time++;

            // 다리의 맨 앞 칸이 이동 (트럭이 있었다면 다리를 완전히 건넌 것)
            int exitTruck = bridge.poll();
            currentWeight -= exitTruck;

            // 대기 중인 트럭이 있는 경우
            if (!waitingTrucks.isEmpty()) {
                // 다음 트럭이 다리에 올라갈 수 있는지 확인
                // 조건: 현재 다리 위 무게 + 다음 트럭 무게 <= 다리가 견딜 수 있는 무게
                if (currentWeight + waitingTrucks.peek() <= weight) {
                    // 다음 트럭을 다리에 올림
                    int enterTruck = waitingTrucks.poll();
                    bridge.offer(enterTruck);
                    currentWeight += enterTruck;
                } else {
                    // 트럭이 올라갈 수 없으면 빈 공간(0)을 추가
                    // 이렇게 하면 다리 위의 트럭들이 한 칸씩 앞으로 이동하는 효과
                    bridge.offer(0);
                }
            }
            // 대기 트럭이 없고 다리 위에만 트럭이 있는 경우
            // bridge가 빌 때까지 반복하면서 시간이 자동으로 증가
        }

        return time;
    }

    /*
     * 문제 풀이 접근 방법:
     *
     * 1. 핵심 아이디어:
     *    - 다리를 큐로 모델링 (길이가 bridge_length인 큐)
     *    - 매 초마다 다리의 맨 앞 트럭이 빠져나가고, 새로운 트럭이 뒤에서 들어옴
     *    - 0을 사용해서 다리 위의 빈 공간을 표현
     *
     * 2. 시뮬레이션 과정:
     *    - 시간을 1초씩 증가시키면서 다음을 반복:
     *      a) 다리의 맨 앞 요소를 제거 (트럭이 다리를 벗어남)
     *      b) 현재 다리 무게 갱신
     *      c) 대기 중인 트럭이 올라갈 수 있으면 올리고, 아니면 0(빈 공간) 추가
     *
     * 3. 예제 추적 (bridge_length=2, weight=10, truck_weights=[7,4,5,6]):
     *    time=0: bridge=[0,0], waiting=[7,4,5,6], currentWeight=0
     *    time=1: bridge=[0,7], waiting=[4,5,6], currentWeight=7
     *    time=2: bridge=[7,0], waiting=[4,5,6], currentWeight=7
     *    time=3: bridge=[0,4], waiting=[5,6], currentWeight=4 (7이 빠짐)
     *    time=4: bridge=[4,5], waiting=[6], currentWeight=9
     *    time=5: bridge=[5,0], waiting=[6], currentWeight=5 (4가 빠짐)
     *    time=6: bridge=[0,6], waiting=[], currentWeight=6 (5가 빠짐)
     *    time=7: bridge=[6,0], waiting=[], currentWeight=6
     *    time=8: bridge=[0,0], waiting=[], currentWeight=0 (6이 빠짐)
     *    -> 답: 8초
     *
     * 4. 시간 복잡도: O(N * bridge_length)
     *    - N: 트럭의 개수
     *    - 각 트럭마다 최대 bridge_length만큼 시간이 소요
     *
     * 5. 공간 복잡도: O(N + bridge_length)
     *    - 대기 큐: O(N)
     *    - 다리 큐: O(bridge_length)
     */


}