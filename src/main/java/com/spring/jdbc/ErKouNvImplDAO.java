package com.spring.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ErKouNvImplDAO implements ErKouNvDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public List<ErKouNv> queryErKouNv() throws Exception {
		// TODO Auto-generated method stub
		Connection conn = dataSource.getConnection();
		
		String sql = "Select e.play_Id, e.play_Kill, e.play_Name from erkounv e";
		Statement smt = conn.createStatement();
		
		ResultSet rs = smt.executeQuery(sql);
		
		List<ErKouNv> list = new ArrayList<ErKouNv>();
		
		/*
		 * ResultSetMetaData rsmd = rs.getMetaData();
		 * 遍历列名即字段名
		for (int column = 1; column < rsmd.getColumnCount(); column++){
			System.out.println(rsmd.getColumnName(column));
		}*/
		
		while (rs.next()){
			Long playId = rs.getLong("play_id");
			String playKill = rs.getString("play_Kill");
			String playName = rs.getString("play_Name");
			ErKouNv ekn = new ErKouNv(playId, playKill, playName);
			list.add(ekn);
		}
		return list;
	}

}
