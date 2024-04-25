package com.pennant.irctc.mvc;

import java.sql.Date;

public class TicketModel {
	private Integer ticket_No;
	private Integer pnr_No;
	private Date doj;
	private String from;
	private String to;
	private Integer class_Index;
	private Double total_Fare;
	private Integer passenger_count;
	private Integer train_No;

	public Integer getTicket_No() {
		return ticket_No;
	}

	public void setTicket_No(Integer ticket_No) {
		this.ticket_No = ticket_No;
	}

	public Integer getPnr_No() {
		return pnr_No;
	}

	public void setPnr_No(Integer pnr_No) {
		this.pnr_No = pnr_No;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Integer getClass_Index() {
		return class_Index;
	}

	public void setClass_Index(Integer class_Index) {
		this.class_Index = class_Index;
	}

	public Double getTotal_Fare() {
		return total_Fare;
	}

	public void setTotal_Fare(Double total_Fare) {
		this.total_Fare = total_Fare;
	}

	public Integer getPassenger_count() {
		return passenger_count;
	}

	public void setPassenger_count(Integer passenger_count) {
		this.passenger_count = passenger_count;
	}

	public Integer getTrain_No() {
		return train_No;
	}

	public void setTrain_No(Integer train_No) {
		this.train_No = train_No;
	}
}