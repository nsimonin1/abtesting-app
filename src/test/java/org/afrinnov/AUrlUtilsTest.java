package org.afrinnov;

import org.junit.jupiter.api.Test;

import static org.afrinnov.AUrlUtils.failedRedirect;
import static org.assertj.core.api.Assertions.assertThat;

class AUrlUtilsTest {
    @Test
    void should_send_failed_redirect_when_give_error_code() {
        assertThat(failedRedirect("/car",202)).isEqualTo("redirect:/car?error-code=202");
    }
}