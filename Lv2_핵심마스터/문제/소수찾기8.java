package 문제;

import java.util.HashSet;
import java.util.Set;

/*
* 소수 찾기
문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항
- numbers는 길이 1 이상 7 이하인 문자열입니다.
- numbers는 0~9까지 숫자만으로 이루어져 있습니다.
- "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
* */
public class 소수찾기8 {

    static class Solution {
        // 1. 모든 순열의 결과를 중복 없이 저장하기 위한 Set
        //    String이 아닌 Integer로 저장하여 "011"과 "11"을 같은 값으로 처리
        private final Set<Integer> uniqueNumbers = new HashSet<>();


        /**
         * 주어진 숫자 조각으로 만들 수 있는 소수의 개수를 반환합니다.
         * @param numbers 숫자 조각이 담긴 문자열 (예: "17")
         * @return 만들 수 있는 소수의 개수
         */

        public int solution(String numbers) {
            // 2. DFS(깊이 우선 탐색)를 이용해 만들 수 있는 모든 숫자 조합을 찾는다.
            //    초기 호출: 현재 조합은 비어있고(""), 남은 숫자 조각은 numbers 전체
            generatePermutations("", numbers);

            // 3. 찾은 숫자 조합들 중에서 소수(prime number)의 개수를 센다.
            int primeCount = 0;
            for (int num : uniqueNumbers) {
                if (isPrime(num)) {
                    primeCount++;
                }
            }

            return primeCount;
        }

        /**
         * 재귀를 이용해 가능한 모든 숫자 조합(순열)을 생성하는 함수
         * @param currentCombination 현재까지 만들어진 숫자 조합 (문자열)
         * @param remainingDigits  아직 사용하지 않은 숫자 조각들
         */
        private void generatePermutations(String currentCombination, String remainingDigits) {
            // 4. 현재 조합이 비어있지 않다면, Set에 추가한다.
            if (!currentCombination.isEmpty()) {
                uniqueNumbers.add(Integer.parseInt(currentCombination));
            }

            // 5. 남은 숫자 조각들을 하나씩 가져와 현재 조합에 붙여 새로운 조합을 만든다.
            for (int i = 0; i < remainingDigits.length(); i++) {
                // 새로운 조합: currentCombination + i번째 숫자
                String newCombination = currentCombination + remainingDigits.charAt(i);

                // 다음 재귀 호출에 넘길 남은 숫자들: i번째 숫자를 제외한 나머지
                String nextRemaining = remainingDigits.substring(0, i) + remainingDigits.substring(i + 1);

                // 재귀 호출
                generatePermutations(newCombination, nextRemaining);
            }
        }

        /**
         * 주어진 숫자가 소수인지 판별하는 함수
         * @param n 판별할 숫자
         * @return 소수이면 true, 아니면 false
         */
        private boolean isPrime(int n) {
            // 6. 0과 1은 소수가 아니므로 false 반환
            if (n < 2) {
                return false;
            }

            // 7. 소수 판별 최적화: 2부터 해당 숫자의 제곱근까지만 확인한다.
            //    어떤 수 N이 소수가 아니라면, N = a * b 형태로 표현되고 a와 b 중 적어도 하나는 sqrt(N)보다 작거나 같기 때문.
            for (int i = 2; i <= Math.sqrt(n); i++) {
                // 나누어 떨어지는 수가 있다면 소수가 아님
                if (n % i == 0) {
                    return false;
                }
            }

            // 8. 위 반복문을 통과하면 소수임
            return true;
        }

//        소수공식
        private boolean isPrime2(int n){
            if(n < 2){
                return false;

            }
            for (int i=2; i<= Math.sqrt(n);i++){
                if(n%i == 0) {
                    return false;
                }
            }
            return true;
        }

        private boolean isPrime3(int n){
            if(n < 2){
                return false;
            }

            for(int i=2; i<=Math.sqrt(n);i++){
                if(n % i == 0){
                    return false;
                }
            }
            return true;
        }


        private boolean isPrime4(int n){
            if(n < 2){
                return false;
            }

            for(int i =2; i<=Math.sqrt(n);i++){
                if(n%2 ==0){
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        // 솔루션 객체 생성
        Solution solution = new Solution();

        System.out.println("### 소수 찾기 문제 테스트 ###");

        // 테스트 케이스 1
        String numbers1 = "17";
        int result1 = solution.solution(numbers1);
        System.out.println("입력 1: \"" + numbers1 + "\"");
        System.out.println("결과 1: " + result1); // 예상 출력: 3 (7, 17, 71)
        System.out.println("--------------------");

        // 객체를 다시 생성하여 Set을 초기화해야 함
        solution = new Solution();

        // 테스트 케이스 2
        String numbers2 = "011";
        int result2 = solution.solution(numbers2);
        System.out.println("입력 2: \"" + numbers2 + "\"");
        System.out.println("결과 2: " + result2); // 예상 출력: 2 (11, 101)
        System.out.println("--------------------");
    }
}