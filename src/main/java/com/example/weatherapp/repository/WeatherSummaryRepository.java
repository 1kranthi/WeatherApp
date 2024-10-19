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

    // Fetch summaries for a specific city
    List<WeatherSummary> findByCity(String city);

    // Fetch summaries for a specific date
    List<WeatherSummary> findByDate(LocalDate date);

    // Fetch summaries for a city on a specific date
    List<WeatherSummary> findByCityAndDate(String city, LocalDate date);

    // Fetch all summaries before a certain date (for historical data)
    List<WeatherSummary> findByDateBefore(LocalDate date);
}

