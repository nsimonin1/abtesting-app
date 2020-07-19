package org.afrinnov.tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.afrinnov.config.AbtestApplicationProperties.Abtest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureTypeTest {
    @Mock
    private Abtest abtest;

    @Test
    void shouldReturnEditWeightFeatureNameWhenPonderationStrategyIsEnable() {
        //Given
        when(abtest.isPonderationStrategy()).thenReturn(true);
        //When
        String featureName = FeatureType.CARS_EDIT.getFeatureName(abtest);
        //Then
        assertThat(featureName).isEqualTo("new_cars_edit_weight");
    }

    @Test
    void shouldReturnEditFeatureNameWhenPonderationStrategyIsEnable() {
        //Given
        when(abtest.isPonderationStrategy()).thenReturn(false);
        //When
        String featureName = FeatureType.CARS_EDIT.getFeatureName(abtest);
        //Then
        assertThat(featureName).isEqualTo("new_cars_edit");
    }

    @Test
    void shouldReturnNewWeightFeatureNameWhenPonderationStrategyIsEnable() {
        //Given
        when(abtest.isPonderationStrategy()).thenReturn(true);
        //When
        String featureName = FeatureType.CARS_NEW.getFeatureName(abtest);
        //Then
        assertThat(featureName).isEqualTo("new_cars_new_weight");
    }

    @Test
    void shouldReturnNewFeatureNameWhenPonderationStrategyIsEnable() {
        //Given
        when(abtest.isPonderationStrategy()).thenReturn(false);
        //When
        String featureName = FeatureType.CARS_NEW.getFeatureName(abtest);
        //Then
        assertThat(featureName).isEqualTo("new_cars_new");
    }

    @Test
    void shouldReturnListWeightFeatureNameWhenPonderationStrategyIsEnable() {
        //Given
        when(abtest.isPonderationStrategy()).thenReturn(true);
        //When
        String featureName = FeatureType.CARS_LIST.getFeatureName(abtest);
        //Then
        assertThat(featureName).isEqualTo("new_cars_list_weight");
    }

    @Test
    void shouldReturnListFeatureNameWhenPonderationStrategyIsEnable() {
        //Given
        when(abtest.isPonderationStrategy()).thenReturn(false);
        //When
        String featureName = FeatureType.CARS_LIST.getFeatureName(abtest);
        //Then
        assertThat(featureName).isEqualTo("new_cars_list");
    }
}