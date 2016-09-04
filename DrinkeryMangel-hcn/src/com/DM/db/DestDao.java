package com.DM.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DM.model.tb_dest;

public class DestDao {
	//查询所有台号信息
	public static List<tb_dest> selectDesk(){
		List<tb_dest>list = new ArrayList<tb_dest>();
		String sql ="select * from tb_dest";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_dest td = new tb_dest();
				td.setNum(rs.getString("num"));
				td.setSeating(rs.getInt("seating"));
				list.add(td);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	//查询指定台号信息
	public static List<tb_dest> selectDeskByNum(String num){
		List<tb_dest>list = new ArrayList<tb_dest>();
		String sql ="select * from tb_dest where num ='"+num+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				tb_dest td = new tb_dest();
				td.setNum(rs.getString("num"));
				td.setSeating(rs.getInt("seating"));
				list.add(td);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	//添加台号信息
	public static int insertDest(String num,int seating){
		int i=0;
		try {
			String sql ="insert into tb_dest values('"+num+"',"+seating+")";
			i=Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//删除台号信息
	public static int deleteDest(String num,int seating){
		int i=0;
		try {
			String sql ="delete from tb_dest where num='"+num+"'";
			i= Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
