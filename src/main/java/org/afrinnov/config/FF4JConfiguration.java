package org.afrinnov.config;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.cache.InMemoryCacheManager;
import org.ff4j.conf.XmlConfig;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.store.JdbcFeatureStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class FF4JConfiguration {


    @Bean
    @DependsOn(value = {"liquibase"})
    public FF4j getFF4j(DataSource dataSource) {

        FF4j ff4j = new FF4j();

        initFeatureStore(dataSource, ff4j);

        initPropertyStore(dataSource, ff4j);

        initJdbcEventStore(dataSource, ff4j);

        enableAuditProxy(ff4j);

        enableCacheProxy(ff4j);

        loadInitFeatures(ff4j);
        return ff4j;
    }

    private void enableCacheProxy(FF4j ff4j) {
        ff4j.cache(new InMemoryCacheManager());
    }

    private void enableAuditProxy(FF4j ff4j) {
        ff4j.audit(true);
    }

    private void initJdbcEventStore(DataSource dataSource, FF4j ff4j) {
        JdbcEventRepository eventRepository = new JdbcEventRepository(dataSource);
        eventRepository.createSchema();
        ff4j.setEventRepository(eventRepository);
    }

    private void initPropertyStore(DataSource dataSource, FF4j ff4j) {
        JdbcPropertyStore jdbcPropertyStore = new JdbcPropertyStore(dataSource);
        jdbcPropertyStore.createSchema();
        ff4j.setPropertiesStore(jdbcPropertyStore);
    }

    private void initFeatureStore(DataSource dataSource, FF4j ff4j) {
        JdbcFeatureStore store = new JdbcFeatureStore(dataSource);
        store.createSchema();
        ff4j.setFeatureStore(store);
    }

    private void loadInitFeatures(FF4j ff4j) {
        XmlConfig xmlConfig = ff4j.parseXmlConfig("db/init-ff4j.xml");
        FeatureStore store = ff4j.getFeatureStore();
        PropertyStore propertyStore = ff4j.getPropertiesStore();
        xmlConfig.getFeatures().values().stream()
                .filter(feature -> !store.exist(feature.getUid()))
                .forEach(store::create);
        xmlConfig.getProperties().values().stream()
                .filter(property -> !propertyStore.existProperty(property.getName()))
                .forEach(propertyStore::createProperty);
    }

}
