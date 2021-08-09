package org.afrinnov.config;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.cache.InMemoryCacheManager;
import org.ff4j.conf.XmlConfig;
import org.ff4j.conf.XmlParser;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.Property;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.store.JdbcFeatureStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.function.Predicate;

@Configuration(proxyBeanMethods = false)
public class FF4JConfiguration {
    @Value("db/init-ff4j.xml")
    private Resource initFf4jResource;

    @Bean
    @DependsOn(value = {"liquibase"})
    public FF4j getFF4j(DataSource dataSource) throws IOException {

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

    private void loadInitFeatures(FF4j ff4j) throws IOException {
        XmlConfig xmlConfig = new XmlParser().parseConfigurationFile(initFf4jResource.getInputStream());
        FeatureStore store = ff4j.getFeatureStore();
        PropertyStore propertyStore = ff4j.getPropertiesStore();
        xmlConfig.getFeatures().values().stream()
                .filter(doesNotFeatureExistInStore(store))
                .forEach(store::create);
        xmlConfig.getProperties().values().stream()
                .filter(doesNotPropertyExistInStore(propertyStore))
                .forEach(propertyStore::createProperty);
    }

    private Predicate<Property<?>> doesNotPropertyExistInStore(PropertyStore propertyStore) {
        return property -> !propertyStore.existProperty(property.getName());
    }

    private Predicate<Feature> doesNotFeatureExistInStore(FeatureStore store) {
        return feature -> !store.exist(feature.getUid());
    }

}
