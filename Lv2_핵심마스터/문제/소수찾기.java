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

        private final Set<Integer> uniqueNumbers = new HashSet<>();

        private boolean isPrime(int num){

            if(num < 2){
                return false;
            }

            for(int i = 2; i<=num.length;i++){
                if(num % i ==0){
                    return false
                }
            }
            return true;
        }

        private generatePermutations(String currentCombination, String remainingDigits){

            if(!currentCombination.isEmpty()){
                uniqueNumbers.add(Integer.parseInt(currentCombination));
            }


            for (int i = 0; i < remainingDigits.length(); i++) {
                String newCombination = currentCombination + remainingDigits.charAt(i);
                String nextRemaining = remainingDigits.substring(0, i) + remainingDigits.substring(i + 1);

                generatePermutations(newCombination, nextRemaining);
            }
        }

        public solution(String numbers) {
            generatePermutations("", numbers);

            int primeCount = 0;

            for (int n : uniqueNumbers){
                if (isPrime(n)) {
                    primeCount++;
                }
            }
            return primeCount;
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