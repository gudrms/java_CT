/*
* H-Index
제출 내역
문제 설명
H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.

어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.

어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

제한사항
과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
입출력 예
citations	return
[3, 0, 6, 1, 5]	3
입출력 예 설명
이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.

*/

package 문제;

import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);          // 1) 오름차순 정렬
        int n = citations.length;
        int answer = 0;

        // 2) i번째 논문을 "기준"으로 잡으면
        //    - citations[i] 이상 인용된 논문 수는 (i 포함해서 뒤에 있는 개수) = n - i
        //    - 가능한 h는 "인용수"와 "논문수" 중 작은 값
        for (int i = 0; i < n; i++) {
            int h = Math.min(citations[i], n - i);
            answer = Math.max(answer, h);
        }

        return answer;
    }


    class SolutionPractice {
        public int solution(int[] citations) {
            // 1) citations 오름차순 정렬

            // 2) n = citations.length, answer = 0 준비

            // 3) i를 0부터 n-1까지 반복
            //    - papers = n - i  (i번째부터 끝까지 논문 수)
            //    - h = min(citations[i], papers)
            //    - answer = max(answer, h)

            // 4) answer 반환
            return 0; // TODO: 완성 후 수정
        }
    }

    import java.util.Arrays;

    class Solution {
        // H-Index: 암기용 쉬운 버전
        // 정렬(오름차순) 후 i마다 h = min(citations[i], n-i) 의 최댓값
        public int solution(int[] citations) {
            Arrays.sort(citations);

            int n = citations.length;
            int answer = 0;

            for (int i = 0; i < n; i++) {
                int h = Math.min(citations[i], n - i);
                answer = Math.max(answer, h);
            }

            return answer;
        }
    }

    public class Main {
        public static void main(String[] args) {
            Solution sol = new Solution();

            int[] citations1 = {3, 0, 6, 1, 5};
            System.out.println(sol.solution(citations1)); // 기대: 3

            int[] citations2 = {0, 0, 0};
            System.out.println(sol.solution(citations2)); // 기대: 0

            int[] citations3 = {10, 8, 5, 4, 3};
            System.out.println(sol.solution(citations3)); // 기대: 4
        }
    }
}
