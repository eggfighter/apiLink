package com.kt.psso.onm.admin.member;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class MemberValidatorTest {

	private MemberVo memberVo;

	@Before
	public void setUp() {
		this.memberVo = new MemberVo();
		memberVo.setUserId("abcd1234");
		memberVo.setUserPswd("1324qewr");
		memberVo.setUserName("홍길동");
		memberVo.setTelNo("01012341234");
		memberVo.setEmail("psso@trutec.co.kr");
		memberVo.setUserHost("*");
	}

	@Test
	public void testValidate() {
		BeanPropertyBindingResult errors = validate(memberVo);
		assertEquals(0, errors.getAllErrors().size());
	}

	private BeanPropertyBindingResult validate(MemberVo memberVo) {
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(memberVo, "memberVo");
		new MemberValidator().validate(memberVo, errors);

//		printErrors(errors);

		return errors;
	}

	private void printErrors(BeanPropertyBindingResult errors) {
		for (ObjectError error : errors.getAllErrors()) {
			System.out.println("error = " + error);
		}

		FieldError fieldError = errors.getFieldError();
		if (fieldError != null) System.out.println("fieldError = " + fieldError);
	}

	@Test
	public void testValidate1() {
//		6~16자 영문소문자와 숫자를 조합
//		아이디, 연락처, 이름과 달라야함
//		연속문자(123.., abc..등) 불가
//		동일한 문자(aaa.., 111..등) 불가
		memberVo.setUserPswd("1qaz2");
		assertEquals(1, validate(memberVo).getAllErrors().size());
		memberVo.setUserPswd("1qaz123");
		assertEquals(1, validate(memberVo).getAllErrors().size());
		memberVo.setUserPswd("1qazabc");
		assertEquals(1, validate(memberVo).getAllErrors().size());
		memberVo.setUserPswd("1qaz111");
		assertEquals(1, validate(memberVo).getAllErrors().size());
		memberVo.setUserPswd("1qazaaa");
		assertEquals(1, validate(memberVo).getAllErrors().size());
	}

	@Test
	public void testVlidate2() {
		memberVo.setEmail("aaaa");
		assertEquals(1, validate(memberVo).getAllErrors().size());
		memberVo.setEmail("abc@abc");
		assertEquals(1, validate(memberVo).getAllErrors().size());
		memberVo.setEmail("123@abc.com");
		assertEquals(1, validate(memberVo).getAllErrors().size());
	}

	@Test
	public void testVlidate3() {
		memberVo.setUserPswd(null);
		assertEquals(0, validate(memberVo).getAllErrors().size());
		memberVo.setUserPswd("");
		assertEquals(0, validate(memberVo).getAllErrors().size());
	}

}
