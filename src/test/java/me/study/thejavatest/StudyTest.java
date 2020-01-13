package me.study.thejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    @Test
    @DisplayName("스터디 만들기")
    void create() {
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

    @Test
    @DisplayName("스터디 만들기 2")
    void created1() {
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