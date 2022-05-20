package com.by.bytest.repos;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.by.bytest.entity.Capacity;
@Repository
public interface CapacityRepository extends CrudRepository<Capacity,Integer> {
	
	Optional<Capacity> findByStoreNoAndDateAndNoOfOrdersAcceptedGreaterThan(String storeNo,Date date,Double noOfOrdersAccepted);
}
