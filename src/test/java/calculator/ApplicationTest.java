package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 기본_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 빈_문자열() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 숫자_하나() {
        assertSimpleTest(() -> {
            run("1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 구분자만_있는_문자열() {
        assertSimpleTest(() -> {
            run(",");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 커스텀_구분자_사용_2() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용_3() {
        assertSimpleTest(() -> {
            run("//;\\n1;2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 숫자를_구분자로_사용() {
        assertSimpleTest(() -> {
            run("//1\\n31214");
            assertThat(output()).contains("결과 : 9");
        });
    }

    @Test
    void 커스텀_구분자_공백() {
        assertSimpleTest(() -> {
            run("//; \\n1; 2 :3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 잘못된_구분자_종료문자_없음() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("// ; \\1; 2 :3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
