package com.spring.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.blog.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http
		   .csrf().disable()
		   .authorizeRequests()
		   .antMatchers(HttpMethod.GET, "/api/**").permitAll()
		   .antMatchers("/api/auth/**").permitAll()
		   .anyRequest()
		   .authenticated()
		   .and()
		   .httpBasic();	
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//	
//		UserDetails gabriel = User.builder().username("gabriel").password(passwordEncoder().encode("password")).roles("USER").build();
//		UserDetails otherUSer = User.builder().username("admin").password(passwordEncoder().encode("password")).roles("ADMIN").build();
//		// TODO Auto-generated method stub
//		return new InMemoryUserDetailsManager(gabriel, otherUSer);
//	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
   @Override
   @Bean
   protected AuthenticationManager authenticationManager() throws Exception {
	return super.authenticationManager();
   }
   
   
   
   
	
	
	
	
	
}
