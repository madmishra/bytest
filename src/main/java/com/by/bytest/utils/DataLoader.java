package com.by.bytest.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.by.bytest.entity.Availability;
import com.by.bytest.entity.Capacity;
import com.by.bytest.entity.StoreCalendar;
import com.by.bytest.repos.AvailabilityRepository;
import com.by.bytest.repos.CalendarRepository;
import com.by.bytest.repos.CapacityRepository;
@Component
public class DataLoader {
	private AvailabilityRepository ar;
	private CapacityRepository cr;
	private CalendarRepository calrepo;

	public DataLoader(AvailabilityRepository availRepo,CapacityRepository capRepo, CalendarRepository calRepo) {
		this.ar = availRepo;
		this.cr = capRepo;
		this.calrepo=calRepo;
	}
	
	@PostConstruct
	public void run() throws Exception {
		ar.save(new Availability("Store001", "Prod1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-19"), 1.0));
		ar.save(new Availability("Store001", "Prod1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-20"), 3.0));
		ar.save(new Availability("Store001", "Prod1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-21"), 0.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-19"), 0.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-20"), 2.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-21"), 2.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-21"), 2.0));
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
