package org.afrinnov.service;

import org.afrinnov.entities.CarEntity;
import org.afrinnov.exception.AfrinnovException;
import org.afrinnov.repository.CarRepository;
import org.afrinnov.request.CarRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceUTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;
    @Captor
    private ArgumentCaptor<CarEntity> carCaptor;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("load all cars \uD83D\uDE00")
    void allCars() {
        carService.allCars();
        verify(carRepository).findAll();
    }

    @Test
    @DisplayName("i create my first car \uD83D\uDE05 and get first code")
    void my_first_create_car() {
        //Given
        CarRequest carRequest = new CarRequest("Yaris", "0001");
        when(carRepository.findLastCode()).thenReturn(Optional.of("00001009"));

        when(carRepository.save(any(CarEntity.class))).then(new Answer<CarEntity>() {
            private long sequence = 1;

            @Override
            public CarEntity answer(InvocationOnMock invocation) {
                CarEntity entity = invocation.getArgument(0);
                entity.setId(sequence++);
                entity.setVersion(1L);
                return entity;
            }
        });

        carService.createCar(carRequest);

        verify(carRepository).findLastCode();
        verify(carRepository).save(carCaptor.capture());
        CarEntity entity = carCaptor.getValue();
        assertThat(entity).isNotNull();
        assertThat(entity.getVersion()).isEqualTo(1L);
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getCode()).isEqualTo("00001010");
    }

    @Test
    @DisplayName("create car failed  \uD83D\uDE1C when persistence run bad")
    void test() {
        //Given
        CarRequest carRequest = new CarRequest("Yaris", "0001");

        when(carRepository.save(any(CarEntity.class))).then((Answer<CarEntity>) invocation -> {
            throw new PersistenceException();
        });

        Throwable error = catchThrowable(() -> carService.createCar(carRequest));

        assertThat(error).isNotNull();
        assertThat(error).isInstanceOf(AfrinnovException.class);
    }

    @Test
    @DisplayName("i create a car \uD83D\uDE05 and get next generated code")
    void create_nth_cars_and_get_nth_plus_one_next_code() {
        //Given
        CarRequest carRequest = new CarRequest("Yaris", "0001");

        when(carRepository.save(any(CarEntity.class))).then(new Answer<CarEntity>() {
            private long sequence = 1;

            @Override
            public CarEntity answer(InvocationOnMock invocation) {
                CarEntity entity = invocation.getArgument(0);
                entity.setId(sequence++);
                entity.setVersion(1L);
                return entity;
            }
        });

        carService.createCar(carRequest);

        verify(carRepository).findLastCode();
        verify(carRepository).save(carCaptor.capture());
        CarEntity entity = carCaptor.getValue();
        assertThat(entity).isNotNull();
        assertThat(entity.getVersion()).isEqualTo(1L);
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getCode()).isEqualTo("00000001");
    }
}