package com.kt.psso.onm.restore;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kt.psso.onm.common.SearchVo;

public class RestoreValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RestoreVo.class.equals(clazz) || SearchVo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof RestoreVo)) {
			return;
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cn", "cn.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "reason.empty", "필수 파라미터 값이 존재하지 않음.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "act", "act.empty", "필수 파라미터 값이 존재하지 않음.");
		
		RestoreVo restoreVo = (RestoreVo) target;
		String act = restoreVo.getAct();
		if (act.equals("2") && "Y".equals(restoreVo.getKpm())) {
			errors.rejectValue("kpm", "kpm.delete", "KPM 가입자의 경우 KPM 탈퇴 후 삭제 가능합니다");
		}
	}

}
