package org.afrinnov.controller;

import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarsControllerUTest {
    @InjectMocks
    private CarsController controller;
    @Mock
    private CarService carService;
    @Mock
    private FeatureTools featureTools;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_send_dynamic_page_when_ab_testing_false() {
        //Given When
        ModelAndView mav = controller.carsRequest();
        //Then
        assertThat(mav.getViewName()).isEqualTo("pages/cars");
        assertThat(mav.getModel()).containsKey("cars");
        verify(featureTools).isNewFeatureCarsListAllow();
    }

    @Test
    void should_send_react_page_when_ab_testing_false() {
        //Given
        lenient().when(featureTools.isNewFeatureCarsListAllow()).thenReturn(true);
        //When
        ModelAndView mav = controller.carsRequest();
        //Then
        assertThat(mav.getViewName()).isEqualTo("page-react");
        assertThat(mav.getModel()).containsKey("data");
    }
}