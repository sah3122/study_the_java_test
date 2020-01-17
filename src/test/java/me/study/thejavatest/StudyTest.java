package me.study.thejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@ExtendWith(FindSlowTestExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {
    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    void createTest() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println(this);
        System.out.println(value++);
    }

    @Order(2)
    @Test
    void createTest2() {
        System.out.println(this);
        System.out.println(value++);
    }

    @Order(1)
    @FastTest
    @DisplayName("스터디 만들기")
    void create() {
        System.out.println(this);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = illegalArgumentException.getMessage();
        assertEquals("limit은 0 보다 커야 한다.", message);

        assertTimeout(Duration.ofMillis(300), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // assertTimeoutPreemptively 사용시 타임 아웃 발생시 테스트 바로 종료
        // TODO ThreadLocal 을 사용하는 경우 예상하지못한 결과가 발생할 수 있다. ex ) transaction

        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 "+StudyStatus.DRAFT+"여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0 보다 커야 한다.")
        );

    }

    @SlowTest
    @DisplayName("스터디 만들기 2")
    void created1() {
        System.out.println(this);
        String test_env = System.getenv("TEST_ENV");
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("local");
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 3")
    @EnabledOnOs(OS.MAC) // DisabledOnOS
    void created_on_mac() {
        String test_env = System.getenv("TEST_ENV");

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 4")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    void created_on_jre() {
        String test_env = System.getenv("TEST_ENV");

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 4")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
    void created_on_env() {
        String test_env = System.getenv("TEST_ENV");

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @DisplayName("스터디 만들기 10회 반복")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기 파라미터")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    @EmptySource // 비어있는 문자열을 추가로 넣어줌
    @NullSource // null 을 파라미터로 넣어줌,
    @NullAndEmptySource // null 과 비어있는 파라미터를 넣어준다.
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("스터디 만들기 파라미터2")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(ints = {10, 20, 30})
    void parameterizedTest2(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            assertEquals(Study.class, aClass, "Can only to Study");
            return new Study(Integer.parseInt(o.toString()));
        }
    }

    @DisplayName("스터디 만들기 cvs")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, '자바스터디'", "20, '스프링'"})
    void csvTest(int limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study.getLimit());
    }

    @DisplayName("스터디 만들기 cvs argument accessor")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, '자바스터디'", "20, '스프링'"})
    void csvArgumentAccessorTest(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study.getLimit());
    }

    @DisplayName("스터디 만들기 cvs argument aggregator")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, '자바스터디'", "20, '스프링'"})
    void csvArgumentAggregatorTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    //aggregator inner static class 이거나 public class여야 한다.
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beferoEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

}