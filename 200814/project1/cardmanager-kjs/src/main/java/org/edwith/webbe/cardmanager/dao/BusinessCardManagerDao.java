package org.edwith.webbe.cardmanager.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.edwith.webbe.cardmanage.sql.CardRowMapper;
import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;

public class BusinessCardManagerDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public BusinessCardManagerDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
    	List<BusinessCard> list = null;
    		list = jdbcTemplate.query(
        			"select * from card",
        			new CardRowMapper()
        			);
        	return list;
    	
    	
    	
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// 구현하세요.
    	jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pstmt = con.prepareStatement("insert into card (name, phone, companyname, createdate) values (?, ?, ?, ?)");
				//인덱스 파라미터 값 설정
				pstmt.setString(1, businessCard.getName());
				pstmt.setString(2, businessCard.getPhone());
				pstmt.setString(3, businessCard.getCompanyName());
				pstmt.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));

				return pstmt;
			}
			
		});
    	
    	return businessCard;
    }
}
