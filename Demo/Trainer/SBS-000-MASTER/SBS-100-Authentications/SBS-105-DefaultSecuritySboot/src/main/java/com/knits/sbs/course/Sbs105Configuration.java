package com.knits.sbs.course;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class Sbs105Configuration extends WebSecurityConfigurerAdapter{

	
	@Override
    protected void configure(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 
		
		authorizationBuilder.inMemoryAuthentication()
        .withUser("stefano").password(passwordEncoder().encode("stefan0"))
        .authorities("ROLE_MANAGER");
    }
    
    
    @Override
    protected void configure(HttpSecurity authorizationBuilder) throws Exception {  
    	
    	//configureBasicAuthentication(authorizationBuilder);
    	configureFormAuthentication(authorizationBuilder);
    
    }
       
    private void configureBasicAuthentication(HttpSecurity authorizationBuilder) throws Exception {
    	authorizationBuilder.authorizeRequests()    	
		.anyRequest().authenticated().and().httpBasic();	
    }
	
    private void configureFormAuthentication(HttpSecurity authorizationBuilder) throws Exception {
    	authorizationBuilder.authorizeRequests()    	
		.anyRequest().authenticated().and().formLogin();	
    }
    
  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
 
	
}
