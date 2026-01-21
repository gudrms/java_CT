/*
* K번째수
제출 내역
문제 설명
배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.

예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면

array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
2에서 나온 배열의 3번째 숫자는 5입니다.
배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때, commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
array의 길이는 1 이상 100 이하입니다.
array의 각 원소는 1 이상 100 이하입니다.
commands의 길이는 1 이상 50 이하입니다.
commands의 각 원소는 길이가 3입니다.
입출력 예
array	commands	return
[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]
입출력 예 설명
[1, 5, 2, 6, 3, 7, 4]를 2번째부터 5번째까지 자른 후 정렬합니다. [2, 3, 5, 6]의 세 번째 숫자는 5입니다.
[1, 5, 2, 6, 3, 7, 4]를 4번째부터 4번째까지 자른 후 정렬합니다. [6]의 첫 번째 숫자는 6입니다.
[1, 5, 2, 6, 3, 7, 4]를 1번째부터 7번째까지 자릅니다. [1, 2, 3, 4, 5, 6, 7]의 세 번째 숫자는 3입니다.*/

package 문제;

import java.util.Arrays;

/*
 * [문제 설명]
 * 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구합니다.
 *
 * [풀이 순서]
 * 1. commands의 각 원소를 순회하며 [i, j, k] 값을 꺼냅니다.
 * 2. 원본 배열 array에서 i번째부터 j번째까지 복사하여 부분 배열을 만듭니다. (인덱스 주의!)
 * 3. 부분 배열을 오름차순으로 정렬합니다.
 * 4. 정렬된 배열에서 k번째 숫자를 결과 배열에 담습니다.
 */
public class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        // 결과를 담을 배열 선언 (commands의 개수만큼)
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            // commands[i]는 [start, end, k] 형식을 가짐
            int start = commands[i][0]; // 시작 위치 (1번째부터 시작하는 기준)
            int end = commands[i][1];   // 끝 위치
            int k = commands[i][2];     // 찾을 순서

            /*
             * 1. 배열 자르기: Arrays.copyOfRange(원본, 시작인덱스, 끝인덱스)
             * - 문제에서는 1번째부터 세지만, Java 인덱스는 0부터 시작하므로
             * - 시작 인덱스는 (start - 1)이 됩니다.
             * - 끝 인덱스는 포함되지 않으므로 (end)를 그대로 넣으면 됩니다.
             */
            int[] temp = Arrays.copyOfRange(array, start - 1, end);

            // 2. 부분 배열 정렬 (오름차순)
            Arrays.sort(temp);

            // 3. k번째 수 추출 (인덱스로는 k-1)
            answer[i] = temp[k - 1];
        }

        return answer;
    }

    public static void main(String[] args) {
        K번째수 sol = new K번째수();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        int[] result = sol.solution(array, commands);
        System.out.println(Arrays.toString(result)); // [5, 6, 3] 출력
    }

    /*
     * 연습용: 주석만 보고 직접 구현해보세요!
     */
    public int[] solution2(int[] array, int[][] commands) {
        // 결과를 담을 배열 선언 (commands의 개수만큼)

        // commands의 각 원소를 순회

            // commands[i]에서 [start, end, k] 값을 추출




            /*
             * 1. 배열 자르기: Arrays.copyOfRange(원본, 시작인덱스, 끝인덱스)
             * - 문제에서는 1번째부터 세지만, Java 인덱스는 0부터 시작하므로
             * - 시작 인덱스는 (start - 1)이 됩니다.
             * - 끝 인덱스는 포함되지 않으므로 (end)를 그대로 넣으면 됩니다.
             */


            // 2. 부분 배열 정렬 (오름차순)


            // 3. k번째 수 추출 (인덱스로는 k-1)



        // 결과 배열 반환

    }
}


// 1. 입력받은 int 배열을 String 배열로 변환하기
// (이유: 숫자 6, 10을 "610"과 "106"으로 붙여서 비교하기 위해)


// 2. String 배열을 특수 기준에 따라 정렬하기
// (기준: 두 문자열 a, b를 합쳤을 때, [b + a]가 [a + b]보다 크다면 b를 앞으로 보냄)
// (팁: Arrays.sort와 람다식을 활용하면 좋음)


// 3. 만약 정렬된 배열의 첫 번째 원소가 "0"이라면?
// (이유: [0, 0, 0] 같은 경우 "000"이 아닌 "0"을 리턴하기 위한 예외 처리)


// 4. 정렬된 문자열들을 하나로 합치기
// (팁: 성능을 위해 StringBuilder를 사용해서 append로 붙이기)


// 5. 최종 결과 리턴하기