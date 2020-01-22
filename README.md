# study_the_java_test
Inflearn 더 자바, 애플리케이션을 테스트하는 다양한 방법

* JUnit 5소개
  * 자바 개발자가 가장 많이 사용하는 테스팅 프레임워크
  * 자바 8 이상 필요
  * 대체제 : TestNG, Spock, ...
  * JUnit 5
    * Platform : 테스트를 실행해주는 런처 제공, TestEngine API 제공
    * Jupiter : TestEngine API 구현체로 JUnit 5를 제공
    * Vintage : JUnit 4와 3을 지원하는 TestEngine 구현체
* JUnit 5 시작하기
    * 기본 애노테이션
        * @Test
            * private 를 안붙여도 된다.
        * @BeforeAll / @AfterAll 
            * 테스트 시작 전 / 후 한번만 실행
            * static 메소드로 구현
        * @BeforeEach / @AfterEach
            * 각 테스트 시작 전 / 후 실행
        * @Disabled
            * 실행하지 않을 테스트 지정
* JUnit 5 : 테스트 이름 표기하기
    * @DisplayNameGeneration
        * Methods와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정
        * 기본 구현체로 ReplaceUnderscores 제공
    * @DisplayName
        * 어떤 테스트 인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션
        * @DisplayNameGeneration보다 우선 순위가 높다
* JUnit 5 : Assertion
    * org.junit.jupiter.api.Assertions.*
        * 실제 값이 기대한 값과 같은지 확인 : assertEquals(expected, actual)
        * 값이 null이 아닌지 확인 : assertNotNull(actual)
        * 다음 조건이 참인지 확인 : assertTrue(boolean)
        * 모든 확인 구문 확인 : assertAll(executables)
        * 예외 발생 확인 : assertThrows(expectedType, executable)
        * 특정 시간 안에 실행이 완료되는지 확인 : assertTimeout(duration, executable)
    * 마지막 매개변수로 Supplier<String> 타입의 인스턴스를 람다 형태로 제공할 수 있다.
        * 복잡한 메시지 생성해야 하는 경우 사용하면 실패한 경우에만 해당 메시지를 만들게 할 수 있다.
* JUnit 5 : 조건에 따라 테스트 실행하기
    * 특정한 조건을 만족하는 경우에 테스트를 실행하는 방법
    * org.junit.jupiter.api.Assumptions.*
        * assumeTrue(조건)
        * assumingThat(조건, 테스트)
    * @Enabled__ 와 @Disabled__
        * OnOS
        * OnJre
        * IfSystemProperty
        * IfEnvironmentVariable
        * If
* 태깅과 필터링
    * 테스트 그룹을 만들고 원하는 테스트 그룹만 테스트를 실행할 수 있는 기능
    * @Tag
        * 테스트 메소드에 태그를 추가할 수 있다.
        * 하나의 테스트 메소드에 여러 태그를 사용할 수 있다.
    * 인텔리 J에서 특정 태그로 테스트 필터링
    * 메이븐에서 테스트 필터
* 커스텀 태그
    * JUnit5에서 제공하는 META Annotation을 활용해서 만들수있다.
* 테스트 반복하기
    * @RepeatedTest
        * 반복 횟수와 반복 테스트 이름을 설정할 수 있다.
            * {displayName}
            * {currentRepetition}
            * {totalRepetitions}

        * RepetitionInfo 타입의 인자를 받을 수 있다.
    * @ParameterizedTest
        * 테스트에 여러 다른 매개변수를 대입해가며 반복 실행한다.
            * {displayName}
            * {index}
            * {arguments}
            * {0}, {1}, ...
    * 인자 값들의 소스
        * @ValueSource
        * @NullSource, @EmptySource, @NullAndEmptySource
        * @EnumSource
        * @MethodSource
        * @CvsSource
        * @CvsFileSource
        * @ArgumentSource
    * 인자 값 타입 변환
        * 암묵적인 타입 변환
            * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-argument-conversion-implicit
        * 명시적인 타입 변환
            * SimpleArgumentConverter 상속 받은 구현체 제공
            * @ConvertWith
        * 인자 값 조합
            * ArgumentsAccessor
            * 커스텀 Asscessor
                * ArgumentsAggregator 인터페이스 구현
                * @AggregateWith
* 테스트 인스턴스
    * JUnit은 테스트 메소드 마다 테스트 인스턴스를 새로 만든다.
        * 이것이 기본 전략
        * 테스트 메소드를 독립적으로 실행하여 예상치 못한 부작용을 방지하기 위함
        * 이 전략을 JUnit 5에서 변경할 수 있다.
    * @TestInstance(Lifecycle.PER_CLASS)
        * 테스트 클래스당 인스턴스를 하나만 만들어 사용한다.
        * 경우에 따라, 테스트 간에 공유하는 모든 상태를  @BeforeEach 또는 @AfterEach에서 초기화 할 필요가 있다.
        * @BeforeAll과 @AfterAll을 인스턴스 메소드 또는 인터페이스에 정의한 default 메소드로 정의할 수 있다.                                
* 테스트 순서
    * 실행할 테스트 메소드는 특정한 순서에 의해 실행되지만 어떻게 그 순서를 정하는지는 의도적으로 분명하지 않다.
    * 경우에 따라 특정 순서대로 테스트를 실행하고 싶을 때도 있다. 그 경우애는 테스트 메소드를 원하는 순서에 따라 실행하도록 @TestInstance(Lifecycle.PER_CLASS) <br>
      와 함꼐 @TestMethodOrder를 사용할 수 있다.
        - MethodOrder 구현체를 설정한다.
        - 기본 구현체
            - Alphanumeric
            - OrderAnnotation
            - Random
* junit-platform.properties
    * JUnit 설정 파일로, 클래스패스 루트 (src/test/resources/)에 넣어두면 적용된다.
    * 테스트 인스턴스 라이프사이클 설정
        * junit.jupiter.testinstance.lifecycle.default = per_class
    * 확장팩 자동 감지 기능
        * junit.jupiter.extensions.autodetection.enabled = true
    * @Disabled 무시하고 실행하기
        * junit.jupiter.conditions.deactivate = org.junit.*DisabledCondition
    * 테스트 이름 표기 전략 설정
        * junit.jupiter.displayname.generator.default = org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
* 확장 모델
    * JUnit 4의 확장 모델은  @RunWith(Runner), TestRule
    * JUnit 5의 확장 모델은 단 하나, Extension
    
    * 확장 모델 등록 방법
        * 선언적인 등록 @ExtensionWith
        * 프로그래밍 등록 @RegisterExtention
        * 자동 등록 자바 ServiceLoader 이용
    * 확장 모델 만드는 방법
        * 테스트 실행 조건
        * 테스트 인스턴스 팩토리
        * 테스트 인스턴스 후 처리기
        * 테스트 매개별수 리졸버
        * 테스트 라이프사이클 콜백 
        * 예외 처리
        * ...
* Junit 4 마이그레이션
    * junit-veintage-engine을 의존성으로 추가하면, JUnit 5의 junit-platform으로 JUnit 3과 4로 작성된 테스트를 실행할 수 있다.
    * @Rule은 기본적으로 지원하지 않지만 junit-jupiter-migrationsupport 모듈이 제공하는 @EnableRuleMigrationSupport를 사용하면 다음 타입의 Rule을 지원한다.
        * ExternalResource
        * Verifier
        * ExpectedException
    * Junit 4 -> Junit 5
        * @Category(Class) -> @Tag(String) 
        * @RunWith, @Rule, @ClassRule -> @ExtendWith, @RegisterExtension
        * @Ignore -> @Disabled
        * @Before, @After, @BeforeClass, @AfterClass -> @BeforeEach, @AfterEach, @BeforeAll, @AfterAll                 
* Mocito 소개
    * Mock : 진짜 객체와 비슷하게 동작하지만 프로그래머가 직접 그 객체의 행동을 관리하는 객체
    * Mockito : Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법을 제공한다.
* 시작하기
    * Spring boot 2.2+ 프로젝트 생성시 spring-boot-starter-test에서 자동으로 Mockito 를 추가해줌
    * 다음 세 가지만 알면 MOCK을 활용한 테스트를 쉽게 작성할 수 있다.
        - Mock을 만드는 방법
        - Mock이 어떻게 동작해야 하는지 관리하는 방법
        - Mock의 행동을 검증하는 방법
* Mock 객체 만들기   
    * Mockito.mock() 메소드로 만드는 방법
        * MemberService memberService = mock(MemberService.class);
        * StudyRepository studyRepository = mock(StudyRepository.class);        
    * Mock 애노테이션으로 만드는 방법
        - Junit 5 extension으로 MockitoExtension을 사용해야 한다.
        - 필드
        - 메소드 매개변수                                                         