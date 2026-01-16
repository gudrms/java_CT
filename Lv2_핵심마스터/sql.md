# 📊 SQL 기본 정리 (프리테스트 & 기술 면접 대비)

> **준비 시기**: 2.2 일요일 (면접 2일 전)
> **소요 시간**: 4시간
> **목표**: SQL 약해도 괜찮다! 기본 개념만 알면 충분!

---

## 🎯 SQL 준비 전략

### ⚠️ 중요도 분석
- **라이브 코딩 (2시간)**: SQL 안 나옴 ✅
- **프리테스트 (30분)**: DB 파트 일부만 (5-7문제)
- **기술 면접 (1.5시간)**: 기본 개념만 (깊이 X)

**결론**: SQL 완벽히 몰라도 됨! 기본만 알면 충분!

---

## 📚 빠른 SQL 정리 (2.2 일요일 4시간 플랜)

### **오전 2시간**:
1. 프로그래머스 SQL 고득점 Kit (30분)
   - SELECT 문제 2-3개
   - JOIN 문제 2-3개
2. SQL 기본 문법 정리 (30분)
3. 실행 순서 암기 (30분)
4. JOIN 종류 암기 (30분)

### **오후 2시간**:
1. 인덱스 개념 정리 (30분)
2. 정규화 개념 정리 (30분)
3. 트랜잭션 & ACID 정리 (1시간)

---

## 1️⃣ SQL 기본 문법 (1시간)

### SELECT 기본
```sql
-- 기본 조회
SELECT 컬럼명 FROM 테이블명 WHERE 조건;

-- 정렬
SELECT * FROM 테이블 ORDER BY 컬럼 DESC;

-- 조건 결합
SELECT * FROM 직원 WHERE 나이 > 30 AND 부서 = 'IT';
```

### GROUP BY + 집계함수
```sql
SELECT 부서, COUNT(*), AVG(급여)
FROM 직원
GROUP BY 부서
HAVING COUNT(*) > 5;
```

**집계함수**: `COUNT()`, `SUM()`, `AVG()`, `MAX()`, `MIN()`

---

### JOIN (가장 중요! ⭐⭐⭐)

```sql
-- INNER JOIN
SELECT * FROM A
INNER JOIN B ON A.id = B.id;

-- LEFT JOIN
SELECT * FROM A
LEFT JOIN B ON A.id = B.id;

-- RIGHT JOIN
SELECT * FROM A
RIGHT JOIN B ON A.id = B.id;

-- FULL OUTER JOIN
SELECT * FROM A
FULL OUTER JOIN B ON A.id = B.id;
```

---

## 2️⃣ SQL 실행 순서 (30분) ⭐

### 문법 순서 (코드 작성)
```
SELECT → FROM → WHERE → GROUP BY → HAVING → ORDER BY
```

### 실제 실행 순서 (DB 내부)
```
FROM → ON → JOIN → WHERE → GROUP BY → HAVING
→ SELECT → DISTINCT → ORDER BY → LIMIT
```

### 면접 단골 질문

**Q: "WHERE와 HAVING의 차이는?"**

**A**:
- `WHERE`: GROUP BY **전**에 실행 (행 필터링)
- `HAVING`: GROUP BY **후**에 실행 (그룹 필터링)

**예시**:
```sql
-- WHERE: 30살 이상만 필터링 후 그룹화
SELECT 부서, COUNT(*)
FROM 직원
WHERE 나이 >= 30
GROUP BY 부서;

-- HAVING: 그룹화 후 5명 이상인 부서만
SELECT 부서, COUNT(*)
FROM 직원
GROUP BY 부서
HAVING COUNT(*) > 5;
```

---

## 3️⃣ 인덱스 (30분) ⭐⭐

### 개념
- 책의 색인처럼 데이터 빠르게 찾기
- **B-Tree** 구조 사용
- 조회 속도 ↑, 삽입/수정/삭제 속도 ↓

### 장점
- SELECT 쿼리 속도 향상
- WHERE, JOIN 조건 빠르게 처리

### 단점
- 추가 저장 공간 필요
- INSERT/UPDATE/DELETE 느려짐

### 언제 사용?
- WHERE 절에 자주 사용되는 컬럼
- JOIN 조건 컬럼
- ORDER BY 자주 하는 컬럼

### 면접 답변 템플릿
> "인덱스는 B-Tree 구조로 데이터 조회 속도를 향상시키지만, 삽입/수정/삭제 시 인덱스도 재구성해야 하므로 쓰기 성능은 저하됩니다. WHERE 절이나 JOIN 조건에 자주 사용되는 컬럼에 생성하는 것이 효과적입니다."

---

## 4️⃣ 정규화 (30분) ⭐

### 개념
데이터 중복 제거, 이상 현상 방지

### 단계

**1NF (제1정규형)**:
- 모든 값이 원자값 (하나의 값만)

**2NF (제2정규형)**:
- 부분 함수 종속 제거

**3NF (제3정규형)**:
- 이행 함수 종속 제거

### 면접 답변 템플릿
> "정규화는 데이터 중복을 제거하고 이상 현상을 방지하기 위한 과정입니다. 일반적으로 3NF까지 진행하며, 삽입/수정/삭제 이상을 방지할 수 있습니다."

---

## 5️⃣ 트랜잭션 & ACID (1시간) ⭐⭐⭐

### 트랜잭션
하나의 작업 단위

### ACID

**A**tomicity (원자성): 전부 성공 or 전부 실패
**C**onsistency (일관성): 규칙 항상 만족
**I**solation (격리성): 동시 실행해도 독립적
**D**urability (지속성): 완료 후 영구 저장

### 격리 수준 (Isolation Level)

1. **READ UNCOMMITTED**: 커밋 안 된 것도 읽기 (Dirty Read)
2. **READ COMMITTED**: 커밋된 것만 읽기
3. **REPEATABLE READ**: 같은 값 반복 읽기 보장
4. **SERIALIZABLE**: 완전 격리

### 면접 답변 템플릿
> "ACID는 트랜잭션의 안전성을 보장하는 4가지 속성입니다. 원자성은 all-or-nothing, 일관성은 규칙 준수, 격리성은 동시성 제어, 지속성은 영구 저장을 의미합니다."

---

## 6️⃣ JOIN 종류 (30분) ⭐⭐

```
INNER JOIN: 교집합 (양쪽 모두 있는 것만)
LEFT JOIN: 왼쪽 테이블 전부 + 오른쪽 매칭
RIGHT JOIN: 오른쪽 테이블 전부 + 왼쪽 매칭
FULL OUTER JOIN: 합집합 (양쪽 모두)
```

### 면접 질문

**Q: "INNER JOIN과 LEFT JOIN의 차이는?"**

**A**:
> "INNER JOIN은 양쪽 테이블에 모두 존재하는 데이터만 조회하고, LEFT JOIN은 왼쪽 테이블의 모든 데이터를 조회하며 오른쪽 테이블과 매칭되는 것이 있으면 같이 가져옵니다."

---

## 💡 SQL 최소 암기 리스트 (면접 직전)

### 1. SQL 실행 순서
```
FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
```

### 2. ACID
```
Atomicity (원자성)
Consistency (일관성)
Isolation (격리성)
Durability (지속성)
```

### 3. JOIN 종류
```
INNER JOIN: 교집합
LEFT JOIN: 왼쪽 전부
RIGHT JOIN: 오른쪽 전부
FULL OUTER JOIN: 합집합
```

### 4. 인덱스
```
장점: 조회 속도 ↑
단점: 삽입/수정/삭제 속도 ↓, 저장 공간 필요
```

### 5. WHERE vs HAVING
```
WHERE: GROUP BY 전 (행 필터링)
HAVING: GROUP BY 후 (그룹 필터링)
```

---

## 🚨 SQL 때문에 떨어지지 않습니다!

### 합격 가중치
- **라이브 코딩 (알고리즘)**: 40% ← SQL 안 나옴!
- **과제 리뷰**: 30% ← SQL 안 나옴!
- **프리테스트 (CS)**: 15% ← SQL은 일부만
- **기술 면접 (CS)**: 15% ← SQL은 일부만

**SQL 비중**: 전체의 **10-15%** 정도

---

## 📋 우선순위 (중요도 순)

1. **알고리즘 15문제** (최우선!) ⭐⭐⭐
2. **과제 코드 리뷰** (매우 중요!) ⭐⭐⭐
3. **Java, Spring 개념** (중요) ⭐⭐
4. **OS, 네트워크** (중요) ⭐⭐
5. **SQL** (선택) ⭐

---

## ✅ 면접에서 SQL 모르면?

### 솔직하게 말하기

**Q: "SQL 쿼리 최적화 경험이 있나요?"**

**A (솔직하게)**:
> "SQL은 실무에서 기본적인 CRUD와 JOIN 정도만 사용해봤고, 복잡한 쿼리 최적화는 경험이 부족합니다. 하지만 필요하다면 빠르게 학습할 자신 있습니다."

**Q: "트랜잭션 격리 수준을 아시나요?"**

**A (개념만 알면)**:
> "READ COMMITTED, REPEATABLE READ 등의 격리 수준이 있다는 것은 알지만, 실무에서 직접 설정해본 경험은 없습니다."

---

## 🙏 핵심 메시지

### **SQL 약해도 괜찮습니다!** ✅

**이유**:
1. 라이브 코딩에서 안 나옴 (알고리즘만)
2. 프리테스트에서 일부만 (기본 개념만)
3. 기술 면접에서 나와도 기본만 물어봄

**대응 전략**:
- **2.2 일요일 4시간**만 투자
- 위에 정리한 내용만 암기
- 모르면 솔직히 말하기

**지금은 알고리즘에 집중하세요!** 💪
**SQL은 2.2에 4시간만 투자하면 충분합니다!**
