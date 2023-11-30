package demo.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import demo.domain.User;

@Mapper
public interface UserMapper {
	
	public Optional<User> findById(String id);
}
