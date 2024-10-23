package com.example.weatherapp.repository;

import com.example.weatherapp.model.WeatherSummary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface WeatherSummaryRepository extends JpaRepository<WeatherSummary, Long> {

    List<WeatherSummary> findByCity(String city);

    List<WeatherSummary> findByDate(LocalDate date);

    List<WeatherSummary> findByCityAndDate(String city, LocalDate date);

    List<WeatherSummary> findByDateBefore(LocalDate date);
}
