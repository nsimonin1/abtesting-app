package org.afrinnov.feature;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.EventRepository;
import org.ff4j.audit.repository.InMemoryEventRepository;
import org.ff4j.conf.XmlConfig;
import org.ff4j.conf.XmlParser;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.store.InMemoryPropertyStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.store.InMemoryFeatureStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
class FeatureUTest {

    @Autowired
    private FF4j getFF4j;


    @Test
    void should_check_all_features_exist() {
        assertThat(getFF4j.exist("new_cars_list_weight")).isTrue();
        assertThat(getFF4j.exist("new_cars_list")).isTrue();
    }

    @Test
    void should_check_default_toggle_value() {

        assertThat(getFF4j.check("new_cars_list")).isFalse();

        double success = 0.0;
        for (int i = 0; i < 1000000; i++) {
            if (getFF4j.check("new_cars_list_weight")) {
                success++;
            }
        }
        double resultPercent = success / 1000000;
        assertThat(resultPercent < (0.6 + 0.001)).isTrue();
        assertThat(resultPercent > (0.6 - 0.001)).isTrue();
    }

    @Test
    void should_check_all_new_form_create_features_exist() {
        assertThat(getFF4j.exist("new_cars_new_weight")).isTrue();
        assertThat(getFF4j.exist("new_cars_new")).isTrue();
    }

    @Test
    void should_check_default_new_form_create_toggle_value() {

        assertThat(getFF4j.check("new_cars_new")).isFalse();

        double success = 0.0;
        for (int i = 0; i < 1000000; i++) {
            if (getFF4j.check("new_cars_new_weight")) {
                success++;
            }
        }
        double resultPercent = success / 1000000;
        assertThat(resultPercent < (0.2 + 0.001)).isTrue();
        assertThat(resultPercent > (0.2 - 0.001)).isTrue();
    }

    @Test
    void should_check_all_new_form_edit_features_exist() {
        assertThat(getFF4j.exist("new_cars_edit_weight")).isTrue();
        assertThat(getFF4j.exist("new_cars_edit")).isTrue();
    }

    @Test
    void should_check_default_new_form_edit_toggle_value() {

        assertThat(getFF4j.check("new_cars_edit")).isFalse();

        double success = 0.0;
        for (int i = 0; i < 1000000; i++) {
            if (getFF4j.check("new_cars_edit_weight")) {
                success++;
            }
        }
        double resultPercent = success / 1000000;
        assertThat(resultPercent < (0.2 + 0.001)).isTrue();
        assertThat(resultPercent > (0.2 - 0.001)).isTrue();
    }

    @TestConfiguration
    static class FakeConfiguration {
        @Value("db/init-ff4j.xml")
        private Resource resource;

        @Bean
        public FF4j getFF4j() throws IOException {
            XmlConfig configuration = new XmlParser().parseConfigurationFile(resource.getInputStream());
            FeatureStore featureStore = new InMemoryFeatureStore(configuration);
            PropertyStore propertyStore = new InMemoryPropertyStore(configuration);
            EventRepository logsAudit = new InMemoryEventRepository();

            FF4j ff4j = new FF4j();
            ff4j.setFeatureStore(featureStore);
            ff4j.setPropertiesStore(propertyStore);
            ff4j.setEventRepository(logsAudit);

            //ff4j.setEnableAudit(true);
            ff4j.setAutocreate(true);
            return ff4j;
        }
    }

}
