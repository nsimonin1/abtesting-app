package org.afrinnov.repository;

import org.afrinnov.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    @Query("SELECT MAX(car.code) FROM CarEntity car WHERE car.code LIKE '________'")
    Optional<String> findLastCode();

    Optional<CarEntity> findOneByCode(String code);
}
