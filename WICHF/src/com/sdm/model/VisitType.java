package com.sdm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visit_type")
public class VisitType {
	@Id
	@Column(name="visit_type_id")
	private int visitTypeId;
	
	@Column(name="visit_type")
	private String visitType;

	@Column(name="visit_time")
	private int visitTime;

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public int getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(int visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public int getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(int visitTime) {
		this.visitTime = visitTime;
	}

	
	
	
}
