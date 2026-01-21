/*
* 가장 큰 수
제출 내역
문제 설명
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항
numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
입출력 예
numbers	return
[6, 10, 2]	"6210"
[3, 30, 34, 5, 9]	"9534330"*/

import java.util.*;

class Solution {
  public String solution(int[] numbers) {
    // 1. int 배열을 String 배열로 변환합니다. (문자열로 이어 붙여 비교하기 위함)
    String[] strNumbers = new String[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      strNumbers[i] = String.valueOf(numbers[i]);
    }

    // 2. 정렬 기준 설정 (커스텀 정렬)
    // 두 문자열 a와 b를 합쳤을 때, (b + a)가 더 크면 b가 앞으로 오게 정렬합니다.
    Arrays.sort(strNumbers, (a, b) -> (b + a).compareTo(a + b));

    // 3. 예외 처리: 모든 숫자가 0인 경우 (예: [0, 0, 0])
    // 정렬 후 첫 번째 숫자가 "0"이면 전체가 0이므로 "0"만 리턴합니다.
    if (strNumbers[0].equals("0")) {
      return "0";
    }

    // 4. 정렬된 문자열을 하나로 합칩니다.
    StringBuilder answer = new StringBuilder();
    for (String str : strNumbers) {
      answer.append(str);
    }

    return answer.toString();
  }
}