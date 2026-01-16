# 📚 NHN PAYCO 면접 대비 CS 요약본

> **면접 일정**: 2026년 2월 4일 (수) 14:00 ~ 18:00
> **프리테스트**: 16:00-16:30 (30분, CS 객관식 20-30문제)
> **기술 면접**: 16:30-18:00 (1.5시간, 라이브 코딩 리뷰 + CS 질문)

---

## 1️⃣ 운영체제 (Operating System)

### 프로세스 vs 스레드

| 구분 | 프로세스 (Process) | 스레드 (Thread) |
| :--- | :--- | :--- |
| **정의** | 실행 중인 프로그램 (독립적 단위) | 프로세스 내 실행 단위 |
| **메모리** | Code, Data, Stack, Heap 독립적 | Stack만 독립, 나머지는 공유 |
| **통신** | IPC 필요 (비용 높음) | 공유 메모리로 쉬움 (비용 낮음) |
| **컨텍스트 스위칭** | 비용 큼 | 비용 작음 |

### 프로세스 상태
- **생성(New)**: 프로세스 생성 중
- **준비(Ready)**: CPU 할당을 기다리는 상태
- **실행(Running)**: CPU를 점유하여 명령어를 실행 중인 상태
- **대기(Waiting)**: I/O 작업 등 특정 이벤트 발생을 기다리는 상태
- **종료(Terminated)**: 실행 완료 후 자원 반납

### CPU 스케줄링
- **FCFS (First Come First Served)**: 선입선출. 먼저 온 순서대로 처리 (Convoy Effect 발생 가능)
- **SJF (Shortest Job First)**: 실행 시간 짧은 순서 (Starvation 발생 가능)
- **Round Robin**: 동일 시간 할당량(Time Quantum)만큼 순환 실행
- **Priority Scheduling**: 우선순위 기반 (Aging 기법으로 Starvation 방지)
- **Multi-Level Queue**: 여러 개의 큐를 사용하며 큐마다 다른 스케줄링 적용

### 동기화 (Synchronization)
- **Race Condition (경쟁 상태)**: 여러 프로세스가 공유 자원에 동시 접근하여 결과값이 달라지는 현상
- **Critical Section (임계 영역)**: 공유 자원에 접근하는 코드 영역
- **해결 방법**:
  - **Mutex (상호배제)**: Lock을 가진 하나만 접근 허용
  - **Semaphore**: S개의 자원에 대해 N개까지 접근 허용 (Counting / Binary)
  - **Monitor**: 프로그래밍 언어 수준에서 제공하는 고수준 동기화 도구

### 데드락 (Deadlock)
- **발생 조건 (4가지 모두 만족 시)**:
  1. **상호배제 (Mutual Exclusion)**: 한 번에 한 프로세스만 자원 사용
  2. **점유대기 (Hold and Wait)**: 자원을 가진 채 다른 자원을 기다림
  3. **비선점 (No Preemption)**: 다른 프로세스의 자원을 강제로 뺏을 수 없음
  4. **순환대기 (Circular Wait)**: 대기 프로세스들이 순환 형태로 자원 요구
- **해결 방법**:
  - **예방 (Prevention)**: 조건 중 하나를 제거 (자원 낭비 심함)
  - **회피 (Avoidance)**: Banker's Algorithm (안전 상태 유지)
  - **탐지 (Detection)**: 주기적으로 체크
  - **회복 (Recovery)**: 프로세스 종료 또는 자원 선점

### 메모리 관리
- **Paging (페이징)**: 물리 메모리를 고정 크기(Page)로 분할. **외부 단편화 해결, 내부 단편화 발생 가능.**
- **Segmentation (세그멘테이션)**: 논리적 단위(Segment)로 분할. **내부 단편화 해결, 외부 단편화 발생 가능.**
- **가상 메모리 (Virtual Memory)**: 실제 메모리보다 큰 프로그램을 실행하기 위한 기술
  - **Page Fault**: 필요한 페이지가 메모리에 없을 때 발생
  - **교체 알고리즘**: LRU (최근 미사용), FIFO, LFU (빈도 기반)

---

## 2️⃣ 네트워크 (Network)

### OSI 7계층
1. **Physical**: 전기 신호, 케이블 (Hub)
2. **Data Link**: MAC 주소, 에러 검출 (Switch)
3. **Network**: IP 주소, 경로 결정 (Router)
4. **Transport**: 신뢰성 있는 전송 (TCP/UDP, Port)
5. **Session**: 통신 세션 유지
6. **Presentation**: 암호화, 압축, 인코딩
7. **Application**: 사용자 서비스 (HTTP, FTP, DNS)

### TCP vs UDP

| 구분 | TCP | UDP |
| :--- | :--- | :--- |
| **연결 방식** | 연결 지향 (3-way Handshake) | 비연결 지향 |
| **신뢰성** | 높음 (순서 보장, 흐름/혼잡 제어) | 낮음 (데이터 손실 가능) |
| **속도** | 느림 (헤더 큼) | 빠름 (헤더 작음) |
| **사용 사례** | 웹(HTTP), 이메일, 파일 전송 | 스트리밍, DNS, 게임 |

### TCP Handshake
- **3-Way Handshake (연결)**: SYN → SYN+ACK → ACK
- **4-Way Handshake (종료)**: FIN → ACK → FIN → ACK (TIME_WAIT 상태로 마지막 패킷 유실 대비)

### HTTP vs HTTPS
- **HTTP (Port 80)**: 평문 전송, 보안 취약
- **HTTPS (Port 443)**: SSL/TLS 암호화 추가, CA 인증서 활용

### HTTP 메서드 & 상태 코드
- **주요 메서드**:
  - **GET**: 조회 (Idempotent, Safe)
  - **POST**: 생성 (Non-idempotent)
  - **PUT**: 전체 수정 (Idempotent)
  - **PATCH**: 부분 수정
  - **DELETE**: 삭제 (Idempotent)
- **상태 코드**:
  - **2xx (Success)**: 200(OK), 201(Created)
  - **3xx (Redirection)**: 301(영구 이동), 302(임시 이동)
  - **4xx (Client Error)**: 400(잘못된 요청), 401(인증 실패), 403(권한 없음), 404(찾을 수 없음)
  - **5xx (Server Error)**: 500(서버 내부 오류), 502(Bad Gateway), 503(서비스 점검 중)

### 쿠키 vs 세션 vs 토큰 (JWT)
- **쿠키**: 클라이언트 저장, 4KB 제한, 보안 취약
- **세션**: 서버 저장, Session ID만 쿠키로 전달, 서버 부하 발생
- **JWT (토큰)**: 클라이언트 저장 (Stateless), 확장성 우수, 토큰 탈취 시 위험

---

## 3️⃣ 데이터베이스 (Database)

### 인덱스 (Index)
- **개념**: 검색 속도 향상을 위한 자료구조 (B-Tree 주로 사용)
- **특징**: `SELECT`는 빠르지만, `INSERT/UPDATE/DELETE`는 추가 작업 필요로 느려짐
- **설계**: 카디널리티(중복도)가 높은 컬럼, WHERE/JOIN/ORDER BY가 빈번한 컬럼에 생성

### 트랜잭션 ACID
1. **Atomicity (원자성)**: 모두 반영되거나, 전혀 반영되지 않아야 함
2. **Consistency (일관성)**: 완료 후 DB 상태가 일관되어야 함
3. **Isolation (격리성)**: 실행 중인 트랜잭션 간 간섭 불가
4. **Durability (지속성)**: 결과가 영구적으로 보존되어야 함

### 격리 수준 (Isolation Level)
1. **Read Uncommitted**: 커밋 전 데이터 읽기 가능 (Dirty Read)
2. **Read Committed**: 커밋된 것만 읽기 가능 (Non-Repeatable Read 발생 가능)
3. **Repeatable Read**: 한 트랜잭션 내 같은 쿼리 결과 보장 (Phantom Read 발생 가능)
4. **Serializable**: 완벽한 격리, 성능 매우 낮음

### 정규화 (Normalization)
- **목적**: 데이터 중복 제거 및 이상 현상(삽입/삭제/수정 이상) 방지
- **단계**: 1NF(원자성) → 2NF(부분함수 종속 제거) → 3NF(이행함수 종속 제거) → BCNF(결정자가 후보키)

---

## 4️⃣ 자료구조 (Data Structure)

### 시간복잡도 (Big-O)
- **O(1)**: 배열 인덱스 접근, 해시 맵 검색
- **O(log N)**: 이진 탐색
- **O(N)**: 순차 탐색
- **O(N log N)**: 병합/힙/퀵(평균) 정렬
- **O(N²)**: 버블/선택 정렬

### Array vs LinkedList
- **Array**: 연속 메모리, 인덱스 접근 `O(1)`, 삽입/삭제 `O(N)`, 크기 고정
- **LinkedList**: 노드 연결, 인덱스 접근 `O(N)`, 삽입/삭제 `O(1)` (위치 알 때), 크기 가변

### Hash Table
- **구조**: Key → Hash Function → Index → Value (평균 `O(1)`)
- **충돌 해결**:
  - **Chaining**: 연결 리스트로 데이터 연결
  - **Open Addressing**: 빈 공간 탐색 (Linear Probing 등)

### 트리 & 그래프
- **BST (이진 탐색 트리)**: 왼쪽 < 부모 < 오른쪽 (탐색 평균 `O(log N)`)
- **Heap**: 우선순위 큐 구현용 (Max/Min Heap)
- **DFS (깊이 우선)**: Stack 또는 재귀 사용
- **BFS (너비 우선)**: Queue 사용

---

## 5️⃣ 빈출 면접 질문 Q&A

**Q: 프로세스와 스레드의 가장 큰 차이점은?**
A: 프로세스는 독립된 자원을 할당받아 실행되지만, 스레드는 프로세스 내의 Code, Data, Heap 영역을 공유하여 통신 비용이 훨씬 적습니다.

**Q: TCP 3-way handshake를 사용하는 이유는?**
A: 양측 모두 데이터를 전송할 준비가 되었음을 보장하고, 시퀀스 번호를 동기화하여 신뢰성 있는 연결을 설정하기 위함입니다.

**Q: 인덱스를 무분별하게 생성하면 안 되는 이유는?**
A: 인덱스 생성을 위한 추가 저장 공간이 필요하며, 데이터의 삽입/수정/삭제 시마다 인덱스도 재구성해야 하므로 성능 저하가 발생할 수 있기 때문입니다.


✅ 1교시: 라이브 코딩 (전략: 구현력 + 클린코드)
[마인드셋]

[ ] 문제를 빨리 푸는 것보다 **'읽기 좋은 코드'**와 **'예외 처리'**가 더 중요함.

[ ] 막히면 혼자 끙끙대지 말고 주석으로 **나의 논리(의도)**를 적어둘 것.

[Java 구현 필수 점검]

[ ] 입출력: Scanner 대신 BufferedReader, StringTokenizer 사용법 숙지 (속도).

[ ] 변수명: a, b, temp 절대 금지 → userList, idx, result 등 의미 있게.

[ ] 메서드 분리: main에 다 넣지 말고 기능별로 함수 쪼개기 (가장 큰 가산점).

[ ] Collection 선택:

순서 중요, 중복 허용: ArrayList

중복 제거 필수: HashSet

키-값 매핑: HashMap

선입선출: Queue / Deque

[예상 문제 유형]

[ ] 문자열 처리 (String parsing)

[ ] 구현 (Simulation)

[ ] 자료구조 활용 (Map, Set 적재적소 사용)

✅ 2교시: 필기 테스트 (전략: CS 속성 암기)
[Java 기초]

[ ] JVM 메모리 구조: Method(Static) / Stack(지역변수) / Heap(객체) 차이
  - **Method**: 클래스 정보, static 변수 (공유)
  - **Stack**: 메서드 호출 프레임, 지역변수 (스레드별 독립)
  - **Heap**: `new` 객체 생성 (공유, GC 대상)

[ ] GC (Garbage Collection): 동작 원리 및 시점 (Minor vs Major)
  - **동작**: Reachability 확인 후 Unreachable 객체 삭제
  - **Minor GC**: Young 영역 꽉 참 (빠름)
  - **Major GC**: Old 영역 꽉 참 (느림, Stop-The-World)

[ ] String: String(불변) vs StringBuilder(가변, 단일) vs StringBuffer(가변, 멀티스레드)
  - **String**: 불변, 읽기 많을 때 유리
  - **StringBuilder**: 가변, 동기화 X, 단일 스레드 성능 우수
  - **StringBuffer**: 가변, 동기화 O, 멀티 스레드 안전

[ ] OOP: Overloading vs Overriding / Interface vs Abstract Class
  - **Overloading**: 메서드 이름 같음, 매개변수 다름 (확장)
  - **Overriding**: 상위 메서드 재정의 (다형성)
  - **Interface**: `implements`, 다중 구현, "기능" 정의
  - **Abstract Class**: `extends`, 단일 상속, "본질/공통" 정의

[ ] Collection: HashMap 동작 원리(hashCode, equals), ArrayList vs LinkedList
  - **HashMap**: `hashCode`로 버킷 찾고, 충돌 시 LinkedList/Tree로 체이닝. `equals`로 값 비교.
  - **ArrayList**: 배열 기반, 조회 빠름 `O(1)`, 삽입/삭제 느림
  - **LinkedList**: 노드 기반, 조회 느림 `O(N)`, 삽입/삭제 빠름

[CS 공통]

[ ] DB: Index(장단점, 동작원리), Transaction(ACID), 정규화
  - **Index**: B-Tree. 검색 빠름 vs 쓰기 느림/공간 차지
  - **ACID**: 원자성, 일관성, 격리성, 지속성
  - **정규화**: 중복 제거, 이상 현상 방지

[ ] Network: TCP 3-way Handshake, HTTP Method(GET vs POST), Status Code(200, 404, 500)
  - **3-way**: SYN -> SYN+ACK -> ACK (신뢰성 연결)
  - **GET vs POST**: 조회(멱등) vs 생성(Body 사용)
  - **Code**: 200(성공), 404(없음), 500(서버 에러)

[ ] OS: Process vs Thread 차이, Context Switching, Deadlock(교착상태) 발생 조건
  - **Process vs Thread**: 자원 독립 vs 자원 공유(Stack 제외)
  - **Context Switching**: CPU 작업 전환 (오버헤드)
  - **Deadlock**: 상호배제, 점유대기, 비선점, 순환대기

✅ 3교시: 기술 면접 (전략: 논리 방어 + Why)
[라이브 코딩 리뷰 대비]

[ ] "왜 이 자료구조(ArrayList 등)를 선택했나요?" (조회 성능 vs 삽입 삭제 성능)
  - 답변 예: "인덱스 접근이 빈번하여 조회 성능이 O(1)인 ArrayList가 적합했습니다."

[ ] "이 부분의 시간 복잡도는 어떻게 되나요?"
  - 답변 예: "이중 루프이므로 O(N^2)입니다. N이 작아 허용 범위라 판단했습니다."

[ ] "입력값이 null이거나 엄청 클 때는 어떻게 처리하실 건가요?"
  - 답변 예: "Validation 로직으로 null 체크를 하고, 큰 수는 BigInteger 등을 고려하겠습니다."

[이력서 & 기술 꼬리 질문]

[ ] Spring: DI/IoC란? / Bean 생명주기 / @Transactional 동작 원리(AOP)
  - **DI/IoC**: 객체 관리를 프레임워크에 위임(IoC), 의존성 주입(DI)으로 결합도 낮춤.
  - **Bean Lifecycle**: 생성 -> 의존성주입 -> 초기화 -> 사용 -> 소멸.
  - **@Transactional**: AOP 프록시 패턴. try-catch로 커밋/롤백 관리.

[ ] Java: "Java의 특징이 뭔가요?" (객체지향, 플랫폼 독립성 등)
  - 답변: "JVM으로 OS 독립적 실행이 가능하며, 객체지향적이고 GC가 메모리를 관리해줍니다."

[ ] DB: "N+1 문제가 무엇이고 어떻게 해결했나요?" (Fetch Join 등)
  - **N+1**: 1번 조회 후 연관 데이터 N번 추가 조회.
  - **해결**: Fetch Join, EntityGraph, BatchSize 설정.

[ ] 상황 질문: "트래픽이 갑자기 몰리면 서버 구조를 어떻게 바꿀 것인가?"
  - 답변: "Scale-out으로 서버 증설, Redis 캐싱, 비동기 큐 도입을 고려하겠습니다."

[태도/인성]

[ ] 모르는 것은 솔직하게 인정하되, 아는 지식을 근거로 논리적 추론 시도하기.
  - "정확히는 모르겠지만 ~원리에 빗대어 볼 때 ~일 것 같습니다."

[ ] "과거 프로젝트에서 가장 힘들었던 점과 해결 방법" (협업 or 기술적 난관)
  - S(상황) T(문제) A(행동) R(결과) 구조로 구체적 사례 준비.

💡 면접 전 마지막 10분 리마인드
두괄식 답변: 결론부터 말하고 이유 설명하기.

Why: 모든 답변에 "왜냐하면 ~ 때문입니다" 붙이기.

코드 설명: 내 코드를 남에게 설명한다고 생각하고 논리 정리하기.