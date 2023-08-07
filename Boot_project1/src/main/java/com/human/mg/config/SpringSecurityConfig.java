package com.human.mg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.human.mg.service.MgService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/").permitAll()
		.requestMatchers("/join", "/insertOk","/update","/updateOk","/deleteOk","/main","redirect:/main","/login","/error","/board/**","/idCheck").permitAll()
		.requestMatchers("/board/boardlist/").permitAll()
		.requestMatchers("/board/delete/**").permitAll()
		.requestMatchers("/board/deleteOk").permitAll()
		.requestMatchers("/mboard/deleteOk").permitAll()
		.requestMatchers("/mboard/delete/**").permitAll()
		.requestMatchers("/mboard/list/**").permitAll()		
		.requestMatchers("/mboard/insert/**").permitAll()		
		.requestMatchers("/mboard/insertOk/**").permitAll()		
		.requestMatchers("/mboard/update/**").permitAll()		
		.requestMatchers("/mboard/updateOk/**").permitAll()		
		.requestMatchers("/mboard/view/**").permitAll()
		.requestMatchers("/hboard/deleteOk").permitAll()
		.requestMatchers("/hboard/delete/**").permitAll()
		.requestMatchers("/hboard/list/**").permitAll()		
		.requestMatchers("/hboard/insert/**").permitAll()		
		.requestMatchers("/hboard/insertOk/**").permitAll()		
		.requestMatchers("/hboard/update/**").permitAll()		
		.requestMatchers("/hboard/updateOk/**").permitAll()		
		.requestMatchers("/hboard/view/**").permitAll()				
		.requestMatchers("/css/**", "/js/**", "/img/**","/upload/**","/api/**").permitAll() 
		.requestMatchers("/list/**","/view").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(new LoginSuccessHandler())
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/main")
		.permitAll();

		return http.build();
	}
	
	
	/*
.csrf().disable(): CSRF(Cross-Site Request Forgery) 공격 방지 기능을 비활성화합니다. 이는 보안 설정을 단순화하거나, CSRF 토큰을 사용하지 않는 특별한 상황에서만 사용되어야 합니다. 보안상의 이유로 기본적으로 활성화하는 것이 좋습니다.

.authorizeRequests(): HTTP 요청에 대한 접근 권한을 설정합니다.

.requestMatchers().permitAll(): 특정 요청 경로들에 대해 모든 사용자에게 접근 권한을 부여합니다. 여기에서는 "/join", "/joinOk", "/update", "/updateOk", "/deleteOk", "/main" 경로들에 대해 모든 사용자가 접근할 수 있도록 허용합니다.

.requestMatchers("/list/**").hasRole("admin"): "/list/**" 경로에 대해서는 "admin" 역할을 가진 사용자만 접근할 수 있도록 설정합니다. 이러한 경로들은 관리자 기능을 수행하는 데 사용될 것으로 보입니다.

.anyRequest().authenticated(): 이외의 모든 요청은 인증된 사용자만 접근할 수 있도록 설정합니다. 인증되지 않은 사용자는 로그인 페이지로 리다이렉트됩니다.

.formLogin(): 사용자가 폼 기반 로그인을 사용하여 로그인할 수 있도록 설정합니다.

.loginPage("/login"): 로그인 페이지의 경로를 "/login"으로 설정합니다.

.usernameParameter("username"): 로그인 시 사용되는 사용자 이름(parameter)을 "username"으로 설정합니다.

.passwordParameter("password"): 로그인 시 사용되는 비밀번호(parameter)를 "password"로 설정합니다.

.successHandler(new LoginSuccessHandler()): 로그인 성공 후에 사용자 지정 로그인 성공 핸들러(LoginSuccessHandler)를 호출합니다. 로그인 성공 시 추가적인 작업을 처리할 수 있습니다.

.logout(): 사용자 로그아웃 설정을 구성합니다.

.invalidateHttpSession(true): 로그아웃 시 현재 HttpSession을 무효화합니다. 이렇게 함으로써 기존 세션 정보가 남아있는 것을 방지합니다.

.logoutSuccessUrl("/main"): 로그아웃 성공 후에 리다이렉트될 경로를 "/main"으로 설정합니다.
	 * */
	
	// 4. 시큐리티에서 현재의 VO를 사용하려면 
	//    여기에서 회원 정보를 가져와 인증 영역에 정보를 저장할 서비스를 등록해 준다.
	//    등록해주는 서비스는 UserDetailsService를 구현한 서비스이어야 한다.
	
	@Autowired
	private MgService mgService;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mgService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
