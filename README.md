# meets backend

소개팅 앱 백엔드의 기본 골격입니다. `dropshop`의 패턴을 참고해서 `common`과 `domain`을 분리하고, 각 도메인 안에서 `controller/service/repository/dto/entity`를 나누는 구조로 시작합니다.

## package structure

```text
src/main/java/com/example/meets
├─ common
│  ├─ config
│  ├─ dto
│  ├─ entity
│  └─ exception
└─ domain
   ├─ auth
   ├─ chat
   ├─ match
   ├─ member
   │  ├─ controller
   │  ├─ dto
   │  │  ├─ request
   │  │  └─ response
   │  ├─ entity
   │  ├─ repository
   │  └─ service
   └─ profile
```

## current baseline

- Spring Boot 4 / Java 17 / Gradle wrapper
- 공통 API 응답 래퍼와 전역 예외 처리
- JPA Auditing용 `BaseEntity`
- 첫 샘플 도메인으로 `member` CRUD 일부 구현
- H2 기반 로컬 실행 설정

## domain expansion guide

- `auth`: 로그인, 토큰, 인증/인가
- `profile`: 자기소개, 사진, 관심사, 위치, 이상형
- `match`: 좋아요, 매칭, 추천 로직
- `chat`: 매칭 이후 채팅방, 메시지, 읽음 처리

새 도메인을 추가할 때도 `domain/{name}` 아래에서만 확장하고, 여러 도메인에서 재사용되는 코드는 `common`으로 올리는 방식으로 유지하면 됩니다.

## run

```bash
./gradlew.bat bootRun
```

기본 포트는 `8080`, H2 콘솔은 `/h2-console`, 헬스체크는 `/actuator/health` 입니다.

