package com.DM.model;

public class tb_order_item {
	private int id;
	private String order_for_num;
	private String menu_num;
	private int amount;
	private int total;
	private String menuname;
	private String unit;
	private int unit_price;
	
	public int getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_for_num() {
		return order_for_num;
	}
	public void setOrder_for_num(String order_for_num) {
		this.order_for_num = order_for_num;
	}
	public String getMenu_num() {
		return menu_num;
	}
	public void setMenu_num(String menu_num) {
		this.menu_num = menu_num;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
