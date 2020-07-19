package org.afrinnov.controller;

import org.afrinnov.service.CarService;
import org.assertj.core.api.Assertions;
import org.ff4j.FF4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarsControllerUTest {
    @InjectMocks
    private CarsController controller;
    @Mock
    private CarService carService;
    @Mock
    private FF4j getFF4j;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    void should_send_dynamic_page_when_ab_testing_false() {
        //Given
        when(getFF4j.check("new_cars_list")).thenReturn(false);
        //When
        ModelAndView mav = controller.carsRequest();
        //Then
        assertThat(mav.getViewName()).isEqualTo("pages/cars");
        assertThat(mav.getModel()).containsKey("cars");
    }

    @Test
    @Disabled
    void should_send_react_page_when_ab_testing_false() {
        //Given
        when(getFF4j.check("new_cars_list")).thenReturn(true);
        //When
        ModelAndView mav = controller.carsRequest();
        //Then
        assertThat(mav.getViewName()).isEqualTo("page-react");
        assertThat(mav.getModel()).containsKey("data");
    }
}