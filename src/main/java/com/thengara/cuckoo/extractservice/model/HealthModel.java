package com.thengara.cuckoo.extractservice.model;

import java.util.Date;

public class HealthModel {
	private final Long id;
	private final String name;
	private Date startDate;
	private Date endDate;
	private final boolean isHealhty;

	public HealthModel(Long id, String name, Date startDate, Date endDate,
			boolean isHealhty) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isHealhty = isHealhty;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isHealhty() {
		return isHealhty;
	}

	@Override
	public String toString() {
		return "HealthModel [id=" + id + ", name=" + name + ", startDate="
				+ startDate.toString() + ", endDate=" + endDate.toString()
				+ ", isHealhty=" + isHealhty + "]";
	}

}
