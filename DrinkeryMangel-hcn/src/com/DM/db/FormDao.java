package com.DM.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DM.model.tb_order_form;

public class FormDao {
	//查询开台信息
	public static List<tb_order_form> selectForm(){
		 List<tb_order_form> list = new ArrayList<tb_order_form>();
		 String sql="select * from tb_order_form where money=0";
		 ResultSet rs =Dao.executeQuery(sql);
		 try {
			while(rs.next()){
				tb_order_form tof = new tb_order_form();
				tof.setNum(rs.getString("num"));
				tof.setDesk_num(rs.getString("desk_num"));
				tof.setDatetime(rs.getString("datetime"));
				tof.setMoney(rs.getDouble("money"));
				tof.setUser_id(rs.getInt("user_id"));
				list.add(tof);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//查询已结账的开台信息
	public static List<tb_order_form> selectFormByOver(){
		 List<tb_order_form> list = new ArrayList<tb_order_form>();
		 String sql="select * from tb_order_form where money!=0";
		 ResultSet rs =Dao.executeQuery(sql);
		 try {
			while(rs.next()){
				
				tb_order_form tof = new tb_order_form();
				tof.setNum(rs.getString("num"));
				tof.setDesk_num(rs.getString("desk_num"));
				tof.setDatetime(rs.getString("datetime"));
				tof.setMoney(rs.getDouble("money"));
				tof.setUser_id(rs.getInt("user_id"));
				list.add(tof);
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//统计查询
	public static double selectFormSum(String date){
//		 List<tb_order_form> list = new ArrayList<tb_order_form>();
		 String sql="select SUM(money) from tb_order_form  where num like '"+date+"%' ";
		 ResultSet rs =Dao.executeQuery(sql);
		 double money_sum=0;
		 try {
			while(rs.next()){
					 money_sum =rs.getDouble(1);			
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();	
		return money_sum;
	}
	//查询开台信息通过餐台号
	public static List<tb_order_form> selectFormByDeskNum(String desknum){
		 List<tb_order_form> list = new ArrayList<tb_order_form>();
		 String sql="select * from tb_order_form where desk_num='"+desknum+"' and money=0";
		 ResultSet rs =Dao.executeQuery(sql);
		 try {
			while(rs.next()){
				tb_order_form tof = new tb_order_form();
				tof.setNum(rs.getString("num"));
				tof.setDesk_num(rs.getString("desk_num"));
				tof.setDatetime(rs.getString("datetime"));
				tof.setMoney(rs.getDouble("money"));
				tof.setUser_id(rs.getInt("user_id"));
				list.add(tof);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//添加开台信息
	public static int insertForm(String num,String desk_num,String datetime,Double money,Integer user_id ){
		
		int i=0;
		try {
			String sql="insert into tb_order_form values('"+num+"','"+desk_num+"','"+datetime+"',"+money+","+user_id+")";
			i =Dao.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	//结账
	public static int updateForm(String num ,Double money ){
		int i=0;
		try {
			String sql="update tb_order_form set money="+money+" where num='"+num+"'";
			i =Dao.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//日结账报表
	public static List<tb_order_form> selectFormByDay(String date){
		 List<tb_order_form> list = new ArrayList<tb_order_form>();
		 String sql="select * from tb_order_form where num like '"+date+"%' ";
		 ResultSet rs =Dao.executeQuery(sql);
		 try {
			while(rs.next()){
				tb_order_form tof = new tb_order_form();
				tof.setNum(rs.getString("num"));
				tof.setDesk_num(rs.getString("desk_num"));
				tof.setDatetime(rs.getString("datetime"));
				tof.setMoney(rs.getDouble("money"));
				tof.setUser_id(rs.getInt("user_id"));
				list.add(tof);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//月报表统计
	public static List<tb_order_form> selectFormByMonth(String date){
		 List<tb_order_form> list = new ArrayList<tb_order_form>();
		 String sql="select  COUNT(num),sum(money) ,AVG(money),MAX(money),MIN(money) from tb_order_form  where num like '"+date+"%'";
		 ResultSet rs =Dao.executeQuery(sql);
		 try {
			while(rs.next()){
				tb_order_form s =new tb_order_form();
				s.setCount(rs.getInt(1));
				s.setMoney_sum(rs.getDouble(2));
				s.setMoney_avg(rs.getDouble(3));
				s.setMoney_max(rs.getDouble(4));
				s.setMoney_min(rs.getDouble(5));
				list.add(s);
			
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//月报表统计
	
//	public static void main(String[] args){
//		FormDao f = new FormDao();
//		f.selectFormByMonth("1501");
//	}
}
