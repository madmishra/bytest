package com.by.bytest.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.by.bytest.entity.Inventory;
@Repository
public interface AvailabilityRepository extends CrudRepository<Inventory, Integer>{
	List<Inventory> findByProductidAndAvailDateBetween(String ProdId,Date start,Date end);
	List<Inventory> findByProductidAndAvailDateGreaterThanAndAvailDateLessThan(String ProdId,Date start,Date end);

}
