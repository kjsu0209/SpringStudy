package org.edwith.webbe.cardmanage.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;

public class DbQuery {
	private DataSource dataSource;
	
	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int count() {
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) from CARD")){
					rs.next();
					return rs.getInt(1);
				}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn != null)
				try {
					conn.close(); //풀에 반환
				}catch (SQLException e) {
					
				}
		}
	}
}
