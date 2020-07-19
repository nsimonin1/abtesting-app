package org.afrinnov.tools;

import org.afrinnov.config.AbtestApplicationProperties;
import org.ff4j.FF4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.afrinnov.config.AbtestApplicationProperties.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FeatureToolsTest {
    @InjectMocks
    private FeatureTools featureTools;
    @Mock
    private FF4j getFF4j;
    @Mock
    private AbtestApplicationProperties applicationProperties;
    @Mock
    private Abtest abtest;

    @BeforeEach
    void setUp() {
        when(applicationProperties.getAbtest()).thenReturn(abtest);
    }

    @Test
    //@Disabled
    void shouldReturnTrueWhenCarsEditFeatureIsEnable() {
        //Given
        lenient().when(abtest.isPonderationStrategy()).thenReturn(true);
        lenient().when(getFF4j.check("new_cars_edit_weight")).thenReturn(true);
        //When
        boolean isNewFeatureCarsEditListAllow = featureTools.isNewFeatureCarsEditAllow();
        //Then
        assertThat(isNewFeatureCarsEditListAllow).isTrue();
    }

}