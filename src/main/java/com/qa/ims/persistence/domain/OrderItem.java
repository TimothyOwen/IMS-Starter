package com.qa.ims.persistence.domain;

public class OrderItem {
	private Long order_item_id;
	private Long order_id;
	private Long item_id;
	private int item_quantity;
	public OrderItem(Long order_id, Long item_id, int item_quantity) {
		this.order_id = order_id;
		this.item_id = item_id;
		this.item_quantity = item_quantity;
	}
	public OrderItem(Long order_item_id, Long order_id, Long item_id, int item_quantity) {
		this.order_item_id = order_item_id;
		this.order_id = order_id;
		this.item_id = item_id;
		this.item_quantity = item_quantity;
	}
	public Long getOrderItemId() {
		return order_item_id;
	}
	public void setOrderItemId(Long order_item_id) {
		this.order_item_id = order_item_id;
	}
	public Long getOrderId() {
		return order_id;
	}
	public void setOrderId(Long order_id) {
		this.order_id = order_id;
	}
	public Long getItemId() {
		return item_id;
	}
	public void setItemId(Long item_id) {
		this.item_id = item_id;
	}
	public int getItemQuantity() {
		return item_quantity;
	}
	public void setItemQuantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}
	@Override
	public String toString() {
		return "Order Item Id= " + order_item_id + "  Order Id= " + order_id + "  Item Id= " + item_id
				+ " Item Quantity= " + item_quantity;
	}
	
}