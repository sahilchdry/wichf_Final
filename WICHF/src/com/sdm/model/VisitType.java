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
	private int visitType;

	public int getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(int visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public int getVisitType() {
		return visitType;
	}

	public void setVisitType(int visitType) {
		this.visitType = visitType;
	}
	
	
}
