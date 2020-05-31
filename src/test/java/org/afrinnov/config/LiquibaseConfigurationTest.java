package org.afrinnov.config;

import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(value = {LiquibaseConfiguration.class})
class LiquibaseConfigurationTest {
    @Autowired
    private SpringLiquibase liquibase;

    @Test
    void should_check_when_load_master_changelog_file() {
        assertThat(liquibase.getChangeLog()).isEqualTo("classpath:db/master.xml");
    }
}