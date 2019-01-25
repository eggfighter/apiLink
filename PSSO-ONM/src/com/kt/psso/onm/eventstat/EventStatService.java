package com.kt.psso.onm.eventstat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.EventStatDao;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

@Service
public class EventStatService {

	/**
	 * @uml.property  name="eventStatDao"
	 * @uml.associationEnd  
	 */
	private EventStatDao eventStatDao;

	public List<EventStatVo> listByTime(EventStatSearchVo searchVo) {
		String period	= searchVo.getpPeriod();
		if (period == null || "".equals(period)) {
			return new ArrayList<EventStatVo>();
		}

		if ("HOUR".equals(period)) {
			return eventStatDao.listEventStatHour(searchVo);
		}
		if ("DAY".equals(period)) {
			return eventStatDao.listEventStatDay(searchVo);
		}
		if ("MONTH".equals(period)) {
			return eventStatDao.listEventStatMonth(searchVo);
		}
		
		return new ArrayList<EventStatVo>();
	}

	public int totalByTime(EventStatSearchVo searchVo) {
		String period	= searchVo.getpPeriod();
		if (period == null || "".equals(period)) {
			return 0;
		}

		if ("HOUR".equals(period)) {
			return eventStatDao.totalEventStatHour(searchVo);
		}
		if ("DAY".equals(period)) {
			return eventStatDao.totalEventStatDay(searchVo);
		}
		if ("MONTH".equals(period)) {
			return eventStatDao.totalEventStatMonth(searchVo);
		}

		return 0;
	}

	public List<EventStatVo> listBySite(EventStatSearchVo searchVo) {
		String period	= searchVo.getpPeriod();
		if (period == null || "".equals(period)) {
			return new ArrayList<EventStatVo>();
		}

		return eventStatDao.listEventStatBySite(searchVo);
	}
	/*
	public List<EventStatVo> listBySiteExcel(EventStatSearchVo searchVo) {
	    String period = searchVo.getpPeriod();
	    if ((period == null) || ("".equals(period))) {
	      return new ArrayList();
	    }

	    return this.eventStatDao.listEventStatBySiteExcel(searchVo);
	  }*/


	public int totalBySite(EventStatSearchVo searchVo) {
		String period	= searchVo.getpPeriod();
		if (period == null || "".equals(period)) {
			return 0;
		}

		return eventStatDao.totalEventStatBySite(searchVo);
	}

	public String[][] getYearArr() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		int now = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 2011; i <= now; i++) {
			String str = String.valueOf(i);
			list.add(new String[] {str, str});
		}
		return list.toArray(new String[0][0]);
	}

	public String[][] getMonthArr() {
		return new String[][] {
				{"01", "01"},
				{"02", "02"},
				{"03", "03"},
				{"04", "04"},
				{"05", "05"},
				{"06", "06"},
				{"07", "07"},
				{"08", "08"},
				{"09", "09"},
				{"10", "10"},
				{"11", "11"},
				{"12", "12"}
			};
	}

	public String[][] getDayArr() {
		return new String[][] {
				{"01", "01"},
				{"02", "02"},
				{"03", "03"},
				{"04", "04"},
				{"05", "05"},
				{"06", "06"},
				{"07", "07"},
				{"08", "08"},
				{"09", "09"},
				{"10", "10"},
				{"11", "11"},
				{"12", "12"},
				{"13", "13"},
				{"14", "14"},
				{"15", "15"},
				{"16", "16"},
				{"17", "17"},
				{"18", "18"},
				{"19", "19"},
				{"20", "20"},
				{"21", "21"},
				{"22", "22"},
				{"23", "23"},
				{"24", "24"},
				{"25", "25"},
				{"26", "26"},
				{"27", "27"},
				{"28", "28"},
				{"29", "29"},
				{"30", "30"},
				{"31", "31"}
			};
	}

	public String getEventTypeCondition(EventStatSearchVo searchVo) {
		String eventType = searchVo.getpEvent_type();
		if ("LOGIN".equals(eventType))
			return "로그인";
		if ("JOIN".equals(eventType))
			return "회원가입";
		if ("WITHDRAW".equals(eventType))
			return "회원탈퇴";
		if ("UPDATE".equals(eventType))
			return "정보변경";
		if ("KONG".equals(eventType))
			return "공인인증서";
		if ("SIN".equals(eventType))
			return "신용카드";
		if ("EMAIL".equals(eventType))
			return "이메일";
		if ("IPIN".equals(eventType))
			return "아이핀";
		if ("TEL".equals(eventType))
			return "핸드폰";
		
		return null;
	}

	public String getPeriodCondition(EventStatSearchVo searchVo) {
		String period	= searchVo.getpPeriod();
		
		if ("HOUR".equals(period))
			return "시간별";
		if ("DAY".equals(period))
			return "일별";
		if ("MONTH".equals(period))
			return "월별";
		return null;
	}

	public String getDateCondition(EventStatSearchVo searchVo) {
		String period	= searchVo.getpPeriod();
		String year		= searchVo.getpYear();
		String month	= searchVo.getpMonth();
		String day		= searchVo.getpDay();
		String year2	= searchVo.getpYear2();
		String month2	= searchVo.getpMonth2();
		String day2		= searchVo.getpDay2();

		if (period == null)
			return null;

		if ("MONTH".equals(period)) {
			return year + "." + month + " ~ " + year2 + "." + month2;
		} else {
			return year + "." + month + "." + day + " ~ " + year2 + "." + month2 + "." + day2;
		}
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @param eventStatDao
	 * @uml.property  name="eventStatDao"
	 */
	@Autowired
	public void setEventStatDao(EventStatDao eventStatDao) {
		this.eventStatDao = eventStatDao;
	}

}
