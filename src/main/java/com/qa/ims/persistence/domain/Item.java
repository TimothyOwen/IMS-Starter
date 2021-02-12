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
	
	@Override
	public String toString() {
		return "Item ID: " + item_id + "  Item Name: " + item_name + "  Price: " + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (getPrice() == null) {
			if (other.getPrice() != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}
