package org.edwith.webbe.cardmanage.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.springframework.jdbc.core.RowMapper;


public class CardRowMapper implements RowMapper<BusinessCard>{

	@Override
	public BusinessCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BusinessCard card = new BusinessCard(
				rs.getString("name"),
				rs.getString("phone"),
				rs.getString("companyName"));
		card.setCreateDate(rs.getDate("createDate"));
		return card;		
	}

}
