package com.DM.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DM.model.tb_sort;

public class SortDao {
	//查询所有菜品
	public static List<tb_sort> selectSort(){
		List<tb_sort> list = new ArrayList<tb_sort>();
		String sql ="select * from tb_sort";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_sort ts = new tb_sort();
				ts.setId(rs.getInt("id"));
				ts.setName(rs.getString("sortname"));
				list.add(ts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//按照指定编号搜索 判断是否存在该编号
	public static List<tb_sort> selectSortByID(int id){
		List<tb_sort> list = new ArrayList<tb_sort>();
		String sql ="select * from tb_sort where id="+id+"";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_sort ts = new tb_sort();
				ts.setId(rs.getInt("id"));
				ts.setName(rs.getString("sortname"));
				list.add(ts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//添加菜系
	public static int  insertSort(Integer id,String sortname){
		int i=0;
		try {
			String sql = "insert into tb_sort values("+id+",'"+sortname+"')";
			i=Dao.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	//删除菜系
	public static int deleteSort(Integer id, String sortname){
		int i=0;
		try {
			String sql ="delete from tb_sort where id="+id+"";
			i=Dao.executeUpdate(sql);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
