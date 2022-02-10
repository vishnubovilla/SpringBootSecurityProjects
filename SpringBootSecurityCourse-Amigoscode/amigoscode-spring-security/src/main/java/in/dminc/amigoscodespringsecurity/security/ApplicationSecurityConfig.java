package in.dminc.amigoscodespringsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index","/css/**","/js/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
        UserDetails annasmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))   //No PasswordEncoder error will be thrown only when this username is used to login.
                .roles("STUDENT")   //ROLE_STUDENT
                .build();

        UserDetails vbovillaUser = User.builder()
                .username("vbovilla")
                .password("password")
                .roles("STUDENT")
                .build();

//        System.out.println("Encoded Password : "+passwordEncoder.encode("password"));

        //when password encoder is specified for one user and another user does not use password encoder,
        //and when user details of user who does not using password encoder are used to sign/login, then we'll get the following error.
        //example: Encoded password does not look like BCrypt

        UserDetails lyndaUser = User.builder()
                .username("lynda")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                annasmithUser,
                vbovillaUser,
                lyndaUser
        );
    }
}
