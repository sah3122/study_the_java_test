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
                
                        