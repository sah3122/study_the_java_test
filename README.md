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
      