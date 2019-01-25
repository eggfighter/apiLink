package com.kt.psso.onm.authcheck;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kt.psso.onm.common.SearchVo;

public class AuthCheckValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SearchVo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof SearchVo)) {
			return;
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pDateFlag", "pDateFlag.empty", "필수 파라미터 값이 존재하지 않음.");
	}

}
