package com.example.demo.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PasswordEncoderTest {

	@Test
	@DisplayName("PasswordEncoder 암호화 테스트")
	void passwordEncodeAndMatches(){
		//given - 테스트 준비
		String rawPassword = "a1234567!";

		//when - 테스트 진행
		String encodedPassword = PasswordEncoder.encode(rawPassword);

		//then - 테스트 결과 검증
		//제대로 암호화 됐는지 확인
		assertNotEquals(rawPassword, encodedPassword);
		//암호화 매치 테스트
		assertTrue(PasswordEncoder.matches(rawPassword, encodedPassword));
	}

}
