package in.dminc.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/*
 * For User 'admin' : 
 * 1. Able to access /admin page.
 * 2. Unable to access /user page, redirect to /403 access denied page.
 * 
 * For User 'user' : 
 * 1. Able to access /user page.
 * 2. Unable to access /admin page, redirect to /403 access denied page.
 *  
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/","/home","/about").permitAll()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER")
			.anyRequest().authenticated()
		.and()
		.formLogin()
//			.loginPage("/login")
//			.permitAll()
			.defaultSuccessUrl("/home", true)
		.and()
		.logout()
			.logoutSuccessUrl("/")
			.permitAll()
		.and()
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	//create two users, admin and user.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN")
			.and()
			.withUser("user").password(passwordEncoder.encode("password")).roles("USER");
	}
}
