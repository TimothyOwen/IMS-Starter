package com.qa.ims.persistence.domain;

public class Item {
	private Long item_id;
	private String item_name;
	private Double price;
	public Item(String item_name, Double price) {
		this.item_name = item_name;
		this.price = price;
	}
	public Item(Long item_id, String item_name, Double price) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
	}
	public Long getItemId() {
		return item_id;
	}
	public void setItemId(Long item_id) {
		this.item_id = item_id;
	}
	public String getItemName() {
		return item_name;
	}
	public void setItemName(String item_name) {
		this.item_name = item_name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	//toString & hashCode & Equals
}
