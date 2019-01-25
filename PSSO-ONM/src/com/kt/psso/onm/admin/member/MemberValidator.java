package com.kt.psso.onm.admin.member;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.util.PagingUtil;
import com.kt.psso.onm.util.StringUtil;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVo.class.equals(clazz) || SearchVo.class.equals(clazz) || PagingUtil.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof MemberVo)) {
			return;
		}
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "userId.empty", "아이디를 입력하세요");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.empty", "이름을 입력하세요");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telNo", "telNo.empty", "연락처를 입력하세요");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "이메일을 입력하세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userHost", "userHost.empty", "접근아이피를 입력하세요");
		
		MemberVo memberVo = (MemberVo) target;
		String userId = memberVo.getUserId();
		/*
		if (!StringUtil.checkEnglishWithNumber(userId)) {
			errors.rejectValue("userId", "userId.engNum", "아이디는 영문소문자/숫자로 입력하세요.");
			return;
		}
		int length = userId.getBytes().length;
		if (length < 3 || length > 15) {
			errors.rejectValue("userId", "userId.length", "아이디는 3~15자 이내로 입력하세요.");
			return;
		}
		if (!StringUtil.checkWeakId(userId)) {
			errors.rejectValue("userId", "userId.weakId", "취약한 ID 입니다. 다른 ID를 입력하세요.");
			return;
		}
		if (memberVo.getUserId() == null && memberVo.getUserPswd() == null) {
			errors.rejectValue("userPswd", "userPswd.empty", "비밀번호를 입력하세요");
			return;
		}
		if (memberVo.getUserPswd() != null && !"".equals(memberVo.getUserPswd())) {
			String userPswd = memberVo.getUserPswd();
			if (!StringUtil.checkPswd(userPswd)) {
				errors.rejectValue("userPswd", "userPswd.engNumSpe", "비밀번호는 영문/숫자/특수문자 조합으로 입력하세요.");
				return;
			}
			if (userPswd.length() < 8) {
				errors.rejectValue("userPswd", "userPswd.length", "비밀번호는 8자 이상으로 입력하세요.");
				return;
			}
			if (userPswd.equals(memberVo.getUserId())) {
				errors.rejectValue("userPswd", "userPswd.dup", "비밀번호를 아이디와 다르게 입력하세요.");
				return;
			}
			if (userPswd.equals(memberVo.getTelNo())) {
				errors.rejectValue("userPswd", "userPswd.dup", "비밀번호를 연락처와 다르게 입력하세요.");
				return;
			}
			if (userPswd.equals(memberVo.getUserName())) {
				errors.rejectValue("userPswd", "userPswd.dup", "비밀번호를 이름과 다르게 입력하세요.");
				return;
			}
			if (StringUtil.contain123Abc(userPswd)) {
				errors.rejectValue("userPswd", "userPswd.123", "비밀번호에 연속된 문자는 허용되지 않습니다.");
				return;
			}
			if (StringUtil.conta111Aaa(userPswd)) {
				errors.rejectValue("userPswd", "userPswd.123", "비밀번호에 연속된 동일한 문자는 허용되지 않습니다.");
				return;
			}
		}
		if (memberVo.getUserName().getBytes().length > 30) {
			errors.rejectValue("userName", "userName.length", "이름은 30 바이트 이내로 입력하세요.");
			return;
		}
		if (memberVo.getTelNo().getBytes().length > 20) {
			errors.rejectValue("telNo", "telNo.length", "연락처는 20 바이트 이내로 입력하세요.");
			return;
		}
		if (!StringUtil.checkEmailFormat(memberVo.getEmail())) {
			errors.rejectValue("email", "email.format", "이메일을 형식에 맞게 입력하세요.");
			return;
		}
		if (memberVo.getEmail().getBytes().length > 100) {
			errors.rejectValue("email", "email.length", "이메일은 100 바이트 이내로 입력하세요.");
			return;
		}
		if (memberVo.getAddress() != null && memberVo.getAddress().getBytes().length > 200) {
			errors.rejectValue("address", "address.length", "주소는 200 바이트 이내로 입력하세요.");
			return;
		}
		if (memberVo.getUserDesc() != null && memberVo.getUserDesc().getBytes().length > 2000) {
			errors.rejectValue("userDesc", "userDesc.length", "설명은 2000 바이트 이내로 입력하세요.");
			return;
		}*/
		if (memberVo.getUserHost().getBytes().length > 40) {
			errors.rejectValue("userHost", "userHost.length", "접근아이피는 40 바이트 이내로 입력하세요.");
			return;
		}
	}

}
