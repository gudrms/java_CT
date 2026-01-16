package 문제;

import java.util.*;

/*
[문제 설명]
운영체제의 역할 중 하나는 컴퓨터 시스템의 자원을 효율적으로 관리하는 것입니다. 이 문제에서는 운영체제가 다음 규칙에 따라 프로세스를 관리할 경우 특정 프로세스가 몇 번째로 실행되는지 알아내면 됩니다.

1. 실행 대기 큐(Queue)에서 대기중인 프로세스 하나를 꺼냅니다.
2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣습니다.
3. 만약 그런 프로세스가 없다면 방금 꺼낸 프로세스를 실행합니다.
  3.1 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료됩니다.

예를 들어 프로세스 4개 [A, B, C, D]가 순서대로 실행 대기 큐에 들어있고, 우선순위가 [2, 1, 3, 2]라면 [C, D, A, B] 순으로 실행하게 됩니다.

현재 실행 대기 큐(Queue)에 있는 프로세스의 중요도가 순서대로 담긴 배열 priorities와, 몇 번째로 실행되는지 알고싶은 프로세스의 위치를 알려주는 location이 매개변수로 주어질 때, 해당 프로세스가 몇 번째로 실행되는지 return 하도록 solution 함수를 작성해주세요.

[제한사항]
- priorities의 길이는 1 이상 100 이하입니다.
- priorities의 원소는 1 이상 9 이하의 정수입니다.
- priorities의 원소는 우선순위를 나타내며 숫자가 클 수록 우선순위가 높습니다.
- location은 0 이상 (대기 큐에 있는 프로세스 수 - 1) 이하의 값을 가집니다.
- priorities의 가장 앞에 있으면 0, 두 번째에 있으면 1 … 과 같이 표현합니다.

[입출력 예]
priorities          location    return
[2, 1, 3, 2]        2           1
[1, 1, 9, 1, 1, 1]  0           5
*/




class Printer5 {
    public int solution(int[] priorities, int location) {
        // 우선순위가 높은 순서대로 확인하기 위해 PriorityQueue 사용 (내림차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 실제 인쇄 대기열을 관리하기 위한 Queue (문서의 초기 인덱스를 저장)
        Queue<Integer> queue = new LinkedList<>();

        // 큐와 우선순위 큐 초기화
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(i);        // 문서의 인덱스(위치) 저장
            pq.offer(priorities[i]); // 중요도 저장
        }

        int answer = 0; // 몇 번째로 인쇄되는지 카운트

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            // 1. 대기 목록(큐)에서 가장 앞에 있는 문서(인덱스)를 꺼냄
            int currentIdx = queue.poll();

            // 현재 문서의 중요도
            int currentPriority = priorities[currentIdx];

            // 2. 현재 문서보다 중요도가 높은 문서가 있는지 확인 (PriorityQueue의 최상단 값과 비교)
            // pq.peek()는 현재 남은 문서들 중 가장 높은 중요도
            if (currentPriority == pq.peek()) {

                // 현재 문서가 가장 높은 중요도를 가짐 -> 인쇄 확정
                pq.poll(); // 우선순위 큐에서도 해당 중요도 제거 (처리됨)
                answer++;  // 인쇄 횟수 증가

                // 우리가 찾는 문서인지 확인 (초기 위치 location과 비교)
                if (currentIdx == location) {
                    return answer;
                }
            } else {
                // 3. 중요도가 더 높은 문서가 뒤에 존재함 -> 다시 큐의 맨 뒤로 보냄
                queue.offer(currentIdx);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        Printer5 solution = new Printer5();
        int result = solution.solution(priorities, location);
        System.out.println("인쇄 순서: " + result);
    }


}

