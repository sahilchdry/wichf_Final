package com.sdm.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="room_non_availablity")
public class RoomNonAvailability {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="room_non_availablity_date")
	private Date roomNotAvailableDate;
	
	@ManyToOne(targetEntity = Room.class, cascade = CascadeType.ALL)
	@JoinColumn(name="room_id", referencedColumnName="room_id")
	private Room room;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRoomNotAvailableDate() {
		return roomNotAvailableDate;
	}

	public void setRoomNotAvailableDate(Date roomNotAvailableDate) {
		this.roomNotAvailableDate = roomNotAvailableDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
}
