
import java.util.Date;
import java.util.List;

import org.edwith.webbe.cardmanager.dao.BusinessCardManagerDao;
import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.ui.CardManagerUI;

public class main {
	private static CardManagerUI cm = CardManagerUI.getInstance();
	private static BusinessCardManagerDao dao = new BusinessCardManagerDao();
	
	public static void main(String[] args) {		
		while(true) {
			cm.printMainMenu();
			int n = cm.getMenuNumber();
			
			if(n==1) { // 명함 추가
				BusinessCard bc = cm.inputBusinessCard();
				bc.setCreateDate(new Date());
				dao.addBusinessCard(bc);
			} else if(n==2) { // 명함 검색
				String s = cm.getSearchKeyword();
				List<BusinessCard> cards = dao.searchBusinessCard(s);
				for(BusinessCard c : cards)
					System.out.println(c);
			} else if(n==3) { // 종료
				cm.printExitMessage();
				break;
			} else {
				cm.printErrorMessage();
			}
			
		}
		
	}

}
