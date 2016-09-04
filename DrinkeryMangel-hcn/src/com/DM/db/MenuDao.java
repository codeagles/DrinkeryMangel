package com.DM.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DM.model.tb_menu;
import com.DM.model.tb_sort;

public class MenuDao {
	//查询所有菜品（菜单）
	public static List<tb_menu> selectMenu(){
		List<tb_menu> list = new ArrayList<tb_menu>();
		String sql ="select num,sort_id,name,code,unit,unit_price,id ,sortname from tb_menu join tb_sort on tb_menu.sort_id=tb_sort.id";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_menu tm = new tb_menu();
				tm.setSort_name(rs.getString("sortname"));
				tm.setNum(rs.getString("num"));
				tm.setName(rs.getString("name"));
				tm.setCode(rs.getString("code"));
				tm.setUnit(rs.getString("unit"));
				tm.setUnit_price(rs.getInt("unit_price"));
				list.add(tm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//查询指定编号菜品
	public static List<tb_menu> selectMenuByNum(String num ){
		List<tb_menu> list = new ArrayList<tb_menu>();
		String sql ="select num,sort_id,name,code,unit,unit_price,sortname from tb_menu join tb_sort on num ='"+num+"'and tb_menu.sort_id=tb_sort.id";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_menu tm = new tb_menu();
				tm.setNum(rs.getString("num"));
				tm.setSort_id(rs.getInt("sort_id"));
				tm.setName(rs.getString("name"));
				tm.setCode(rs.getString("code"));
				tm.setUnit(rs.getString("unit"));
				tm.setUnit_price(rs.getInt("unit_price"));
				tm.setSort_name(rs.getString("sortname"));
				list.add(tm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//查询助记码菜品
	public static List<tb_menu> selectMenuByCode(String code ){
		List<tb_menu> list = new ArrayList<tb_menu>();
		String sql ="select num,sort_id,name,code,unit,unit_price,sortname from tb_menu join tb_sort on code like '%"+code+"%'and tb_menu.sort_id=tb_sort.id";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_menu tm = new tb_menu();
				tm.setNum(rs.getString("num"));
				tm.setSort_id(rs.getInt("sort_id"));
				tm.setName(rs.getString("name"));
				tm.setCode(rs.getString("code"));
				tm.setUnit(rs.getString("unit"));
				tm.setUnit_price(rs.getInt("unit_price"));
				tm.setSort_name(rs.getString("sortname"));
				list.add(tm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//添加菜品
	public static int insertMenu(String num ,String sortname,String name,String code,String unit,Integer unit_price ){
		int sortid=0,i=0;
		
		try {
			String sql="select  * from tb_sort where sortname='"+sortname+"' ";
			ResultSet rs =Dao.executeQuery(sql);
			try {
				while(rs.next()){
					tb_sort ts = new tb_sort();
					sortid=rs.getInt("id");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			String sql1 ="insert into tb_menu values('"+num+"',"+sortid+",'"+name+"','"+code+"','"+unit+"',"+unit_price+")";
			i =Dao.executeUpdate(sql1);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Dao.close();
		return i;	
	}
	//修改菜品
	public static int updatetMenu(String num ,String sortname,String name,String code,String unit,Integer unit_price,String tablenum ){
		int sortid=0,i=0;
		
		try {
			String sql="select  * from tb_sort where sortname='"+sortname+"' ";
			ResultSet rs =Dao.executeQuery(sql);
			try {
				while(rs.next()){
					tb_sort ts = new tb_sort();
					sortid=rs.getInt("id");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			String sql1 ="update tb_menu set num='"+num+"',sort_id="+sortid+",name='"+name+"',code='"+code+"',unit='"+unit+"',unit_price="+unit_price+"where num='"+tablenum+"'";
			i =Dao.executeUpdate(sql1);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Dao.close();
		return i;	
	}
	//删除菜品
	public static int deleteMenu(String num){
		int i=0;
		try {
			String sql ="delete from tb_menu where num='"+num+"'";
			i=Dao.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	
}
