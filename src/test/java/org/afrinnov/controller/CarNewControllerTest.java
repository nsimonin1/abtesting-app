package org.afrinnov.controller;

import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
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
class CarNewControllerTest {

    @InjectMocks
    private CarNewController controller;

    @Mock
    private CarService carService;
    @Mock
    private FeatureTools featureTools;

    @Test
    void should_send_dynamic_page_when_ab_testing_false() {
        //Given When
        ModelAndView mav = controller.getRequest();
        //Then
        assertThat(mav.getViewName()).isEqualTo("pages/car-new");
        assertThat(mav.getModel()).containsKey("car");
        verify(featureTools).isNewFeatureCarsNewAllow();
    }

    @Test
    void should_send_react_page_when_ab_testing_true() {
        //Given
        lenient().when(featureTools.isNewFeatureCarsNewAllow()).thenReturn(true);
        //When
        ModelAndView mav = controller.getRequest();
        //Then
        assertThat(mav.getViewName()).isEqualTo("page-react");
        assertThat(mav.getModel()).containsKey("data");
    }
}