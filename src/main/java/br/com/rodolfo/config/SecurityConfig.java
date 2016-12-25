package br.com.rodolfo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("ariadne").password("1234").roles("ACESSO");
	}
	
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/titulos/**", "/js/**").hasRole("ACESSO")
				.antMatchers("/css/**", "/fonts/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
				
		
	}
	
}
