package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
    	List<BusinessCard> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    		String sql = "select * from BusinessCard where name like '%";
    		sql = new String(sql+keyword+"%'");
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			String name = rs.getString(1);
    			String phone =rs.getString(2);
    			String companyName = rs.getString(3);
    			Date createDate = rs.getDate(4);
    			BusinessCard bc = new BusinessCard(name, phone, companyName);
    			bc.setCreateDate(createDate);
    			list.add(bc);
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    	return list;
    }

    public int addBusinessCard(BusinessCard businessCard){
	// 구현하세요.
    	int result = 0;
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    		String sql = "insert into BusinessCard values (?,?,?,?)";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, businessCard.getName());
    		ps.setString(2, businessCard.getPhone());
    		ps.setString(3, businessCard.getCompanyName());
    		ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
    		result = ps.executeUpdate();
    	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    	return result;
    }
}
