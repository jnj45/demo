package demo.mapper;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import demo.domain.User;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {
	
	@Autowired
	UserMapper userMapper;
	
	@Test
	void test() {
		Optional<User> optionalUser = userMapper.findById("2");
		optionalUser.ifPresent(System.out::println);
		
		assertThatExceptionOfType(Exception.class)
			.isThrownBy(() -> {throw new Exception("boom");})
			.withMessage("boom2");
		
//		assertThatExceptionOfType(NoSuchElementException.class)
//			.isThrownBy(() -> optionalUser.get());
		
//		assertThatNoException().isThrownBy(() -> optionalUser.get());
//		assertThat(optionalUser.get().getId()).isEqualTo("1");
	}

}
