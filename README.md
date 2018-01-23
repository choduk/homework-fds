### 실행방법
```shell
./gradlew bootRun
```

### 설명
 - api
   - /v1/fraud/{user_Id} 형태의 단일 API
   - `datasource` 까지 구현되어 있음
   - engine의 테스트와 동일한 결과를 만들기위해 `MockUserActionLogRepositoryImpl` 인젝션 받도록 구성함 
 - engine
   - Multi-thread 로 동작
   - Rule을 구현체를 외부에서 주입받음
   - `Condition`과 `Validator`의 조합을 이용하여 로그를 처리함
   - 기본적인 컨셉
     - `Condition` : 입력된 로그를 filtering 하는 인터페이스 (`chain of responsibility` 패턴으로 구현되어 있음)
     - `RuleValidator` : 필터링된 로그를 매칭하는 인터페이스 
     - `UserActionLogDataExtractor` : 제네릭으로 처리된 각 로그에서 원하는 값을 추출할 수 있도록 도와주는 인터페이스 