package com.by.bytest.repos;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.by.bytest.entity.StoreCalendar;
@Repository
public interface CalendarRepository extends CrudRepository<StoreCalendar, Integer> {
	Optional<StoreCalendar> findByLocationIDAndDayAndCutOffTimeGreaterThan(String locationID, String day, LocalTime cutOffTime);
	Optional<StoreCalendar> findByLocationIDAndCutOffTimeGreaterThan(String locationID, LocalTime cutOffTime);


}
