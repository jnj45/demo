package demo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				requests -> requests.requestMatchers("/", "/home").permitAll()
									.anyRequest().authenticated()
			)
			.formLogin(
				form -> form.loginPage("/login").permitAll()
//							.usernameParameter("userId")				// 아이디 파라미터명 설정, default: username
//						    .loginProcessingUrl("/loginAction") 		// 로그인 Form Action Url, default: /login
//						    .defaultSuccessUrl("/")						// 로그인 성공 후 이동 페이지
						    .successHandler(                            // 로그인 성공 후 핸들러
						    		(request, response, authentication) -> {
						    			//인증 성공 후 접속하려던 페이지로 이동하는 예제.
						    			String redirectUrl = new HttpSessionRequestCache().getRequest(request, response).getRedirectUrl();
						    			response.sendRedirect(StringUtils.defaultIfEmpty(redirectUrl,"/"));
						    		}
						     ) 	
//						    .failureHandler(customLoginFailHandler)		// 로그인 실패 후 핸들러(lambda)
			)
			.logout(
				logout -> logout.permitAll()
			);

		return http.build();
	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
	  /**
	   * AuthenticationProvider에서 필요한 passwordEncoder 빈.
	   * @return
	   */
	  @Bean 
	  BCryptPasswordEncoder bCryptPasswordEncoder() { 
		  return new BCryptPasswordEncoder();
	  }
}
