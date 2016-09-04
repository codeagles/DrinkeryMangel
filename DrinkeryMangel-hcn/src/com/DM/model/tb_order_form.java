package com.DM.model;

import java.sql.Date;

public class tb_order_form {
	
	private double money_sum;
	private double money_avg ;
	private int count ;
	private double money_max ;
	private double money_min ;
	public double getMoney_sum() {
		return money_sum;
	}
	public void setMoney_sum(double money_sum) {
		this.money_sum = money_sum;
	}
	public double getMoney_avg() {
		return money_avg;
	}
	public void setMoney_avg(double money_avg) {
		this.money_avg = money_avg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getMoney_max() {
		return money_max;
	}
	public void setMoney_max(double money_max) {
		this.money_max = money_max;
	}
	public double getMoney_min() {
		return money_min;
	}
	public void setMoney_min(double money_min) {
		this.money_min = money_min;
	}
	private String num;
	private String desk_num;
	private String datetime;
	private Double money;
	private int user_id;
	
	private String menu_num;
	private int amount;
	private String menuname;
	
	
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
	
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDesk_num() {
		return desk_num;
	}
	public void setDesk_num(String desk_num) {
		this.desk_num = desk_num;
	}
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
