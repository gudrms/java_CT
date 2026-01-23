/*
* 체육복
제출 내역
문제 설명
점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다. 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다. 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

제한사항
전체 학생의 수는 2명 이상 30명 이하입니다.
체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
입출력 예
n	lost	reserve	return
5	[2, 4]	[1, 3, 5]	5
5	[2, 4]	[3]	4
3	[3]	[1]	2
입출력 예 설명
예제 #1
1번 학생이 2번 학생에게 체육복을 빌려주고, 3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있습니다.

예제 #2
3번 학생이 2번 학생이나 4번 학생에게 체육복을 빌려주면 학생 4명이 체육수업을 들을 수 있습니다.*/

package 문제;

import java.util.Arrays;

public class 체육복14 {

    /**
     * @param n       전체 학생 수 (1~n)
     * @param lost    도난당한 학생 번호
     * @param reserve 여벌 있는 학생 번호
     * @return 체육수업을 들을 수 있는 학생의 최댓값
     */
    public int solution(int n, int[] lost, int[] reserve) {
        // 학생별 체육복 상태를 int 배열로 관리
        // 0: 딱 1벌(정상)
        // -1: 도난(0벌)
        // +1: 여벌(2벌)
        int[] clothes = new int[n + 1]; // 1번부터 쓰기 위해 n+1

        // 1) lost 반영: 도난당하면 -1
        for (int l : lost) {
            clothes[l]--;
        }

        // 2) reserve 반영: 여벌 있으면 +1
        for (int r : reserve) {
            clothes[r]++;
        }

        // 3) 빌려주기 (그리디)
        //    i가 체육복이 없으면(clothes[i] == -1),
        //    왼쪽(i-1)이 여벌(clothes[i-1] == 1)인지 먼저 보고,
        //    없으면 오른쪽(i+1)을 봅니다.
        for (int i = 1; i <= n; i++) {
            if (clothes[i] == -1) {
                // 왼쪽 학생에게 빌릴 수 있으면 빌린다
                if (i - 1 >= 1 && clothes[i - 1] == 1) {
                    clothes[i - 1]--;
                    clothes[i]++;
                }
                // 왼쪽이 안 되면 오른쪽 학생에게 빌린다
                else if (i + 1 <= n && clothes[i + 1] == 1) {
                    clothes[i + 1]--;
                    clothes[i]++;
                }
            }
        }

        // 4) 최종 계산: clothes[i]가 -1이면 0벌이므로 수업 불가
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (clothes[i] >= 0) {
                answer++;
            }
        }

        return answer;
    }

    // 로컬 테스트용 main (프로그래머스 제출 시엔 없어도 됨)
    public static void main(String[] args) {
        체육복14 sol = new 체육복14();

        System.out.println(sol.solution(5, new int[]{2, 4}, new int[]{1, 3, 5})); // 5
        System.out.println(sol.solution(5, new int[]{2, 4}, new int[]{3}));       // 4
        System.out.println(sol.solution(3, new int[]{3}, new int[]{1}));          // 2
    }

    package 문제;

    public class 체육복14_Practice {

        public int solution(int n, int[] lost, int[] reserve) {
            // 1) 학생별 체육복 상태 배열 clothes 만들기 (크기 n+1)
            //    - 기본 0(정상 1벌로 생각)
            //    - lost면 clothes[번호]--
            //    - reserve면 clothes[번호]++

            // 2) 1번부터 n번까지 순회하면서
            //    clothes[i] == -1 (체육복 없음) 인 학생을 찾는다

            // 3) 빌리기 규칙(그리디)
            //    - 왼쪽(i-1)이 여벌(== 1)이면 왼쪽에서 빌림
            //    - 아니면 오른쪽(i+1)이 여벌(== 1)이면 오른쪽에서 빌림
            //    - 빌리면: 빌려준 쪽은 1 감소, 빌린 학생은 1 증가

            // 4) 최종 answer 계산
            //    - clothes[i] >= 0 인 학생 수를 센다

            return 0; // TODO: 완성 후 수정
        }
    }
}