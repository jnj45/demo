package demo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthenticationProvider를 구현한 클래스
 * 빈으로 등록해 놓으면 spring security 프레임웤이 인증 처리 시 자동으로 호출함.
 * @author ecbank
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

		if (userDetails == null) {
			log.info(username + ": 존재하지 않는 사용자 입니다.");
			throw new BadCredentialsException("UNKNOWN_USER");
		}

		boolean isMatch = passwordEncoder.matches(password, userDetails.getPassword());

		if (!isMatch) {
			log.info("비밀번호가 일치하지 않습니다.");
			throw new BadCredentialsException("DONT_MATCH");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}