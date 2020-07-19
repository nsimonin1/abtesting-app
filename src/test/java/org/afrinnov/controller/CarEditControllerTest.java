package org.afrinnov.controller;

import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.afrinnov.dto.CarDto.CarDtoBuilder.aCarDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarEditControllerTest {

    @InjectMocks
    private CarEditController controller;

    @Mock
    private CarService carService;
    @Mock
    private FeatureTools featureTools;

    @Test
    void should_send_dynamic_page_when_ab_testing_false() {
        //Given
        String code = "0001";
        lenient().when(carService.oneCar(code)).thenReturn(Optional.of(aCarDto().build()));
        // When
        ModelAndView mav = controller.getRequest(code);
        //Then
        assertThat(mav.getViewName()).isEqualTo("pages/car-edit");
        assertThat(mav.getModel()).containsKey("car");
        verify(featureTools).isNewFeatureCarsEditAllow();
    }

    @Test
    void should_send_dynamic_page_with_error_when_ab_testing_false_and_car_not_found() {
        //Given
        String code = "0001";
        lenient().when(carService.oneCar(code)).thenReturn(Optional.empty());
        // When
        ModelAndView mav = controller.getRequest(code);
        //Then
        assertThat(mav.getViewName()).isEqualTo("pages/car-edit");
        assertThat(mav.getModel()).containsKey("error");
        verify(featureTools).isNewFeatureCarsEditAllow();
    }

    @Test
    void should_send_react_page_when_ab_testing_true() {
        //Given
        String code = "0001";
        lenient().when(featureTools.isNewFeatureCarsEditAllow()).thenReturn(true);
        lenient().when(carService.oneCar(code)).thenReturn(Optional.of(aCarDto().build()));
        //When
        ModelAndView mav = controller.getRequest(code);
        //Then
        assertThat(mav.getViewName()).isEqualTo("page-react");
        assertThat(mav.getModel()).containsKey("data");
    }
}