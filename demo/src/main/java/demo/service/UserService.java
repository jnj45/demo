package demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import demo.domain.User;
import demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	
	public Optional<User> selectUserDetails(String userId) {
		return userMapper.findById(userId);
	}
	
}
