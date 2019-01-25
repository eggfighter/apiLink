package com.kt.psso.onm.custom14;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kt.psso.onm.common.SearchVo;

public class Custom14Validator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Custom14Vo.class.equals(clazz) || SearchVo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof Custom14Vo)) {
			return;
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cn", "cn.empty", "필수 파라미터 값이 존재하지 않음.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldCn", "oldCn.empty", "필수 파라미터 값이 존재하지 않음.");
	}

}
