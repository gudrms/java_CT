package 문제;

import java.util.ArrayList;
import java.util.List;

/*
모의고사
문제 설명
수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다.
수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한 조건
- 시험은 최대 10,000 문제로 구성되어있습니다.
- 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
- 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
*/

public class 모의고사7 {

    /**
     * 모의고사 문제를 해결하는 솔루션 클래스입니다.
     * 문제의 요구사항에 따라 solution 메소드를 포함합니다.
     */
    static class Solution {
        /**
         * @param answers 1번 문제부터 마지막 문제까지의 정답이 순서대로 담긴 배열
         * @return 가장 많은 문제를 맞힌 사람의 번호를 오름차순으로 담은 배열
         */
        public int[] solution(int[] answers) {
            // --- 문제 해결 로직 ---

            // 1. 수포자들의 찍기 패턴을 2차원 배열로 정의합니다.
            //    - supojaPatterns[0]: 1번 수포자의 패턴
            //    - supojaPatterns[1]: 2번 수포자의 패턴
            //    - supojaPatterns[2]: 3번 수포자의 패턴
            int[][] supojaPatterns = {
                    {1, 2, 3, 4, 5},
                    {2, 1, 2, 3, 2, 4, 2, 5},
                    {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
            };

            // 2. 각 수포자의 점수를 저장할 배열을 선언합니다.
            //    - scores[0]: 1번 수포자의 점수
            //    - scores[1]: 2번 수포자의 점수
            //    - scores[2]: 3번 수포자의 점수
            int[] scores = new int[3];

            // 3. 정답 배열(answers)을 처음부터 끝까지 순회하며 각 수포자의 답과 비교하여 채점합니다.
            for (int i = 0; i < answers.length; i++) {
                // 각 수포자에 대해 반복합니다.
                for (int j = 0; j < supojaPatterns.length; j++) {
                    // i % pattern.length: 나머지 연산을 사용하여 패턴이 순환되도록 인덱스를 계산합니다.
                    // 예를 들어, 문제 번호 i가 5이고 1번 수포자 패턴 길이가 5일 때,
                    // 5 % 5 = 0 이므로 패턴의 0번째 인덱스인 '1'과 비교하게 됩니다.
                    if (answers[i] == supojaPatterns[j][i % supojaPatterns[j].length]) {
                        scores[j]++; // 정답이 맞으면 해당 수포자의 점수를 1 증가시킵니다.
                    }
                }
            }

            // 4. 채점이 끝난 후, 세 수포자의 점수 중 가장 높은 점수(최고점)를 찾습니다.
            int maxScore = Math.max(scores[0], Math.max(scores[1], scores[2]));

            // 5. 최고점과 동일한 점수를 얻은 수포자들의 번호를 리스트에 담습니다.
            //    리스트를 사용하는 이유: 가장 많이 맞힌 사람이 여러 명일 수 있기 때문입니다.
            List<Integer> winners = new ArrayList<>();
            for (int i = 0; i < scores.length; i++) {
                if (scores[i] == maxScore) {
                    // 수포자 번호는 1, 2, 3이므로 배열 인덱스(i)에 1을 더해줍니다.
                    winners.add(i + 1);
                }
            }

            // 6. 최종적으로, 최고 득점자 리스트를 문제에서 요구하는 int[] 배열 형태로 변환하여 반환합니다.
            //    stream()을 사용하여 리스트를 스트림으로 변환하고,
            //    mapToInt()로 Integer 스트림을 IntStream으로 변환한 뒤,
            //    toArray()로 배열로 만듭니다.
            return winners.stream().mapToInt(i->i).toArray();
        }
    }

    public static void main(String[] args) {
        // 솔루션 객체 생성
        Solution solution = new Solution();

        System.out.println("### 모의고사 문제 테스트 ###");

        // 테스트 케이스 1
        int[] answers1 = {1, 2, 3, 4, 5};
        int[] result1 = solution.solution(answers1);
        System.out.println("입력 1: " + java.util.Arrays.toString(answers1));
        System.out.println("결과 1: " + java.util.Arrays.toString(result1)); // 예상 출력: [1]
        System.out.println("--------------------");

        // 테스트 케이스 2
        int[] answers2 = {1, 3, 2, 4, 2};
        int[] result2 = solution.solution(answers2);
        System.out.println("입력 2: " + java.util.Arrays.toString(answers2));
        System.out.println("결과 2: " + java.util.Arrays.toString(result2)); // 예상 출력: [1, 2, 3]
        System.out.println("--------------------");
    }
}
