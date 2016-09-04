package com.DM.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DM.model.tb_menu;
import com.DM.model.tb_order_item;

public class ItemDao {
	//查询菜品
	public static List<tb_order_item> selectItem(){
	List<tb_order_item> list =new ArrayList<tb_order_item>();
	String sql="select * from tb_order_item join tb_menu on tb_order_item.menu_num= tb_menu.num";
	ResultSet rs = Dao.executeQuery(sql);
	try {
		while(rs.next()){
			tb_order_item toi = new tb_order_item();
			toi.setId(rs.getInt("id"));
			toi.setMenu_num(rs.getString("menu_num"));
			toi.setMenuname(rs.getString("name"));
			toi.setUnit(rs.getString("unit"));
			toi.setAmount(rs.getInt("amount"));
			toi.setTotal(rs.getInt("total"));
			toi.setOrder_for_num(rs.getString("order_for_num"));
			toi.setUnit_price(rs.getInt("unit_price"));
			list.add(toi);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	Dao.close();
	return list;
}
	//查询菜品num
	public static List<tb_order_item> selectItemByNum(String num){
		List<tb_order_item> list =new ArrayList<tb_order_item>();
		String sql="select * from tb_order_item join tb_menu on tb_order_item.menu_num= '"+num+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_order_item toi = new tb_order_item();
				toi.setId(rs.getInt("id"));
				toi.setMenu_num(rs.getString("menu_num"));
				toi.setMenuname(rs.getString("name"));
				toi.setUnit(rs.getString("unit"));
				toi.setAmount(rs.getInt("amount"));
				toi.setTotal(rs.getInt("total"));
				toi.setOrder_for_num(rs.getString("order_for_num"));
				toi.setUnit_price(rs.getInt("unit_price"));
				list.add(toi);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;	
	}
	
	//查询通过Code
	public static List<tb_order_item> selectItemByCode(String code){
		List<tb_order_item> list =new ArrayList<tb_order_item>();
		String num = null;
		String code1;
		String sql1 ="select * from tb_menu where code ='"+code+"'";
		ResultSet rs1 =Dao.executeQuery(sql1);
		try {
			while(rs1.next()){
			
				code1=rs1.getString("code");
				num = rs1.getString("num");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql="select * from tb_order_item join tb_menu on tb_order_item.menu_num= "+num+"";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_order_item toi = new tb_order_item();
				toi.setId(rs.getInt("id"));
				toi.setMenu_num(rs.getString("menu_num"));
				toi.setMenuname(rs.getString("name"));
				toi.setUnit(rs.getString("unit"));
				toi.setAmount(rs.getInt("amount"));
				toi.setTotal(rs.getInt("total"));
				toi.setOrder_for_num(rs.getString("order_for_num"));
				toi.setUnit_price(rs.getInt("unit_price"));
				list.add(toi);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	//查询菜品order_num
	public static List<tb_order_item> selectItemByOrder_Num(String order_for_num){
		List<tb_order_item> list =new ArrayList<tb_order_item>();
		String sql="select distinct(id),order_for_num,menu_num,amount,total ,name,unit,unit_price from tb_order_item join tb_menu on tb_order_item.menu_num= tb_menu.num and tb_order_item.order_for_num='"+order_for_num+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_order_item toi = new tb_order_item();
				toi.setId(rs.getInt("id"));
				toi.setMenu_num(rs.getString("menu_num"));
				toi.setMenuname(rs.getString("name"));
				toi.setUnit(rs.getString("unit"));
				toi.setAmount(rs.getInt("amount"));
				toi.setTotal(rs.getInt("total"));
				toi.setOrder_for_num(rs.getString("order_for_num"));
				toi.setUnit_price(rs.getInt("unit_price"));
				list.add(toi);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;	
	}
	//新开台点菜插入
	public static int insertItem(String order_for_num,String menu_num,Integer amount,Integer total){
		int i =0;
		try {
			String sql ="insert into tb_order_item (order_for_num,menu_num, amount,total)values('"+order_for_num+"','"+menu_num+"',"+amount+","+total+")";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//删除点菜
	public static int deleteItem(String menunum,String Order_num){
		int i=0;
		try {
			String sql ="delete from tb_order_item where menu_num='"+menunum+"' and order_for_num='"+Order_num+"'";
			i= Dao.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
