package org.edwith.webbe.cardmanager;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.edwith.webbe.cardmanager.dao.BusinessCardManagerDao;
import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.ui.CardManagerUI;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CardManager {
    public static void main(String[] args){
    	DataSource ds = new DataSource();
    	ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/card_info?characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("azza0209");
		ds.setInitialSize(2);
		ds.setMaxActive(20);
		ds.setTestWhileIdle(true); //유휴 커넥션 검사
		ds.setMinEvictableIdleTimeMillis(1000*60*3); //최소 유휴 시간 3분
		ds.setTimeBetweenEvictionRunsMillis(1000*10); //10초 주기
		
        CardManagerUI cardManagerUI = CardManagerUI.getInstance();
        BusinessCardManagerDao businessCardManagerDao = new BusinessCardManagerDao(ds);

        while_loop:
        while(true) {
            cardManagerUI.printMainMenu();
            int menuNumber = cardManagerUI.getMenuNumber();
            switch(menuNumber){
                case 1 :
                    BusinessCard businessCard = cardManagerUI.inputBusinessCard();
                    businessCardManagerDao.addBusinessCard(businessCard);
                    break;
                case 2 :
                    String searchKeyword = cardManagerUI.getSearchKeyword();
                    List<BusinessCard> businessCardList = businessCardManagerDao.searchBusinessCard(searchKeyword);
                    cardManagerUI.printBusinessCards(businessCardList);
                    break;
                case 3 :
                    cardManagerUI.printExitMessage();
                    break while_loop;
                default:
                    cardManagerUI.printErrorMessage();
                    break;
            }
        }
    }
}
