package com.kt.psso.onm.eventstat;

import java.util.Calendar;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventStatValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EventStatSearchVo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof EventStatSearchVo)) {
			return;
		}
		EventStatSearchVo searchVo = (EventStatSearchVo) target;

		String pPeriod = searchVo.getpPeriod();
		// pPeriod가 없으면 그냥 패스, 페이지에 처음 들어온 것으로 본다.
		if (pPeriod == null) {
			return;
		}

		// 필수 값
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pEvent_type", "pEvent_type.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pPeriod", "pPeriod.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pYear", "pYear.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pMonth", "pMonth.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pDay", "pDay.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pYear2", "pYear2.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pMonth2", "pMonth2.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pDay2", "pDay2.empty", "필수 파라미터 값이 존재하지 않음.");
		
		int year	= Integer.parseInt(searchVo.getpYear());
		int month	= Integer.parseInt(searchVo.getpMonth());
		int day		= Integer.parseInt(searchVo.getpDay());
		int year2	= Integer.parseInt(searchVo.getpYear2());
		int month2	= Integer.parseInt(searchVo.getpMonth2());
		int day2	= Integer.parseInt(searchVo.getpDay2());

		Calendar begin = Calendar.getInstance();
		begin.set(year, month - 1, day);
		Calendar end = Calendar.getInstance();
		end.set(year2, month2 - 1, day2);

		long gap = end.getTimeInMillis() - begin.getTimeInMillis();
		
		if ("HOUR".equals(pPeriod) && gap > 24L * 60 * 60 * 1000) {
			// 24시간
			errors.rejectValue("pPeriod", "pPeriod.overflow", "검색가능기간을 초과하였습니다.");
		}
		if ("DAY".equals(pPeriod) && gap > 31L * 24 * 60 * 60 * 1000) {
			// 31일
			errors.rejectValue("pPeriod", "pPeriod.overflow", "검색가능기간을 초과하였습니다.");
		}
		if ("MONTH".equals(pPeriod) && gap > 12L * 31 * 24 * 60 * 60 * 1000) {
			// 대략 12개월
			errors.rejectValue("pPeriod", "pPeriod.overflow", "검색가능기간을 초과하였습니다.");
		}
		if ("SITE".equals(pPeriod) && gap > 31L * 24 * 60 * 60 * 1000) {
			// 31일
			errors.rejectValue("pPeriod", "pPeriod.overflow", "검색가능기간을 초과하였습니다.");
		}

	}

}
