/*
카펫
제출 내역
문제 설명
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

carpet.png

Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
입출력 예
brown	yellow	return
        10	2	[4, 3]
        8	1	[3, 3]
        24	24	[8, 6]
출처

※ 공지 - 2020년 2월 3일 테스트케이스가 추가되었습니다.
※ 공지 - 2020년 5월 11일 웹접근성을 고려하여 빨간색을 노란색으로 수정하였습니다.

[문제 핵심 이해]
1. 카펫 구조
   - 테두리 1줄: 갈색 (brown)
   - 중앙 영역: 노란색 (yellow)

2. 수학적 관계식
   - 전체 카펫 크기: width × height = brown + yellow
   - 노란색 영역: (width-2) × (height-2) = yellow
     (테두리 1줄씩 제외하면 중앙 노란색 영역)
   - 갈색 영역: width × height - (width-2) × (height-2) = brown

3. 제약 조건
   - width >= height (가로가 세로보다 크거나 같음)
   - 최소 크기: 3×3 (테두리 1줄 + 중앙 최소 1칸)

[풀이 전략]
완전탐색 방식으로 가능한 모든 (width, height) 조합을 확인
- 전체 넓이 = brown + yellow 부터 시작
- height는 3부터 시작 (최소 크기)
- width는 height 이상이어야 함
- (width-2) × (height-2) = yellow 를 만족하는 조합 찾기

[시간 복잡도]
O(√n) - n은 brown + yellow
height를 3부터 √(brown+yellow)까지만 확인하면 됨
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        // 전체 카펫의 넓이 = 갈색 + 노란색
        int total = brown + yellow;

        // height를 3부터 시작 (최소 카펫 크기)
        // height가 total의 제곱근까지만 확인하면 됨
        // 왜냐하면 width >= height 조건 때문

        for (int height = 3; height <= Math.sqrt(total); height++) {
            // total이 height로 나누어떨어지는 경우만 유효한 사각형
            if (total % height == 0) {
                int width = total / height;

                // width >= height 조건 확인 (문제 조건)
                if (width >= height) {
                    // 노란색 영역 계산: 테두리 1줄을 제외한 중앙 영역
                    // (width-2) × (height-2) = yellow 인지 확인
                    int yellowArea = (width - 2) * (height - 2);

                    if (yellowArea == yellow) {
                        // 조건을 모두 만족하면 [가로, 세로] 반환
                        return new int[]{width, height};
                    }
                }
            }
        }

        // 문제 조건상 항상 답이 존재하므로 여기까지 오지 않음
        return new int[]{};
    }
}

/*
[예제 검증]

예제 1: brown=10, yellow=2
- total = 10 + 2 = 12
- height=3일 때: width = 12/3 = 4
  → (4-2) × (3-2) = 2 × 1 = 2 ✓ (yellow와 일치)
  → 답: [4, 3]

예제 2: brown=8, yellow=1
- total = 8 + 1 = 9
- height=3일 때: width = 9/3 = 3
  → (3-2) × (3-2) = 1 × 1 = 1 ✓ (yellow와 일치)
  → 답: [3, 3]

예제 3: brown=24, yellow=24
- total = 24 + 24 = 48
- height=3일 때: width = 16, (16-2) × (3-2) = 14 × 1 = 14 ✗
- height=4일 때: width = 12, (12-2) × (4-2) = 10 × 2 = 20 ✗
- height=6일 때: width = 8, (8-2) × (6-2) = 6 × 4 = 24 ✓
  → 답: [8, 6]

[핵심 포인트]
1. 완전탐색: 가능한 모든 height 값을 순회
2. 나눗셈 조건: total % height == 0 (정수 크기만 가능)
3. 기하학적 관계: (width-2) × (height-2) = yellow
4. 최적화: height <= √total 까지만 확인 (width >= height 조건)
5. 테두리 구조: 1줄 테두리를 고려한 수식 활용

[알고리즘 분류]
- 완전탐색 (Brute Force)
- 수학 (약수, 기하학)
*/

/*
================================================================================
주석만 보고 코드 작성하기 - 연습용 템플릿
================================================================================

[문제 이해]
- 카펫: 갈색(테두리 1줄) + 노란색(중앙)
- 입력: brown(갈색 개수), yellow(노란색 개수)
- 출력: [가로, 세로] (가로 >= 세로)

[핵심 수식]
1. width × height = brown + yellow  (전체 넓이)
2. (width-2) × (height-2) = yellow  (중앙 영역)
3. width >= height  (제약 조건)

[알고리즘 단계]
1. 전체 넓이(total) 계산
2. height를 3부터 √total까지 반복
3. total이 height로 나누어떨어지면 width 계산
4. width >= height 확인
5. (width-2) × (height-2) == yellow 확인
6. 조건 만족 시 [width, height] 반환

[구현 힌트]
- for문 범위: height = 3 ~ √total
- 조건1: total % height == 0  (정수 크기)
- 조건2: width >= height
- 조건3: (width-2) * (height-2) == yellow
- 반환: new int[]{width, height}

[연습 코드 작성 공간]
*/

class SolutionPractice {
    public int[] solution(int brown, int yellow) {
        // TODO: 여기에 코드를 작성해보세요

        // 1. 전체 넓이 계산


        // 2. height를 3부터 √total까지 반복

            // 3. total이 height로 나누어떨어지는지 확인

                // 4. width 계산


                // 5. width >= height 확인

                    // 6. 중앙 노란색 영역 계산


                    // 7. yellow와 일치하는지 확인

                        // 8. 조건 만족 시 반환






        // 9. 답이 없을 경우 (문제상 발생하지 않음)
        return new int[]{};
    }
}

/*
[자가 테스트]
brown=10, yellow=2 → 예상 결과: [4, 3]
brown=8, yellow=1 → 예상 결과: [3, 3]
brown=24, yellow=24 → 예상 결과: [8, 6]
*/
