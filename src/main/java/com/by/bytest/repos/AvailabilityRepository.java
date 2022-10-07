package com.by.bytest.repos;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.by.bytest.entity.Availability;
@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Integer>{
	Optional<Availability> findByStoreNoAndProductIdAndDateAndAvailQtyGreaterThan(String storeNo,String productId,Date date,Double availQty);

}
