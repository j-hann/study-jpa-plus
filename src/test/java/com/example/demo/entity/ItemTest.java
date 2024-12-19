package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.demo.config.JpaConfig;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)// DataSource 변경 X
@Import(JpaConfig.class)
class ItemTest {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("status 값에 null이 들어가지 않는지 제약조건 테스트")
	void checkStatusIsNotNull(){
		//given - 테스트 준비
		User owner = new User("user","abcdef2@gmail.com", "오너", "a1234567!");
		User manager = new User("user","abcdef3@gmail.com", "매니저", "a1234567!");

		userRepository.save(owner);
		userRepository.save(manager);

		//when - 테스트 진행
		Item item = new Item("아이템", "아이템 설명", manager, owner);

		//then - 테스트 결과 검증
//		assertThrows(예상하는 예외처리 클래스, 예외처리가 터질 로직)
		assertThrows(ConstraintViolationException.class, () -> {
			//save 사용
//			itemRepository.save(item);

			//saveAndFlush 사용 -> DB에 바로 즉시 저장됨
			itemRepository.saveAndFlush(item);

		});

	}



}
