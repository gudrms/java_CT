import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
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