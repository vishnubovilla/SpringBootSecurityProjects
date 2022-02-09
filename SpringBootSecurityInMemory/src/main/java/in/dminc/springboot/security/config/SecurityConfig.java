package in.dminc.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//{noop} --> No Operation for Password Encoder (no password encoding is needed)
		
		auth.inMemoryAuthentication().withUser("devs").password("{noop}devs").authorities("ADMIN");
		auth.inMemoryAuthentication().withUser("ns").password("{noop}ns").authorities("EMPLOYEE");
		auth.inMemoryAuthentication().withUser("vs").password("{noop}vs").authorities("MANAGER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//declares which page (URL) will have what access type.
		
		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAnyAuthority("ADMIN")
		.antMatchers("/emp").hasAnyAuthority("EMPLOYEE")
		.antMatchers("/manager").hasAnyAuthority("MANAGER")
		.antMatchers("/common").hasAnyAuthority("EMPLOYEE", "MANAGER")
		
		//Any Other URLs which are not configured in above antMatchers generally declared authenticated() in real time.
		.anyRequest().authenticated()
		
		//Login Form Details.
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome", true)
		
		//logout form details.
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		//Exception Details.
		.and()
		.exceptionHandling()
		.accessDeniedPage("/accessDenied");
	}
}
