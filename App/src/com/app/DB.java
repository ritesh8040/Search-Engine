package com.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.FormParam;

public class DB {
	
	public String getDataCount(String search) {
		String s = "";
		try {
			ResultSet rs = ConnectionPool.stmt
					.executeQuery("select count(*) from users where NAME like '%" + search + "%'");
			// ResultSet rs = stmt.executeQuery("SELECT * FROM (SELECT ROWNUM
			// rnum ,a.* FROM (select * from users where TYPE='"+searchType+"'
			// and TOPIC like '%"+searchKey+"%') a) WHERE rnum BETWEEN
			// "+startrow+" AND "+endrow+"");

			while (rs.next()) {
				s = s + rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			
		}
		return s;

	}    
	
	public String addPath(String path) throws Exception {
		path =Utility.getDomain(path);
		String s = "";
		try{
		ResultSet rs = ConnectionPool.stmt
				.executeQuery("select count(*) from WEBSITE where path='"+path+"'");
		int i=0;
		while (rs.next()) {
			i=Integer.parseInt(rs.getString(1));
		}
		if(i==0){
			String sql = "INSERT INTO WEBSITE(path) VALUES('" + path + "')";
			System.out.println(sql);
			ConnectionPool.stmt.executeUpdate(sql);
			s="Site Submited - "+ path;
		}else return "Site Submited";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;

	}

	public List<UserInfo> getData(String search, int startrow, int endrow) {
		List<UserInfo> list = new ArrayList<>();

		try {
			ResultSet rs = ConnectionPool.stmt.executeQuery("select * from users where NAME like '%" + search + "%'");
			// ResultSet rs = ConnectionPool.stmt.executeQuery("SELECT * FROM
			// (SELECT ROWNUM rnum ,a.* FROM (select * from users where NAME
			// like '%"+search+"%') a) WHERE rnum BETWEEN "+startrow+" AND
			// "+endrow+"");

			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setName(rs.getString(1));
				userInfo.setEmail(rs.getString(2));
				userInfo.setPassword(rs.getString(3));
				userInfo.setAbout(rs.getString(4));
				list.add(userInfo);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public void insertData(String name, String email, String type, String topic) {

		try {

			String sql = "INSERT INTO users " + "VALUES('" + name + "', '" + email + "', '" + type + "', '" + topic
					+ "')";
			System.out.println(sql);

			ConnectionPool.stmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
