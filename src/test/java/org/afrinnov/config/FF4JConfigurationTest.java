package org.afrinnov.config;

import org.ff4j.FF4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(value = {LiquibaseConfiguration.class, FF4JConfiguration.class})
class FF4JConfigurationTest {
    @Autowired
    private FF4j getFF4j;

    @Test
    void should_check_true_when_new_cars_list_feature_exist() {
        assertThat(getFF4j.exist("new_cars_list"))
                .isTrue();
    }
}