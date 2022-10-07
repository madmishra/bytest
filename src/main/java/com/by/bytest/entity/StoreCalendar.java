package com.by.bytest.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class StoreCalendar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String locationID;
	private String day;
	private LocalTime cutOffTime;
	
	public StoreCalendar() {
		super();
	}
	public StoreCalendar(String locationID, String day, LocalTime cutOffTime) {
		super();
		this.locationID = locationID;
		this.day = day;
		this.cutOffTime = cutOffTime;
	}
	public String getLocationID() {
		return locationID;
	}
	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public LocalTime getCutOffTime() {
		return cutOffTime;
	}
	public void setCutOffTime(LocalTime cutOffTime) {
		this.cutOffTime = cutOffTime;
	}
	
}
