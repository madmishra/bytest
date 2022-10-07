package com.by.bytest.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.by.bytest.entity.Inventory;
import com.by.bytest.entity.StoreCalendar;
import com.by.bytest.repos.AvailabilityRepository;
import com.by.bytest.repos.CalendarRepository;
@Component
public class DataLoader {
	private AvailabilityRepository ar;
	private CalendarRepository calrepo;

	public DataLoader(AvailabilityRepository availRepo, CalendarRepository calRepo) {
		this.ar = availRepo;
		this.calrepo=calRepo;
	}
	
	@PostConstruct
	public void run() throws Exception {
		ar.save(new Inventory("Prod1","Shirt","EACH", 10.0, new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-19")));
		ar.save(new Inventory("Prod1","Shirt","EACH", 20.0, new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-21")));
		ar.save(new Inventory("Prod1","Shirt","EACH", 20.0, new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-28")));
		calrepo.save(new StoreCalendar("STORE001", "SUNDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE001", "MONDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE001", "TUESDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE001", "WEDNESDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE001", "THURSDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE001", "FRIDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE001", "SATURDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE002", "SUNDAY", LocalTime.parse("13:30")));
		calrepo.save(new StoreCalendar("STORE003", "MONDAY", LocalTime.parse("13:30")));
	}

}
