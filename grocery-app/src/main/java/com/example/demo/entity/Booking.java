package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany
	private List<GroceryItem> items;

	public Booking() {
		super();
	}

	public Booking(int id, List<GroceryItem> items) {
		super();
		this.id = id;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<GroceryItem> getItems() {
		return items;
	}

	public void setItems(List<GroceryItem> items) {
		this.items = items;
	}

	// Other fields like user, date, etc.

	// Getters and setters

	// Constructors

}
