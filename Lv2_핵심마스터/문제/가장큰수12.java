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

import java.util.*;

/*
 [문제 이해]
 - 0 또는 양의 정수가 담긴 배열을 재배치하여 만들 수 있는 가장 큰 수를 찾습니다.
 - 숫자를 이어 붙여야 하므로 문자열 비교 로직이 핵심입니다.
 - 결과는 문자열로 반환합니다.

 [풀이 전략]
 1. 숫자 배열을 문자열 배열로 변환합니다.
 2. 두 수를 합쳤을 때 더 큰 숫자가 되는 순서로 정렬합니다. (예: "6"과 "10" -> "610" vs "106")
 3. 모든 숫자가 0인 특수 상황을 처리합니다. ("000" -> "0")
*/

class SolutionPractice {
  public String solution(int[] numbers) {
    // 1. 숫자를 문자열로 변환하여 담을 String 배열을 생성하고 값을 채웁니다.
    //    (힌트: String.valueOf() 사용)
    String[] strNumbers = new String[numbers.length];

    for (int i = 0; i < numbers.length; i++) {
      strNumbers[i] = String.valueOf(numbers[i]);
    }

    // 2. 정렬 로직을 구현합니다. (Arrays.sort 사용)
    //    커스텀 정렬 기준: 두 문자열 a, b를 합쳤을 때 (b+a)와 (a+b) 중 큰 쪽이 앞으로 오도록!
    //    (힌트: (o1, o2) -> (o2 + o1).compareTo(o1 + o2))
    Arrays.sort(strNumbers, (a,b) -> (b+a).compareTo(a+b));

    // 3. 정렬 후 첫 번째 숫자가 "0"인지 확인합니다.
    //    가장 큰 숫자가 "0"이라면 뒤의 숫자들도 모두 "0"일 것이므로 "0"을 반환합니다.
    //    (예외 처리: [0, 0, 0] -> "0")

    if(strNumbers[0].equals("0")){
      return "0";
    }
    // 4. 정렬된 문자열들을 순서대로 하나로 합칩니다.
    //    (힌트: 효율성을 위해 StringBuilder를 사용하세요.)
    StringBuilder answer = new StringBuilder();
    for(String str : strNumbers){
      answer.append(str);
    }

    // 5. 최종 결과 문자열을 반환합니다.
    return answer.toString(); // 여기에 결과 변수를 넣어주세요.
  }
}

/*
 [자가 테스트]
 numbers: [6, 10, 2] -> 예상 결과: "6210"
 numbers: [3, 30, 34, 5, 9] -> 예상 결과: "9534330"
 numbers: [0, 0, 0] -> 예상 결과: "0"

 [핵심 포인트]
 - 정렬 기준을 어떻게 잡느냐가 이 문제의 90%입니다.
 - "3", "30"이 있을 때 "330"이 "303"보다 크므로 "3"이 앞에 와야 합니다.
 - 큰 데이터를 다룰 때는 String 연산(+)보다 StringBuilder가 훨씬 빠릅니다.
*/